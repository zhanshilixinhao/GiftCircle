package com.chouchongkeji.service.gift.virtualItem.impl;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.dial.dao.gift.virtualItem.VirItemOrderMapper;
import com.chouchongkeji.dial.dao.gift.virtualItem.VirtualItemMapper;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.pay.KeyUtil;
import com.chouchongkeji.goexplore.pay.PayResultVo;
import com.chouchongkeji.goexplore.pay.PayVO;
import com.chouchongkeji.goexplore.pay.alipay_v2.AliPayServiceV2;
import com.chouchongkeji.goexplore.pay.weixin.service.WXPayDto;
import com.chouchongkeji.goexplore.pay.weixin.service.WXPayService;
import com.chouchongkeji.goexplore.utils.RSAProvider;
import com.chouchongkeji.dial.pojo.gift.virtualItem.VirItemOrder;
import com.chouchongkeji.dial.pojo.gift.virtualItem.VirtualItem;
import com.chouchongkeji.service.gift.virtualItem.VirtualIteamOrderService;
import com.chouchongkeji.util.Constants;
import com.chouchongkeji.util.OrderHelper;
import com.chouchongkeji.service.vo.BaseOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yy
 * @date 2018/6/12
 **/

@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class VirtualIteamOrderServiceImpl implements VirtualIteamOrderService {
    @Autowired
    private VirItemOrderMapper virItemOrderMapper;

    @Autowired
    private VirtualItemMapper virtualItemMapper;

    @Autowired
    private OrderHelper orderHelper;

    /**
     * 创建虚拟商品订单
     *
     * @param: [userId, client, payWay, id, quantity]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/12
     */
    @Override
    public Response createVirOrder(Integer userId, Integer client, Integer id, Integer quantity) {
        // 获得虚拟商品信息
        VirtualItem virtualItem = virtualItemMapper.selectByPrimaryKey(id);
        if (virtualItem == null) {
            return ResponseFactory.err("无此商品");
        }
        // 计算商品总价
        BigDecimal totalPrice = virtualItem.getPrice().multiply(new BigDecimal(quantity));
        // 创建订单
        VirItemOrder virItemOrder = new VirItemOrder();
        // 设置商品单价和订单总价
        virItemOrder.setPrice(virtualItem.getPrice());
        virItemOrder.setTotalPrice(totalPrice);
        // 初始化订单
        createVirItemOrder(virItemOrder, id, quantity, virtualItem, userId, client);
        // 保存订单
        int count = virItemOrderMapper.insert(virItemOrder);
        if (count < 1) {
            ResponseFactory.err("订单创建失败");
        }
        BaseOrderVo baseOrderVo = new BaseOrderVo();
        baseOrderVo.setOrderNo(virItemOrder.getOrderNo());
        baseOrderVo.setTotalPrice(totalPrice);
        return ResponseFactory.sucData(baseOrderVo);
    }

    /**
     * 创建支付订单
     *
     * @param: [userId, payWay, orderNo]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/12
     */
    @Override
    public Response payVirOrder(Integer userId, Integer payWay, Long orderNo) {
        // 取出用户订单
        VirItemOrder virItemOrder = virItemOrderMapper.selectByOrderNo(orderNo);
        if (virItemOrder == null) {
            ResponseFactory.err("订单不存在");
        }
        if (!virItemOrder.getUserId().equals(userId)) {
            ResponseFactory.err("无权操作");
        }
        if (!virItemOrder.getStatus().equals(1)) {
            return ResponseFactory.err("该订单已支付!");
        }
        // 创建订单参数
        PayVO payVO = assemblePayOrder(virItemOrder, payWay);
        String info = null;
//        String mingwen;
        // 如果时支付宝，构造支付宝参数并签名
        if (payWay == Constants.PAY_TYPE.ALI) {
            info = AliPayServiceV2.createOrderInfo(payVO);
//            System.out.println(info);
//            mingwen = info;
            info = RSAProvider.encrypt(info, KeyUtil.PRIVATE_KEY);
        } else { // 微信支付
            WXPayDto dto = WXPayService.service(payVO).createPrePay();
            if (dto.getCode() != 1) {
                return ResponseFactory.err("创建微信订单失败，" + dto.getMessage());
            }
//            mingwen = JSON.toJSONString(dto);
            info = RSAProvider.encrypt(JSON.toJSONString(dto), KeyUtil.PRIVATE_KEY);
        }
        if (info == null) {
            return ResponseFactory.err("支付宝创建失败");
        }
        // 请求支付成功
        PayResultVo payResultVo = new PayResultVo();
        payResultVo.setParams(info);
        payResultVo.setType(payWay);
        payResultVo.setOrderNo(orderNo);
//        payResultVo.setParams1(mingwen);
        return ResponseFactory.sucData(payResultVo);
    }

    /**
     * 初始化订单
     *
     * @param: [virItemOrder, id, quantity, virtualItem, userId, client]
     * @return: void
     * @author: yy
     * @Date: 2018/6/12
     */
    private void createVirItemOrder(VirItemOrder virItemOrder, Integer id, Integer quantity, VirtualItem virtualItem, Integer userId, Integer client) {
        Long orderNo = orderHelper.genOrderNo(client, 3);
        virItemOrder.setVirtualItemId(id);
        virItemOrder.setUserId(userId);
        virItemOrder.setUpdated(new Date());
        virItemOrder.setSummary(virtualItem.getDescription());
        virItemOrder.setStatus((byte)1);
        virItemOrder.setQuantity(quantity);
        virItemOrder.setOrderNo(orderNo);
        virItemOrder.setName(virtualItem.getName());
        virItemOrder.setCreated(new Date());
        virItemOrder.setCover(virtualItem.getCover());
    }

    /**
     * 构造支付宝|微信需要的支付参数
     *
     * @param order 用户订单
     * @return
     */
    private PayVO assemblePayOrder(VirItemOrder order, int type) {
        PayVO vo = new PayVO();
        vo.setBody(Constants.PAY_BODY);
        vo.setSubject(Constants.PAY_SUBJECT_ORDER);
        vo.setOrderNo(order.getOrderNo());
        if (type == Constants.PAY_TYPE.ALI) {
            vo.setUrl("virItem_order/ali");
        }
        if (type == Constants.PAY_TYPE.WX) {
            vo.setUrl("virItem_order/wx");;
        }
        vo.setPrice(order.getTotalPrice());
        return vo;
    }
}
