package com.chouchongkeji.service.iwant.wallet.impl;

import com.chouchongkeji.dao.iwant.wallet.UserBankCardMapper;
import com.chouchongkeji.dao.iwant.wallet.UserWithdrawMapper;
import com.chouchongkeji.dao.iwant.wallet.WalletMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.pojo.iwant.wallet.UserBankCard;
import com.chouchongkeji.pojo.iwant.wallet.UserWithdraw;
import com.chouchongkeji.pojo.iwant.wallet.Wallet;
import com.chouchongkeji.service.iwant.wallet.UserWithdrawService;
import com.chouchongkeji.service.iwant.wallet.vo.UserWithdrawVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author yy
 * @date 2018/6/5
 **/

@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class UserWithdrawServiceImpl implements UserWithdrawService{
    @Autowired
    private UserWithdrawMapper userWithdrawMapper;

    @Autowired
    private UserBankCardMapper userBankCardMapper;

    @Autowired
    private WalletMapper walletMapper;

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
        // 取出用户银行卡信息
        UserBankCard userBankCard = userBankCardMapper.selectByPrimaryKey(id);
        // 是否存在
        if (userBankCard == null) {
            return ResponseFactory.err("提现的银行卡不存在，请先添加银行卡!");
        }
        // 用户是否匹配当前银行卡
        if (!userId.equals(userBankCard.getUserId())) {
            return ResponseFactory.err("银行卡和用户不匹配!");
        }
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
        userWithdraw.setStatus((byte)1);
        userWithdraw.setDescribe("");
        userWithdraw.setCreated(new Date());

        int count = userWithdrawMapper.insert(userWithdraw);
        if (count == 1) {
            wallet.setBalance(wallet.getBalance().subtract(amount));
            walletMapper.updateByPrimaryKey(wallet);
            return ResponseFactory.sucMsg("提现申请成功!");
        }
        return ResponseFactory.err("提现申请失败!");
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
        return ResponseFactory.sucData(userWithdraws);
    }
}
