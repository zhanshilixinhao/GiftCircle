package com.chouchongkeji.service.backpack.item.impl;

import com.chouchongkeji.dial.dao.backpack.BpItemMapper;
import com.chouchongkeji.dial.dao.backpack.VbpMapper;
import com.chouchongkeji.dial.dao.backpack.gift.AppMessageMapper;
import com.chouchongkeji.dial.dao.backpack.item.DiscountingMapper;
import com.chouchongkeji.dial.dao.iwant.wallet.WalletMapper;
import com.chouchongkeji.dial.dao.iwant.wallet.WalletRecordMapper;
import com.chouchongkeji.dial.pojo.backpack.BpItem;
import com.chouchongkeji.dial.pojo.backpack.Vbp;
import com.chouchongkeji.dial.pojo.backpack.item.Discounting;
import com.chouchongkeji.dial.pojo.gift.virtualItem.AppMessage;
import com.chouchongkeji.dial.pojo.iwant.wallet.Wallet;
import com.chouchongkeji.dial.pojo.iwant.wallet.WalletRecord;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.utils.BigDecimalUtil;
import com.chouchongkeji.service.backpack.item.DiscountingService;
import com.chouchongkeji.service.iwant.wallet.WalletService;
import com.chouchongkeji.service.message.MessageService;
import com.chouchongkeji.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


/**
 * @author linqin
 * @date 2018/7/11
 */
@Service
public class DiscountingServiceImpl implements DiscountingService {

    @Autowired
    private VbpMapper vbpMapper;

    @Autowired
    private DiscountingMapper discountingMapper;

    @Autowired
    private BpItemMapper bpItemMapper;

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private WalletRecordMapper walletRecordMapper;

    @Autowired
    private AppMessageMapper appMessageMapper;

    @Autowired
    private MessageService messageService;

    /**
     * 背包物品折现
     *
     * @param userId 用户id
     * @param bpId   背包id
     * @return
     * @author linqin
     * @date 2018/7/11
     */
    @Override
    public Response addDiscountRecord(Integer userId, Long bpId) {
        //查出背包里的物品
        Vbp vbp = vbpMapper.selectByUserIdBpId(userId, bpId);
        if (vbp == null) {
            return ResponseFactory.err("背包里不存在该物品");
        }
        //优惠券不能折现
        if (vbp.getType() == Constants.BACKPACK_TYPE.DISCOUNT_COUPON) {
            return ResponseFactory.err("优惠券不能折现");
        }
        //数量大于0才能折现
        if (vbp.getQuantity() < 1) {
            return ResponseFactory.err("数量不足不能提现");
        }
        //添加折现记录
        Discounting discounting = new Discounting();
        discounting.setUserId(userId);
        discounting.setBpId(bpId);
        discounting.setItemPrice(vbp.getPrice());
        BigDecimal discountPrice = BigDecimalUtil.multi(vbp.getPrice().doubleValue(), Constants.DISCOUNT_RATE.DISCOUNTING);
        discountPrice = discountPrice.setScale(2, RoundingMode.DOWN);
        discounting.setDiscountPrice(discountPrice);
        discounting.setExplain("背包物品折现");
        discounting.setStatus(Constants.DISCOUNT_STATUS.DISCOUNTING);
        discounting.setType(vbp.getType());
        int insert = discountingMapper.insert(discounting);
        if (insert < 1) {
            return ResponseFactory.err("折现失败");
        }
        //减少背包里已经提货的物品
        BpItem bpItem = new BpItem();
        bpItem.setId(bpId);
        bpItem.setQuantity(vbp.getQuantity() - 1);
        //更新背包
        bpItemMapper.updateByPrimaryKeySelective(bpItem);
        //金额添加到余额，添加收益记录，添加系统消息,更新状态
        //把折后金额添加到余额里
        Wallet wallet = walletMapper.selectByPrimaryKey(userId);
        if (wallet == null){
            wallet = new Wallet();
            wallet.setUserId(userId);
            wallet.setBalance(discountPrice);
            wallet.setTotalAmount(discountPrice);
            wallet.setConsumeAmount(new BigDecimal(0));
            int i = walletMapper.insert(wallet);
            if (i<0){
                return ResponseFactory.err("余额添加失败");
            }
        }
        wallet.setBalance(BigDecimalUtil.add(discountPrice.doubleValue(),wallet.getBalance().doubleValue()));
        wallet.setTotalAmount(BigDecimalUtil.add(discountPrice.doubleValue(),wallet.getTotalAmount().doubleValue()));
        int update = walletMapper.updateByPrimaryKeySelective(wallet);
        if (update<0){
            return ResponseFactory.err("余额添加失败");
        }
        //添加系统消息
        AppMessage message = new AppMessage();
        message.setTitle("系统通知");
        message.setSummary("折现成功通知");
        message.setContent("您好，你的"+vbp.getTitle()+"物品折现金额"+discountPrice+"元，原价格"
                +vbp.getPrice()+"元，您的折现已成功到达账户，请前往余额查看");
        message.setTargetId(bpId);
        message.setTargetType((byte) 21);
        message.setMessageType((byte)2);

        int in = messageService.addMessage(message, new ArrayList<Integer>() {
            {
                add(userId);
            }
        });

//        int in = appMessageMapper.insert(message);
        if (in<1){
            throw new ServiceException(ErrorCode.ERROR.getCode(),"添加系统消息失败");
        }
        //添加收益记录
        WalletRecord record = new WalletRecord();
        record.setUserId(userId);
        Byte type = vbp.getType();
        if (type == 1){
            record.setExplain("商品折现");
            record.setType((byte)2);
        }else {
            record.setExplain("虚拟商品折现");
            record.setType((byte)3);
        }
        record.setAmount(discountPrice);
        record.setTargetId(bpId);
        int insert1 = walletRecordMapper.insert(record);
        if (insert1<1){
            throw new ServiceException(ErrorCode.ERROR.getCode(),"添加系统消息失败");
        }

        return ResponseFactory.sucMsg("折现成功");
    }
}
