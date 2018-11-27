package com.chouchongkeji.service.iwant.wallet.impl;

import com.chouchongkeji.dial.dao.iwant.wallet.WalletMapper;
import com.chouchongkeji.dial.dao.iwant.wallet.WalletRecordMapper;
import com.chouchongkeji.dial.pojo.iwant.wallet.Wallet;
import com.chouchongkeji.dial.pojo.iwant.wallet.WalletRecord;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.iwant.wallet.WalletService;
import com.chouchongkeji.util.Constants;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yy
 * @date 2018/6/5
 **/

@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private WalletRecordMapper walletRecordMapper;

    /**
     * 获取钱包详情
     *
     * @param userId 用户Id
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @Override
    public Response getWalletDetail(Integer userId) {
        //校验必传参数
        if (userId == null) {
            return ResponseFactory.errMissingParameter();
        }
        Wallet wallet = getWallet(userId);
        return ResponseFactory.sucData(wallet);
    }

    private Wallet getWallet(Integer userId) {
        //根用户Id查询钱包信息
        Wallet detail = walletMapper.selectByUserId(userId);
        if (detail != null) {
            return detail;
        }
        //如果钱包信息为空，插入一条数据
        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        wallet.setBalance(BigDecimal.valueOf(0));
        wallet.setTotalAmount(BigDecimal.valueOf(0));
        wallet.setConsumeAmount(BigDecimal.valueOf(0));
        walletMapper.insert(wallet);
        return wallet;
    }

    /**
     * 更新余额
     *
     * @param userId
     * @param amount
     */
    @Override
    public void updateBalance(Integer userId, BigDecimal amount) {
        //根据用户id取出钱包信息
        Wallet detail = getWallet(userId);
        //在钱包里查出余额
        BigDecimal balance = detail.getBalance();
        balance = balance.add(amount);
        //更新余额
        detail.setBalance(balance);
        walletMapper.updateByPrimaryKeySelective(detail);

    }

    /**
     * 扣减余额，更新余额
     *
     * @param userId 用户id
     * @param amount 变得金额
     * @param type   记录类型
     * @param targetId   目标id（订单id）
     * @return
     * @author linqin
     * @date 2018/7/3
     */
    public int updateBalance(Integer userId, BigDecimal amount, Constants.WALLET_RECORD type,Integer targetId) {
        //根据用户id取出钱包信息
        Wallet detail = getWallet(userId);
        //在钱包里查出余额
        BigDecimal balance = detail.getBalance();
        //根据记录类型增加/减少余额
        switch (type.type) {
            case 1:
            case 2:
            case 3:
            case 4:
                break;
            case 5:
            case 6:
                amount = amount.negate();
                break;
        }
        balance = balance.add(amount);
        if (balance.doubleValue()<0){
            throw new ServiceException(ErrorCode.ERROR.getCode(),"余额不足");
        }
        //更新余额
        detail.setBalance(balance);
        walletMapper.updateByPrimaryKeySelective(detail);
        //插入一条钱包使用记录
        WalletRecord record = new WalletRecord();
        record.setUserId(userId);
        record.setExplain(type.explain);
        record.setAmount(amount);
        record.setTargetId(Long.valueOf(targetId));
        record.setType((byte)type.type);
        walletRecordMapper.insert(record);
        return 1;
    }



    /**
     *钱包收益记录
     * @param userId 用户Id
     * @param starting 开始时间
     * @param ending 截至时间
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @Override
    public Response earnRecordList(Integer userId, PageQuery pageQuery,Long starting ,Long ending) {
        //分页
        PageHelper.startPage(pageQuery.getPageNum(),pageQuery.getPageSize());
        //查询收益记录
        List<WalletRecord> list = walletRecordMapper.selectByUserId(userId,starting,ending);
        return ResponseFactory.sucData(list);
    }
}
