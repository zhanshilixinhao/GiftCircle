package com.chouchongkeji.service.backpack.base.impl;

import com.alibaba.fastjson.JSONObject;
import com.chouchongkeji.dial.dao.backpack.BpItemMapper;
import com.chouchongkeji.dial.dao.backpack.consignment.ConsignmentMapper;
import com.chouchongkeji.dial.pojo.backpack.BpItem;
import com.chouchongkeji.dial.pojo.backpack.ForRecord;
import com.chouchongkeji.dial.pojo.backpack.Vbp;
import com.chouchongkeji.dial.pojo.backpack.consignment.Consignment;
import com.chouchongkeji.dial.pojo.backpack.consignment.ConsignmentOrder;
import com.chouchongkeji.dial.pojo.gift.item.ItemOrderDetail;
import com.chouchongkeji.dial.pojo.gift.virtualItem.VirItemOrder;
import com.github.pagehelper.PageInfo;
import com.yichen.auth.redis.MRedisTemplate;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.base.BpService;
import com.chouchongkeji.service.backpack.gift.vo.GiftExItemVo;
import com.chouchongkeji.service.backpack.gift.vo.GiftItemVo;
import com.chouchongkeji.util.Constants;
import com.chouchongkeji.util.OrderHelper;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author yichenshanren
 * @date 2018/7/2
 */

@Service
public class BpServiceImpl implements BpService {


    @Autowired
    private BpItemMapper bpItemMapper;

    @Autowired
    private ConsignmentMapper consignmentMapper;

    @Autowired
    private OrderHelper orderHelper;

    @Autowired
    private MRedisTemplate mRedisTemplate;

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
        if (CollectionUtils.isNotEmpty(list)) {
            for (Vbp vbp : list) {
                if (vbp.getBuyTime() != null) {
                    vbp.setPickRemainTime(DateUtils.addDays(vbp.getBuyTime(), Constants.BP_EXPIRE_TIME).getTime() - System.currentTimeMillis());
                }else {
                    vbp.setPickRemainTime(738477564149L);
                }
            }
        }
        // 存查看背包列表的时间
        mRedisTemplate.setString("lasttimebplist"+userId,String.valueOf(System.currentTimeMillis()));
        return ResponseFactory.sucData(list);
    }


    /**
     * 微信背包列表
     *
     * @param userId 用户信息
     * @param type        1 物品 2 虚拟物品 3 优惠券
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @Override
    public Response getWxBpList(Integer userId, Integer type) {
        List<Vbp> list;
        list = bpItemMapper.selectAllByUserId(userId, type);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Vbp vbp : list) {
                if (vbp.getBuyTime() != null) {
                    vbp.setPickRemainTime(DateUtils.addDays(vbp.getBuyTime(), Constants.BP_EXPIRE_TIME).getTime() - System.currentTimeMillis());
                }else {
                    vbp.setPickRemainTime(738477564149L);
                }
            }
        }
        // 存查看背包列表的时间
        mRedisTemplate.setString("lasttimebplist"+userId,String.valueOf(System.currentTimeMillis()));
        return ResponseFactory.sucData(list);
    }

    /**
     * 我的背包红点(未查看的新品)
     *
     * @param userId
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @Override
    public Response getUnreadBpItem(Integer userId) {
        String string = mRedisTemplate.getString("lasttimebplist" + userId);
        Long time = null;
        Map map = new HashMap();
        if (StringUtils.isNotBlank(string)) {
            time = Long.parseLong(string);
            int count = bpItemMapper.selectByUserIdAndCreated(userId,time/1000);
            map.put("count",count);
            return ResponseFactory.sucData(map);
        }
        return ResponseFactory.suc();
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
//        for (BpItem item : items) {
//            add(item);
//        }
        return 1;
    }

    /**
     * 添加一条记录
     *
     * @param item 背包信息
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    public int add(BpItem item) {
        //判断背包里是否存在相同商品
//        BpItem bpItem = bpItemMapper.selectByTypeTarget(item.getTargetId(), item.getType(),item.getUserId());
//        if (bpItem == null) {
        int count = bpItemMapper.insert(item);
        if (count < 1) {
            throw new ServiceException(ErrorCode.ERROR);
        }
//        } else {
//            bpItem.setQuantity(bpItem.getQuantity() + item.getQuantity());
//            int i = bpItemMapper.updateByPrimaryKeySelective(bpItem);
//            if (i < 1) {
//                throw new ServiceException(ErrorCode.ERROR);
//            }
//        }
        return 1;
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
        Date date = new Date();
        List<BpItem> bpItems = new ArrayList<>();
        for (ItemOrderDetail detail : details) {
            BpItem bpItem = assembleBpItem(detail.getUserId(), detail.getQuantity(), detail.getPrice(),
                    detail.getSkuId(), Constants.BACKPACK_TYPE.ITEM, object.toJSONString(), date);
            bpItems.add(bpItem);
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
                                  Byte type, String from, Date buyTime) {
        BpItem item = new BpItem();
        item.setId(orderHelper.genOrderNo(7, 7));
        item.setUserId(userId);
        // 数量
        item.setQuantity(quantity);
        // 购买时间
        item.setBuyTime(buyTime);
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
                Constants.BACKPACK_TYPE.VIRTUAL_ITEM, object.toJSONString(), new Date()));
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
                consignment.getQuantity(), consignment.getPrice(),
                consignment.getTargetId(), consignment.getType(), object.toJSONString(), new Date()));
    }

    /**
     * 赠送的物品添加到背包
     *
     * @param recordDetailId 礼物记录详情id
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    public int addFromGiftSent(Integer recordDetailId, Integer userId, List<GiftItemVo> vos) {
        JSONObject object = new JSONObject();
        object.put("type", Constants.BP_ITEM_FROM.GIFT);
        object.put("recordDetailId", recordDetailId);

        List<BpItem> items = new ArrayList<>();
        for (GiftItemVo vo : vos) {
            items.add(assembleBpItem(userId, 1, vo.getPrice(),
                    vo.getTargetId(), vo.getTargetType(), object.toJSONString(), vo.getBuyTime()));
        }
        return addBatch(items);
    }

    /**
     * 礼物互换添加到背包
     *
     * @param giftExchangeId
     * @param userId
     * @param vos
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    public int addFromGiftExchange(Integer giftExchangeId, Integer userId, List<GiftExItemVo> vos) {
        JSONObject object = new JSONObject();
        object.put("type", Constants.BP_ITEM_FROM.GIFT_CHANGE);
        object.put("giftExchangeId", giftExchangeId);

        List<BpItem> items = new ArrayList<>();
        for (GiftExItemVo vo : vos) {
            items.add(assembleBpItem(userId, vo.getQuantity(), vo.getPrice(),
                    vo.getTargetId(), vo.getType(), object.toJSONString(), vo.getBuyTime()));
        }
        return addBatch(items);
    }

    /**
     * 向好友索要物品成功添加到背包
     *
     * @param forRecord
     * @param bpItem
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    public int addFromFriendBp(ForRecord forRecord, BpItem bpItem) {
        JSONObject object = new JSONObject();
        object.put("type", Constants.BP_ITEM_FROM.ASK_FOR);
        object.put("forRecordId", forRecord.getId());
        return add(assembleBpItem(forRecord.getUserId(),
                bpItem.getQuantity(), bpItem.getPrice(),
                bpItem.getTargetId(), bpItem.getType(), object.toJSONString(), bpItem.getBuyTime()));
    }


    /**
     * 背包列表
     *
     * @param userId 用户信息
     * @param key    关键字
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @Override
    public Response search(Integer userId, String key, PageQuery page) {
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Vbp> vbps = bpItemMapper.selectBySearch(userId, key);
        if (CollectionUtils.isNotEmpty(vbps)) {
            for (Vbp vbp : vbps) {
                if (vbp.getBuyTime() != null) {
                    vbp.setPickRemainTime(DateUtils.addDays(vbp.getBuyTime(), Constants.BP_EXPIRE_TIME).getTime() - System.currentTimeMillis());
                }else {
                    vbp.setPickRemainTime(738477564149L);
                }
            }
        }
        return ResponseFactory.sucData(vbps);
    }


}
