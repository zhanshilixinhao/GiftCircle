package com.chouchongkeji.service.iwant.wallet.impl;

import com.chouchongkeji.dial.dao.backpack.BpItemMapper;
import com.chouchongkeji.dial.dao.iwant.wallet.UserBankCardMapper;
import com.chouchongkeji.dial.dao.iwant.wallet.UserWithdrawMapper;
import com.chouchongkeji.dial.dao.iwant.wallet.WalletMapper;
import com.chouchongkeji.dial.dao.user.memo.WXDiscountMapper;
import com.chouchongkeji.dial.pojo.backpack.BpItem;
import com.chouchongkeji.dial.pojo.user.memo.WXDiscount;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.dial.pojo.iwant.wallet.UserBankCard;
import com.chouchongkeji.dial.pojo.iwant.wallet.UserWithdraw;
import com.chouchongkeji.dial.pojo.iwant.wallet.Wallet;
import com.chouchongkeji.goexplore.utils.BigDecimalUtil;
import com.chouchongkeji.service.iwant.wallet.BankCardService;
import com.chouchongkeji.service.iwant.wallet.UserWithdrawService;
import com.chouchongkeji.service.iwant.wallet.vo.UserBankCardVo;
import com.chouchongkeji.service.iwant.wallet.vo.UserBankVo2;
import com.chouchongkeji.service.iwant.wallet.vo.UserWithdrawVo;
import com.chouchongkeji.service.iwant.wallet.vo.WXDiscountVo;
import com.chouchongkeji.util.Constants;
import com.github.pagehelper.PageHelper;
import com.yichen.auth.redis.MRedisTemplate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * @author yy
 * @date 2018/6/5
 **/

@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class UserWithdrawServiceImpl implements UserWithdrawService {
    @Autowired
    private UserWithdrawMapper userWithdrawMapper;

    @Autowired
    private UserBankCardMapper userBankCardMapper;

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private BpItemMapper bpItemMapper;

    @Autowired
    private WXDiscountMapper wxDiscountMapper;

    @Autowired
    private MRedisTemplate mRedisTemplate;

    /**
     * 添加用户提现记录
     *
     * @param: [userId 用户id, id 用户银行卡id, amount 提现金额]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/8
     */
    @Override
    public Response addUserWithdraw(Integer userId, Integer id, BigDecimal amount) {
        String string = mRedisTemplate.getString("apply" + userId);
        if (StringUtils.isNotBlank(string)){
            return ResponseFactory.err("您已提交过申请,请稍后再试!");
        }
        mRedisTemplate.setString("apply" + userId,"s",60);
        // 取出用户银行卡信息
        UserBankCard userBankCard = userBankCardMapper.selectByBankId(id);
        // 是否存在
        if (userBankCard == null) {
            return ResponseFactory.err("提现的银行卡不存在，请先添加银行卡!");
        }
        // 用户是否匹配当前银行卡
        if (!userId.equals(userBankCard.getUserId())) {
            return ResponseFactory.err("银行卡和用户不匹配!");
        }
        // 把原来提现的银行卡设为不是默认
        userBankCardMapper.updateIsDefault(userId);
        // 把本次提现的银行卡设为默认
        userBankCard.setIsDefault((byte) 1);
        userBankCardMapper.updateByPrimaryKeySelective(userBankCard);
        // 查看余额是否足够
        Wallet wallet = walletMapper.selectByUserId(userId);
        if (wallet == null) {
            return ResponseFactory.err("提现失败!");
        }
        if (wallet.getBalance().compareTo(amount) < 0) {
            return ResponseFactory.err("余额不足,提现失败!");
        }
        // 保存提现记录
        UserWithdraw userWithdraw = new UserWithdraw();
        userWithdraw.setAmount(amount);
        userWithdraw.setUserId(userId);
        userWithdraw.setUserBankcardId(id);
        userWithdraw.setUpdated(new Date());
        userWithdraw.setStatus((byte) 1);
        userWithdraw.setDescribe("");
        userWithdraw.setCreated(new Date());

        int count = userWithdrawMapper.insert(userWithdraw);
        if (count == 1) {
            wallet.setBalance(amount);
            int i = walletMapper.updateBalance(wallet);
            if (i < 1) {
                throw new ServiceException("申请提现失败");
            }
            return ResponseFactory.sucMsg("提现申请成功!");
        }
        throw new ServiceException("提现申请失败!");
    }


    /**
     * 小程序背包物品折现
     *
     * @param: [details 用户认证信息, bpId 背包id, bankCard 银行信息 ]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/8
     */
    @Override
    public Response addWXBpWithdraw(Integer userId, Long bpId, Byte type, UserBankVo2 bankCard) {
        //根据背包商品id和用户id取出信息
        BpItem bpItem = bpItemMapper.selectByUserIdAndBpItemId(userId, bpId);
        if (bpItem == null) {
            return ResponseFactory.err("背包里不存在该商品");
        }
        if (bpItem.getType() == Constants.BACKPACK_TYPE.DISCOUNT_COUPON) {
            return ResponseFactory.err("优惠券不能折现!");
        }
        //判断数量是否大于0
        if (bpItem.getQuantity() < 1) {
            return ResponseFactory.err("商品数量不足");
        }
        /// 保存
        WXDiscount discount = new WXDiscount();
        discount.setBankId(bankCard.getBankId());
        discount.setBpId(bpId);
        discount.setCardHolder(bankCard.getCardHolder());
        discount.setCardNo(bankCard.getCardNo());
        discount.setDepositBank(bankCard.getDepositBank());
        discount.setType(type);
        discount.setPrice(bpItem.getPrice());
        BigDecimal discountPrice = BigDecimalUtil.multi(bpItem.getPrice().doubleValue(), Constants.DISCOUNT_RATE.DISCOUNTING);
        discountPrice = discountPrice.setScale(2, RoundingMode.DOWN);
        discount.setAmount(discountPrice);
        discount.setUserId(userId);
        discount.setStatus((byte) 1);
        discount.setPhone(bankCard.getPhone());
        int count = wxDiscountMapper.insert(discount);
        if (count < 1) {
            return ResponseFactory.err("提现申请失败!");
        }
        //减少背包里已经提货的物品
        bpItem.setQuantity(bpItem.getQuantity() - 1);
        //更新背包
        bpItemMapper.updateByPrimaryKeySelective(bpItem);
        return ResponseFactory.sucMsg("提现申请成功!");
    }

    /**
     * 微信折现 记录
     *
     * @param userId
     * @return
     */
    @Override
    public Response getWxRecords(Integer userId) {
        List<WXDiscountVo> wxDiscountVos = wxDiscountMapper.selectByUserId(userId);
        return ResponseFactory.sucData(wxDiscountVos);
    }

    /**
     * 获得用户的提现记录
     *
     * @param: [userId 用户id, pageQuery 分页信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/11
     */
    @Override
    public Response getUserWithdrawList(Integer userId, PageQuery page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<UserWithdrawVo> userWithdraws = userWithdrawMapper.selectByUserId(userId);
//        for (UserWithdrawVo userWithdraw : userWithdraws) {
//            if (userWithdraw.getStatus() == Constants.WIDTH_STATUS.APPLY || userWithdraw.getStatus() == Constants.WIDTH_STATUS.SUCCESS){
//               BigDecimal amount = BigDecimalUtil.multi(userWithdraw.getAmount().doubleValue(),-1);
//                userWithdraw.setAmount(amount);
//            }
//
//        }
        return ResponseFactory.sucData(userWithdraws);
    }
}
