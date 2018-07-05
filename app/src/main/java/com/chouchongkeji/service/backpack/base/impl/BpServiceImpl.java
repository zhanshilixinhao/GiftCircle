package com.chouchongkeji.service.backpack.base.impl;

import com.alibaba.fastjson.JSONObject;
import com.chouchongkeji.dial.dao.backpack.BpItemMapper;
import com.chouchongkeji.dial.dao.backpack.consignment.ConsignmentMapper;
import com.chouchongkeji.dial.dao.backpack.gift.GiftRecordDetail;
import com.chouchongkeji.dial.pojo.backpack.BpItem;
import com.chouchongkeji.dial.pojo.backpack.Vbp;
import com.chouchongkeji.dial.pojo.backpack.consignment.Consignment;
import com.chouchongkeji.dial.pojo.backpack.consignment.ConsignmentOrder;
import com.chouchongkeji.dial.pojo.gift.item.ItemOrderDetail;
import com.chouchongkeji.dial.pojo.gift.virtualItem.GiftRecord;
import com.chouchongkeji.dial.pojo.gift.virtualItem.VirItemOrder;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.base.BpService;
import com.chouchongkeji.util.Constants;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/7/2
 */

@Component
public class BpServiceImpl implements BpService {

    @Autowired
    private BpItemMapper bpItemMapper;

    @Autowired
    private ConsignmentMapper consignmentMapper;

    /**
     * 背包列表
     *
     * @param userId 用户信息
     * @param type   1 物品 2 虚拟物品 3 优惠券
     * @param page
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @Override
    public Response getList(Integer userId, Integer type, PageQuery page) {
        List<Vbp> list;
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        list = bpItemMapper.selectAllByUserId(userId, type);
        return ResponseFactory.sucData(list);
    }

    /**
     * 添加到背包
     *
     * @param items 添加的物品
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @Override
    public int addBatch(List<BpItem> items) {
        int count = bpItemMapper.insertBatch(items);
        if (count < 1) {
            throw new ServiceException(ErrorCode.ERROR);
        }
        return count;
    }

    /**
     * 添加一条记录
     *
     * @param item 背包信息
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    private int add(BpItem item) {
        int count = bpItemMapper.insert(item);
        if (count < 1) {
            throw new ServiceException(ErrorCode.ERROR);
        }
        return count;
    }

    /**
     * 商品购买成功添加到背包
     *
     * @param details 购买的商品列表
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    public int addFromItemOrder(List<ItemOrderDetail> details) {
        JSONObject object = new JSONObject();
        object.put("type", Constants.BP_ITEM_FROM.ITEM_ORDER);
        object.put("orderNo", details.get(0).getOrderNo());
        // 遍历商品列表取出信息
        List<BpItem> bpItems = new ArrayList<>();
        for (ItemOrderDetail detail : details) {
            bpItems.add(assembleBpItem(detail.getUserId(), detail.getQuantity(), detail.getPrice(),
                    detail.getSkuId(), Constants.BACKPACK_TYPE.ITEM, object.toJSONString()));
        }
        return addBatch(bpItems);
    }

    /**
     * 组装
     *
     * @param quantity
     * @param price
     * @param targetId
     * @param type
     * @param from
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    private BpItem assembleBpItem(Integer userId, Integer quantity,
                                  BigDecimal price, Integer targetId,
                                  Byte type, String from) {
        BpItem item = new BpItem();
        item.setUserId(userId);
        // 数量
        item.setQuantity(quantity);
        // 价格
        item.setPrice(price);
        // 目标id
        item.setTargetId(targetId);
        // 类型
        item.setType(type);
        item.setFrom(from);
        return item;
    }

    /**
     * 虚拟商品购买成功添加到背包
     *
     * @param virItemOrder 购买虚拟商品的订单
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    public int addFromVirtualItemOrder(VirItemOrder virItemOrder) {
        JSONObject object = new JSONObject();
        object.put("type", Constants.BP_ITEM_FROM.VIR_ITEM_ORDER);
        object.put("orderNo", virItemOrder.getOrderNo());
        return add(assembleBpItem(virItemOrder.getUserId(),
                virItemOrder.getQuantity(), virItemOrder.getPrice(),
                virItemOrder.getVirtualItemId(),
                Constants.BACKPACK_TYPE.VIRTUAL_ITEM, object.toJSONString()));
    }

    /**
     * 寄售台物品购买成功添加到背包
     *
     * @param consignmentOrder 购买寄售台商品的订单
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    public int addFromConsignmengOrder(ConsignmentOrder consignmentOrder) {
        JSONObject object = new JSONObject();
        object.put("type", Constants.BP_ITEM_FROM.CONSIGNMENG_ORDER);
        object.put("orderNo", consignmentOrder.getOrderNo());
        Consignment consignment = consignmentMapper.selectByPrimaryKey(consignmentOrder.getConsignmentId());
        if (consignment == null) {
            throw new ServiceException(ErrorCode.ERROR);
        }
        return add(assembleBpItem(consignment.getUserId(),
                consignment.getQuantity(),consignment.getPrice(),
                consignment.getTargetId(), consignment.getType(), object.toJSONString()));
    }

    /**
     * 赠送的物品添加到背包
     *
     * @param details 赠送的礼物列表
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    public int addFromGiftSent(GiftRecord record, List<GiftRecordDetail> details) {
        return 0;
    }

}
