package com.chouchongkeji.service.backpack.item.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.dial.dao.backpack.BpItemMapper;
import com.chouchongkeji.dial.dao.backpack.item.ReceiveItemOrderMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemCommentMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemSkuMapper;
import com.chouchongkeji.dial.dao.iwant.receiveAddress.ShippingMapper;
import com.chouchongkeji.dial.pojo.backpack.BpItem;
import com.chouchongkeji.dial.pojo.backpack.item.ReceiveItemOrder;
import com.chouchongkeji.dial.pojo.gift.item.ItemComment;
import com.chouchongkeji.dial.pojo.gift.item.ItemSku;
import com.chouchongkeji.dial.pojo.iwant.receiveAddress.Shipping;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.item.ReceiveItemService;
import com.chouchongkeji.service.backpack.item.vo.LogisticsInfoVo;
import com.chouchongkeji.service.backpack.item.vo.ReOrderDetailVo;
import com.chouchongkeji.service.backpack.item.vo.ReceiveItemVo;
import com.chouchongkeji.service.kdapi.ExpressApi;
import com.chouchongkeji.service.kdapi.KdResult;
import com.chouchongkeji.util.Constants;
import com.chouchongkeji.util.OrderHelper;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linqin
 * @date 2018/6/22
 */
@Service
public class ReceiveItemServiceImpl implements ReceiveItemService {

    @Autowired
    private ReceiveItemOrderMapper receiveItemOrderMapper;

    @Autowired
    private BpItemMapper bpItemMapper;

    @Autowired
    private OrderHelper orderHelper;

    @Autowired
    private ItemSkuMapper itemSkuMapper;

    @Autowired
    private ShippingMapper shippingMapper;

    @Autowired
    private ItemCommentMapper itemCommentMapper;

    /**
     * 创建提货订单
     *
     * @param userId     用户id
     * @param bpId       背包商品id
     * @param shippingId 收货地址id
     * @return
     * @author linqin
     * @date 2018/6/28
     */
    @Override
    public Response createOrder(Integer userId, Integer client, Long bpId, Integer shippingId) {
        //根据背包商品id和用户id取出信息
        BpItem bpItem = bpItemMapper.selectByUserIdAndBpItemId(userId, bpId);
        if (bpItem == null) {
            return ResponseFactory.err("背包里不存在该商品");
        }
        if (bpItem.getType() != Constants.BACKPACK_TYPE.ITEM) {
            return ResponseFactory.err("物品才能提货!");
        }

        //判断数量是否大于0
        if (bpItem.getQuantity() < 1) {
            return ResponseFactory.err("商品数量不足");
        }
        //查询itemSku的值
        Integer skuId = bpItem.getTargetId();
        ItemSku itemSku = itemSkuMapper.selectBySkuId(skuId);
        //取出收货地址
        Shipping shippingInfo = shippingMapper.selectByShippingId(shippingId);
        if (shippingInfo == null) {
            return ResponseFactory.err("没有收货地址");
        }
        //创建订单
        ReceiveItemOrder itemOrder = new ReceiveItemOrder();
        itemOrder.setUserId(userId);
        itemOrder.setItemId(itemSku.getItemId());
        itemOrder.setBpId(bpId);
        itemOrder.setSkuId(skuId);
        itemOrder.setOrderNo(orderHelper.genOrderNo(client, 4));
        itemOrder.setTitle(itemSku.getTitle());
        itemOrder.setCover(itemSku.getCover());
        itemOrder.setPrice(bpItem.getPrice());
        itemOrder.setQuantity(1);
        itemOrder.setReceiveInfo(JSON.toJSONString(shippingInfo)); //json序列化 对象变成字符串
        itemOrder.setStatus(Constants.ORDER_DELIVER.NO_DELIVER); //1未发货
        int i = receiveItemOrderMapper.insert(itemOrder);
        if (i < 1) {
            return ResponseFactory.err("创建提货订单失败");
        }
        //减少背包里已经提货的物品
        bpItem.setQuantity(bpItem.getQuantity() - 1);
        //更新背包
        bpItemMapper.updateByPrimaryKeySelective(bpItem);
        return ResponseFactory.sucMsg("订单创建成功");
    }

    /**
     * 提货订单列表
     *
     * @param userId
     * @param pageQuery
     * @param status    订单状态，0-全部，1-未完成(待发货,已发货)；2-已完成（已收货待评价，已评价）
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    @Override
    public Response getOrderList(Integer userId, PageQuery pageQuery, Integer status) {
        //分页
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        //根据用户id和状态查询提货订单
        List<ReceiveItemOrder> order = receiveItemOrderMapper.selectByUserIdStatus(userId, status);
        if (order == null) {
            return ResponseFactory.suc();
        }
        //创建新的列表
        List<ReceiveItemVo> list = new ArrayList<>();
        for (ReceiveItemOrder itemOrder : order) {
            ReceiveItemVo vo = new ReceiveItemVo();
            vo.setOrderNo(itemOrder.getOrderNo());
            vo.setCover(itemOrder.getCover());
            vo.setDescription(itemOrder.getDescription());
            vo.setPrice(itemOrder.getPrice());
            vo.setItemId(itemOrder.getItemId());
            vo.setTitle(itemOrder.getTitle());
            vo.setStatus(itemOrder.getStatus());
            vo.setCreated(itemOrder.getCreated());
            vo.setUpdated(itemOrder.getUpdated());
            //遍历之后添加到列表里面
            list.add(vo);
        }
        return ResponseFactory.sucData(list);
    }

    /**
     * 提货订单详情
     *
     * @param userId  用户id
     * @param orderNo 提货订单号
     * @return
     * @author linqin
     * @date 2018/6/28
     */
    @Override
    public Response getOrderDetail(Integer userId, Long orderNo) {
        //根据用户Id和提货订单号取出订单
        ReceiveItemOrder itemOrder = receiveItemOrderMapper.selectByUserIdOrderNo(userId, orderNo);
        if (itemOrder == null) {
            return ResponseFactory.err("该订单不存在");
        }
        ReOrderDetailVo vo = new ReOrderDetailVo();
        vo.setId(itemOrder.getId());
        vo.setUserId(itemOrder.getUserId());
        vo.setBpId(itemOrder.getBpId());
        vo.setItemId(itemOrder.getItemId());
        vo.setSkuId(itemOrder.getSkuId());
        vo.setTitle(itemOrder.getTitle());
        vo.setCover(itemOrder.getCover());
        vo.setOrderNo(itemOrder.getOrderNo());
        vo.setDescription(itemOrder.getDescription());
        vo.setPrice(itemOrder.getPrice());
        vo.setTotalPrice(itemOrder.getTotalPrice());
        vo.setQuantity(itemOrder.getQuantity());
        vo.setStatus(itemOrder.getStatus());
        vo.setReceiveInfo(JSON.parseObject(itemOrder.getReceiveInfo(), new TypeReference<Shipping>() {
        }));
        //序列化，将物流信息对象转化为json字符串
        LogisticsInfoVo logisticsInfoVo = JSON.parseObject(itemOrder.getLogisticsInfo(), new TypeReference<LogisticsInfoVo>() {
        });
        //显示物流信息
        vo.setLogisticsInfo(logisticsInfoVo);
        //根据物流公司和订单号查询物流信息
        KdResult logisticsInfo = null;
        if (logisticsInfoVo != null) {
            logisticsInfo = ExpressApi.getLogisticsInfo(logisticsInfoVo.getCom(), logisticsInfoVo.getExpressNo());
        }
        //把物流信息返回给前端
        vo.setLogisticsTrace(logisticsInfo != null ? logisticsInfo.getData() : new ArrayList<>());
        vo.setCreated(itemOrder.getCreated());
        vo.setUpdated(itemOrder.getUpdated());
        return ResponseFactory.sucData(vo);

    }

    /**
     * 提货订单状态处理,确认收货
     *
     * @param userId
     * @param orderNo 提货订单号
     * @return
     * @author linqin
     * @date 2018/6/29
     */
    @Override
    public Response confirmOrder(Integer userId, Long orderNo) {
        //根据用户Id和提货订单号取出订单
        ReceiveItemOrder itemOrder = receiveItemOrderMapper.selectByUserIdOrderNo(userId, orderNo);
        if (itemOrder == null) {
            return ResponseFactory.err("该订单不存在");
        }
        //状态是发货才可以确认收货
        Byte status = itemOrder.getStatus();
        if (status != Constants.ORDER_DELIVER.DELIVER) {
            return ResponseFactory.err("发货以后才能确认收货");
        }
        //更新状态
        itemOrder.setStatus(Constants.ORDER_DELIVER.RECEIVE);
        receiveItemOrderMapper.updateByPrimaryKeySelective(itemOrder);
        return ResponseFactory.sucMsg("确认收货成功");
    }


    /**
     * 提货订单状态处理,评论订单
     *
     * @param userId
     * @param orderNo  提货订单号
     * @param star     评价星星
     * @param content  评论文字
     * @param pictures 评论照片
     * @return
     * @author linqin
     * @date 2018/6/30
     */
    @Override
    public Response commentOrder(Integer userId, Long orderNo, Integer star, String content, String pictures) {
        //根据用户Id和提货订单号取出订单
        ReceiveItemOrder itemOrder = receiveItemOrderMapper.selectByUserIdOrderNo(userId, orderNo);
        if (itemOrder == null) {
            return ResponseFactory.err("该订单不存在");
        }
        //状态是确认收货才可以评论
        Byte status = itemOrder.getStatus();
        if (status != Constants.ORDER_DELIVER.RECEIVE) {
            return ResponseFactory.err("只能评论确认收货的订单");
        }
        //添加评论
        ItemComment itemComment = new ItemComment();
        itemComment.setSkuId(itemOrder.getSkuId());
        itemComment.setItemId(itemOrder.getItemId());
        itemComment.setUserId(userId);
        itemComment.setOrderNo(orderNo);
        itemComment.setStar(star);
        itemComment.setContent(content);
        //判断是否传图片
        if (StringUtils.isNotBlank(pictures)) {
            //图片字符串转化为数组
            String[] split = pictures.split(",");
            //数组转化为JSON字符串
            String s = JSON.toJSONString(split);
            itemComment.setPictures(s);
        }
        int count = itemCommentMapper.insert(itemComment);
        if (count < 0) {
            return ResponseFactory.err("评论失败");
        }
        //更新状态
        itemOrder.setStatus(Constants.ORDER_DELIVER.COMMENT);
        receiveItemOrderMapper.updateByPrimaryKeySelective(itemOrder);
        return ResponseFactory.sucMsg("评论成功");
    }


}


