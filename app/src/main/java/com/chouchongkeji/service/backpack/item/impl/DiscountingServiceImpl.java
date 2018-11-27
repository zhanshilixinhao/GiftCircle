package com.chouchongkeji.service.backpack.item.impl;

import com.chouchongkeji.dial.dao.backpack.BpItemMapper;
import com.chouchongkeji.dial.dao.backpack.VbpMapper;
import com.chouchongkeji.dial.dao.backpack.item.DiscountingMapper;
import com.chouchongkeji.dial.pojo.backpack.BpItem;
import com.chouchongkeji.dial.pojo.backpack.Vbp;
import com.chouchongkeji.dial.pojo.backpack.item.Discounting;
import com.chouchongkeji.dial.pojo.iwant.wallet.Wallet;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.utils.BigDecimalUtil;
import com.chouchongkeji.service.backpack.item.DiscountingService;
import com.chouchongkeji.service.iwant.wallet.WalletService;
import com.chouchongkeji.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


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
    private WalletService walletService;

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
        // 更新钱包余额
        walletService.updateBalance(userId,discountPrice);
        return ResponseFactory.sucMsg("折现成功");
    }
}
