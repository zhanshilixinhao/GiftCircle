package com.chouchongkeji.service.backpack.consignment.impl;

import com.chouchongkeji.dial.dao.backpack.BpItemMapper;
import com.chouchongkeji.dial.dao.backpack.VbpMapper;
import com.chouchongkeji.dial.dao.backpack.consignment.ConsignmentMapper;
import com.chouchongkeji.dial.pojo.backpack.BpItem;
import com.chouchongkeji.dial.pojo.backpack.Vbp;
import com.chouchongkeji.dial.pojo.backpack.consignment.Consignment;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.consignment.ConsignmentService;
import com.chouchongkeji.service.backpack.consignment.vo.ConsignmentVo;
import com.chouchongkeji.service.backpack.consignment.vo.PriceVo;
import com.chouchongkeji.util.Constants;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author linqin
 * @date 2018/7/2
 */
@Service
@Transactional(rollbackFor = Exception.class ,isolation = Isolation.REPEATABLE_READ)
public class ConsignmentServiceImpl implements ConsignmentService {

    @Autowired
    private VbpMapper vbpMapper;

    @Autowired
    private ConsignmentMapper consignmentMapper;

    @Autowired
    private BpItemMapper bpItemMapper;

    /**
     * 上架寄售台之前商品信息查询
     *
     * @param userId 用户Id
     * @param bpId   背包id
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    @Override
    public Response getInfo(Integer userId, Long bpId) {
        //根据用户Id和背包id查询物品信息
        Vbp vbp = vbpMapper.selectByUserIdBpId(userId, bpId);
        if (vbp == null) {
            return ResponseFactory.err("背包里不存在该物品");
        }
        //查询最高/最低/最近商品销售价格
        int targetId = vbp.getTargetId();
        int type = vbp.getType();//1 物品 2 虚拟物品 3 优惠券
        //虚拟商品无法上架
        if (type == Constants.BACKPACK_TYPE.VIRTUAL_ITEM) {
            return ResponseFactory.err("寄售台中不存在虚拟商品");
        }
        PriceVo Price = consignmentMapper.selectByTargetIdType(targetId, type);
        if (Price == null) {
            return ResponseFactory.err("寄售台无类似商品");
        }
        return ResponseFactory.sucData(Price);
    }

    /**
     * 上架寄售台
     *
     * @param userId 用户Id
     * @param bpId   背包Id
     * @param price  商品上架价格
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    @Override
    public Response putawayItem(Integer userId, Long bpId, BigDecimal price) {
        //根据用户Id和背包id查询物品信息
        Vbp vbp = vbpMapper.selectByUserIdBpId(userId, bpId);
        if (vbp == null) {
            return ResponseFactory.err("背包里不存在该物品");
        }
        //虚拟商品无法上架
        int type = vbp.getType();//1 物品 2 虚拟物品 3 优惠券
        if (type == Constants.BACKPACK_TYPE.VIRTUAL_ITEM) {
            return ResponseFactory.err("虚拟商品无法上架");
        }
        //背包里的物品数量大于0才能上架
        int quantity = vbp.getQuantity();
        if (quantity < 1) {
            return ResponseFactory.err("商品数量不足");
        }
        //上架背包里的物品
        Consignment item = new Consignment();
        item.setUserId(userId);
        item.setBpId(bpId);
        item.setTargetId(vbp.getTargetId());
        item.setQuantity(1);
        item.setPrice(price);
        item.setStatus(Constants.CONSIGNMENT_ITEM.UP);
        item.setType(vbp.getType());
        int insert = consignmentMapper.insert(item);
        if (insert < 1) {
            return ResponseFactory.err("商品上架失败");
        }
        //更新背包商品数量
        BpItem bpItem = bpItemMapper.selectByUserIdAndBpItemId(userId, bpId);
        bpItem.setQuantity(quantity - 1);
        bpItemMapper.updateByPrimaryKeySelective(bpItem);
        return ResponseFactory.sucMsg("商品上架成功");
    }


    /**
     * 寄售台卖家/买家列表
     *
     * @param userId    用户Id
     * @param user      卖家/买家  1-卖家，2-买家
     * @param condition 商家订单状态 1-全部 ，2-交易中，3-已完成
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    @Override
    public Response buySellList(Integer userId, Byte user, Byte condition, PageQuery pageQuery) {
        //分页
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        //卖家列表
        if (user == Constants.SELLER_BUYER.SELLER) {
            //查询上架商品
            List<ConsignmentVo> sellerList = consignmentMapper.selectList(userId,condition);
            if (sellerList == null){
                return ResponseFactory.sucMsg("还没有商品哦");
            }
            return ResponseFactory.sucData(sellerList);
        }else {//买家列表
            //查询上架商品
            List<ConsignmentVo> buyerList = consignmentMapper.selectBuyerList(userId,condition);
            if (buyerList == null){
                return ResponseFactory.sucMsg("还没有商品哦");
            }
            return ResponseFactory.sucData(buyerList);
        }
    }


    /**
     * 寄售台商品下架功能
     * @param userId 用户id
     * @param consignmentId 寄售台id
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    @Override
    public Response soldOutItem(Integer userId, Integer consignmentId) {
        //查询用户上架商品
        Consignment conVo = consignmentMapper.selectPutAwayItem(userId,consignmentId);
        if (conVo == null){
            throw new ServiceException(ErrorCode.ERROR.getCode(),"商品为空");
        }
        Byte status =  conVo.getStatus();
        //商品状态为1才能下架
        if (status != Constants.CONSIGNMENT_ITEM.UP){
            throw new ServiceException(ErrorCode.ERROR.getCode(),"商品状态为1才能下架");
        }
        //更新商品状态为4（下架）,
        conVo.setStatus(Constants.CONSIGNMENT_ITEM.DOWN);
        int update = consignmentMapper.updateByPrimaryKeySelective(conVo);
        if (update <1){
            throw  new ServiceException(ErrorCode.ERROR.getCode(),"更新状态失败");
        }
        //把下架商品退回到背包中
        Long id = conVo.getBpId();
        BpItem bpItem = bpItemMapper.selectByPrimaryKey(id);
        if (bpItem == null){
            throw new ServiceException(ErrorCode.ERROR.getCode(),"背包商品为空");
        }
        bpItem.setQuantity(bpItem.getQuantity()+1);
        bpItemMapper.updateByPrimaryKeySelective(bpItem);
        return ResponseFactory.sucMsg("下架成功");
    }
}
