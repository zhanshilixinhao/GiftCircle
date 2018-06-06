package com.chouchongkeji.service.iwant.wallet.impl;

import com.chouchongkeji.dao.iwant.wallet.BankDictMapper;
import com.chouchongkeji.dao.iwant.wallet.UserBankCardMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.pojo.iwant.wallet.BankDict;
import com.chouchongkeji.service.iwant.wallet.BankCardService;
import com.chouchongkeji.service.iwant.wallet.vo.UserBankCardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yy
 * @date 2018/6/5
 **/

@Service
public class BankCardServiceImpl implements BankCardService {
    @Autowired
    private BankDictMapper bankDictMapper;

    @Autowired
    private UserBankCardMapper userBankCardMapper;

    /**
     * 获得可用的银行
     *
     * @param: []
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/5
     */
    @Override
    public Response getBankList() {
        Integer status = 1;
        // 根据银行状态查找银行列表 1.正常 2.禁用
        List<BankDict> bankDicts = bankDictMapper.selectByStatus(status);
        return ResponseFactory.sucData(bankDicts);
    }

    /**
     * 获得用户的所有银行卡列表
     *
     * @param: [userId 用户id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/5
     */
    @Override
    public Response getUserBankCardList(Integer userId) {
        Integer status = 1;
        // 根据银行卡状态查找用户银行卡 1.正常 2.删除
        List<UserBankCardVo> userBankCardVos = userBankCardMapper.getUserBankCardList(userId, status);
        return ResponseFactory.sucData(userBankCardVos);
    }
}
