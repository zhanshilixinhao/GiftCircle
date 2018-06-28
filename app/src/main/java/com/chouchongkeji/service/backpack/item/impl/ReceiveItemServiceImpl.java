package com.chouchongkeji.service.backpack.item.impl;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.dial.dao.backpack.item.BpItemMapper;
import com.chouchongkeji.dial.dao.backpack.item.ReceiveItemOrderMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemSkuMapper;
import com.chouchongkeji.dial.dao.iwant.receiveAddress.ShippingMapper;
import com.chouchongkeji.dial.pojo.backpack.item.BpItem;
import com.chouchongkeji.dial.pojo.backpack.item.ReceiveItemOrder;
import com.chouchongkeji.dial.pojo.gift.item.ItemSku;
import com.chouchongkeji.dial.pojo.iwant.receiveAddress.Shipping;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.item.ReceiveItemService;
import com.chouchongkeji.util.Constants;
import com.chouchongkeji.util.OrderHelper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    /**
     * 创建提货订单
     * @param userId 用户id
     * @param bpItemId 背包商品id
     * @param shippingId 收货地址id
     * @return
     * @author linqin
     * @date 2018/6/28
     */
    @Override
    public Response createOrder(Integer userId,Integer client, Integer bpItemId,Integer shippingId) {
        //根据背包商品id和用户id取出信息
        BpItem bpItem = bpItemMapper.selectByUserIdAndBpItemId(userId,bpItemId);
        if (bpItem == null){
            return ResponseFactory.err("背包里不存在该商品");
        }
        //判断数量是否大于0
        if (bpItem.getQuantity()<1){
            return ResponseFactory.err("商品数量不足");
        }
        //查询itemSku的值
        int skuId = bpItem.getSkuId();
        ItemSku itemSku = itemSkuMapper.selectBySkuId(skuId);
        //取出收货地址
        Shipping shippingInfo = shippingMapper.selectByShippingId(shippingId);
        if (shippingInfo == null){
            return ResponseFactory.err("没有收货地址");
        }
        //创建订单
        ReceiveItemOrder itemOrder = new ReceiveItemOrder();
        itemOrder.setUserId(userId);
        itemOrder.setItemId(bpItem.getItemId());
        itemOrder.setBpItemId(bpItemId);
        itemOrder.setSkuId(skuId);
        itemOrder.setOrderNo(orderHelper.genOrderNo(client, 4));
        itemOrder.setTitle(itemSku.getTitle());
        itemOrder.setCover(itemSku.getCover());
        itemOrder.setPrice(bpItem.getPrice());
        itemOrder.setQuantity(1);
        itemOrder.setReceiveInfo(JSON.toJSONString(shippingInfo)); //json序列化 对象变成字符串
        itemOrder.setStatus(Constants.ORDER_DELIVER.NO_DELIVER); //1未发货
        int i = receiveItemOrderMapper.insert(itemOrder);
        if (i< 1){
            return ResponseFactory.err("创建提货订单失败");
        }
        //减少背包里已经提货的物品
       bpItem.setQuantity(bpItem.getQuantity()-1);
        //更新背包
        bpItemMapper.updateByPrimaryKeySelective(bpItem);
        return ResponseFactory.sucMsg("订单创建成功");
    }

    /**
     * 提货订单列表
     *
     * @param userId
     * @param pageQuery
     * @param status 订单状态，1-待发货；2-已发货；3-已收货,待评价，4-已评价,5-取消，6-删除
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    @Override
    public Response getOrderList(Integer userId, PageQuery pageQuery,Integer status) {
        //分页
        PageHelper.startPage(pageQuery.getPageNum(),pageQuery.getPageSize());


        return null;
    }
}



