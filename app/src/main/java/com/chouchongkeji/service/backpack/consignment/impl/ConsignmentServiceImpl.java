package com.chouchongkeji.service.backpack.consignment.impl;

import com.chouchongkeji.dial.dao.backpack.VbpMapper;
import com.chouchongkeji.dial.dao.backpack.consignment.ConsignmentMapper;
import com.chouchongkeji.dial.dao.backpack.item.BpItemMapper;
import com.chouchongkeji.dial.pojo.backpack.Vbp;
import com.chouchongkeji.dial.pojo.backpack.consignment.Consignment;
import com.chouchongkeji.dial.pojo.backpack.item.BpItem;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.backpack.consignment.ConsignmentService;
import com.chouchongkeji.service.backpack.consignment.vo.PriceVo;
import com.chouchongkeji.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2018/7/2
 */
@Service
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
     * @param userId  用户Id
     * @param bpId    背包id
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    @Override
    public Response getInfo(Integer userId, Long bpId) {
        //根据用户Id和背包id查询物品信息
        Vbp vbp = vbpMapper.selectByUserIdBpId(userId,bpId);
        if (vbp==null){
            return ResponseFactory.err("背包里不存在该物品");
        }
        //查询最高/最低/最近商品销售价格
        int targetId = vbp.getTargetId();
        int type = vbp.getType();//1 物品 2 虚拟物品 3 优惠券
        //虚拟商品无法上架
        if (type == Constants.BACKPACK_TYPE.VIRTUAL_ITEM){
            return ResponseFactory.err("寄售台中不存在虚拟商品");
        }
        PriceVo Price = consignmentMapper.selectByTargetIdType(targetId,type);
        if (Price == null){
            return ResponseFactory.err("寄售台无类似商品");
        }
        return ResponseFactory.sucData(Price);
    }

    /**
     * 上架寄售台
     * @param userId 用户Id
     * @param bpId 背包Id
     * @param price 商品上架价格
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    @Override
    public Response putawayItem(Integer userId, Long bpId, BigDecimal price) {
        //根据用户Id和背包id查询物品信息
        Vbp vbp = vbpMapper.selectByUserIdBpId(userId,bpId);
        if (vbp==null){
            return ResponseFactory.err("背包里不存在该物品");
        }
        //虚拟商品无法上架
        int type = vbp.getType();//1 物品 2 虚拟物品 3 优惠券
        if (type == Constants.BACKPACK_TYPE.VIRTUAL_ITEM){
            return ResponseFactory.err("虚拟商品无法上架");
        }
        //背包里的物品数量大于0才能上架
        int quantity = vbp.getQuantity();
        if (quantity<1){
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
        if (insert<1){
            return ResponseFactory.err("商品上架失败");
        }
        //更新背包商品数量
        BpItem bpItem = bpItemMapper.selectByUserIdAndBpItemId(userId,bpId);
        bpItem.setQuantity(quantity-1);
        bpItemMapper.updateByPrimaryKeySelective(bpItem);
        return ResponseFactory.sucMsg("商品上架成功");
    }





}
