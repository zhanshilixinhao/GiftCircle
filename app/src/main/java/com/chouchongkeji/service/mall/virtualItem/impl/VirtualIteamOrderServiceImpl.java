package com.chouchongkeji.service.mall.virtualItem.impl;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.dial.dao.gift.virtualItem.UserVirtualItemMapper;
import com.chouchongkeji.dial.dao.gift.virtualItem.VirItemOrderMapper;
import com.chouchongkeji.dial.dao.gift.virtualItem.VirtualItemMapper;
import com.chouchongkeji.dial.pojo.gift.virtualItem.UserVirtualItem;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
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
import com.chouchongkeji.service.iwant.wallet.WalletService;
import com.chouchongkeji.service.mall.virtualItem.VirtualIteamOrderService;
import com.chouchongkeji.service.user.info.AppPaymentInfoService;
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
    private AppPaymentInfoService appPaymentInfoService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserVirtualItemMapper userVirtualItemMapper;

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
        if (virItemOrder.getStatus().intValue() == 2) {
            return ResponseFactory.err("该订单已支付!");
        }
        //余额支付
        if (payWay == Constants.PAY_TYPE.yue) {
            //扣减余额，更新余额，钱包记录
            int count = walletService.updateBalance(userId, virItemOrder.getTotalPrice(), Constants.WALLET_RECORD.CON_BUY_VIRITEM,virItemOrder.getId());
            if (count < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "支付失败,请选择其他支付方式");
            }
            //更新订单状态
            virItemOrder.setStatus(Constants.CHARGE_ORDER_STATUS.PAY);
            virItemOrder.setUpdated(new Date());
            int i = virItemOrderMapper.updateByPrimaryKey(virItemOrder);
            if (i < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新状态失败");
            }
            //更新销量和详细订单状
            VirtualItem virtualItem = virtualItemMapper.selectByPrimaryKey(virItemOrder.getVirtualItemId());
            virtualItem.setSales(virtualItem.getSales() + virItemOrder.getQuantity());
            virtualItem.setUpdated(new Date());
            i = virtualItemMapper.updateByPrimaryKey(virtualItem);
            if (i < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(),"更新销量失败");
            }
            //  保存用户虚拟商品
            UserVirtualItem userVirtualItem = new UserVirtualItem();
            userVirtualItem.setVirtualItemId(virItemOrder.getVirtualItemId());
            userVirtualItem.setUserId(userId);
            userVirtualItem.setUpdated(new Date());
            userVirtualItem.setTotalPrice(virItemOrder.getTotalPrice());
            userVirtualItem.setSummary(virItemOrder.getSummary());
            userVirtualItem.setQuantity(virItemOrder.getQuantity());
            userVirtualItem.setPrice(virItemOrder.getPrice());
            userVirtualItem.setName(virItemOrder.getName());
            userVirtualItem.setCreated(new Date());
            userVirtualItem.setCover(virItemOrder.getCover());
            i = userVirtualItemMapper.insert(userVirtualItem);
            if (i < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(),"余额支付失败");
            }
            //保存支付信息
            appPaymentInfoService.doYuePaySuccess(orderNo, userId, virItemOrder.getCreated(), Constants.ORDER_TYPE.ITEM,
                    0,virItemOrder.getTotalPrice());
            return ResponseFactory.sucMsg("支付成功");
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
