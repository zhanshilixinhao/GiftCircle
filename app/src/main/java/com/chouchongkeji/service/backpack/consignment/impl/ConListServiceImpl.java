package com.chouchongkeji.service.backpack.consignment.impl;

import com.chouchongkeji.dial.dao.backpack.consignment.ConsignmentMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemMapper;
import com.chouchongkeji.dial.pojo.backpack.consignment.Consignment;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.properties.ServiceProperties;
import com.chouchongkeji.service.backpack.consignment.ConListService;
import com.chouchongkeji.service.backpack.consignment.vo.ConListVo;
import com.chouchongkeji.service.backpack.consignment.vo.ConsignmentVo;
import com.chouchongkeji.service.backpack.consignment.vo.DetailVo;
import com.chouchongkeji.util.Constants;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linqin
 * @date 2018/7/2
 */
@Service
public class ConListServiceImpl implements ConListService {

    @Autowired
    private ConsignmentMapper consignmentMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ServiceProperties serviceProperties;

    /**
     * 查询寄售台某个商品列表
     *
     * @param targetId 目标物品id
     * @param type     1 商品 2虚拟物品 3 优惠券
     * @return
     * @author linqin
     * @date 2018/7/2
     */
    @Override
    public Response getOneList(Integer targetId, Integer type, PageQuery pageQuery) {
        //分页
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        //查出寄售台相同商品
        List<ConsignmentVo> list = consignmentMapper.selectListByTargetIdType(targetId,type);
        return ResponseFactory.sucData(list);
    }

    /**
     * 寄售台商品列表
     *
     * @param pageQuery 分页
     * @return
     * @author linqin
     * @date 2018/7/3
     */
    @Override
    public Response getAllList(PageQuery pageQuery) {
        //分页
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        //查出寄售台商品列表
        List<ConListVo> listVos = consignmentMapper.selectAllItem();
        return ResponseFactory.sucData(listVos);
    }


    /**
     * 寄售台商品详情
     *
     * @param consignmentId 寄售台商品Id
     * @return
     * @author linqin
     * @date 2018/7/3
     */
    @Override
    public Response getItemDetail(Integer consignmentId) {
        //查出寄售台商品
        Consignment conItem = consignmentMapper.selectByPrimaryKey(consignmentId);
        if (conItem == null) {
            return ResponseFactory.err("寄售台中不存在该商品");
        }
        //物品才可以查看详细信息
        if (conItem.getType() != Constants.BACKPACK_TYPE.ITEM) {
            return ResponseFactory.err("物品才可以查看详细信息");
        }
        //查询商品详情
        Integer skuId = conItem.getTargetId();
        DetailVo detailVo = itemMapper.selectItemDtail(skuId);
        if (detailVo == null) {
            return ResponseFactory.err("商品不存在");
        }
        Integer id = detailVo.getItemId();
        detailVo.setConsignmentId(consignmentId);
        detailVo.setDetailUrl(serviceProperties.getProductDetail() + id);
        return ResponseFactory.sucData(detailVo);

    }


}
