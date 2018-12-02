package com.chouchongkeji.service.iwant.wallet.impl;

import com.chouchongkeji.dial.dao.iwant.wallet.BankDictMapper;
import com.chouchongkeji.dial.dao.iwant.wallet.UserBankCardMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.dial.pojo.iwant.wallet.BankDict;
import com.chouchongkeji.dial.pojo.iwant.wallet.UserBankCard;
import com.chouchongkeji.service.iwant.wallet.BankCardService;
import com.chouchongkeji.service.iwant.wallet.vo.UserBankCardVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    /**
     * 删除用户银行卡
     *
     * @param: [userId 用户id, id 银行卡id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/6
     */
    @Override
    public Response delUserBankCard(Integer userId, Integer id) {
        UserBankCard userBankCard = userBankCardMapper.selectByPrimaryKey(id);
        if (userBankCard != null) {
            userBankCard.setStatus((byte)2);
            userBankCardMapper.updateByPrimaryKey(userBankCard);
            return ResponseFactory.sucMsg("删除成功");
        } else {
            return ResponseFactory.err("删除失败");
        }
    }

    /**
     * 添加用户银行卡
     *
     * @param: [userId 用户id, userBankCardVo 银行卡信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/7
     */
    @Override
    public Response addUserBankCard(Integer userId, UserBankCardVo userBankCardVo) {
        UserBankCard userBankCard = new UserBankCard();
        userBankCard.setStatus((byte)1);
        userBankCard.setUserId(userId);
        userBankCard.setUpdated(new Date());
        userBankCard.setDepositBank(userBankCardVo.getDepositBank());
        userBankCard.setCreated(new Date());
        userBankCard.setCardNo(userBankCardVo.getCardNo());
        userBankCard.setCardHolder(userBankCardVo.getCardHolder());
        userBankCard.setBankId(userBankCardVo.getBankId());
        int count = userBankCardMapper.insert(userBankCard);
        if (count == 1) {
            return ResponseFactory.sucMsg("添加成功");
        }
        return ResponseFactory.err("添加失败");
    }


    /**
     * 修改银行卡
     *
     * @param: [details 用户详情, userBankCardVo 银行卡信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/7
     */
    @Override
    public Response updateBankCard(Integer userId, UserBankCard userBankCard) {
        //根据银行卡id查询信息
        UserBankCard card = userBankCardMapper.selectByPrimaryKey(userBankCard.getId());
        if (card == null){
            return ResponseFactory.err("该银行卡不存在");
        }
        // 查看银行卡是否是自己的
        if (!card.getUserId().equals(userId)){
            return ResponseFactory.err("该银行卡不是该用户的");
        }
        UserBankCard bankCard = new UserBankCard();
        bankCard.setUserId(userId);
        bankCard.setId(userBankCard.getId());
        if (userBankCard.getBankId()!=null){
            bankCard.setBankId(userBankCard.getBankId());
        }
        if (StringUtils.isNotBlank(userBankCard.getDepositBank())){
            bankCard.setDepositBank(userBankCard.getDepositBank());
        }
        if (StringUtils.isNotBlank(userBankCard.getCardHolder())){
            bankCard.setCardHolder(userBankCard.getCardHolder());
        }
        if (StringUtils.isNotBlank(userBankCard.getCardNo())){
            bankCard.setCardNo(userBankCard.getCardNo());
        }
        int re = userBankCardMapper.updateByPrimaryKeySelective(bankCard);
        if (re == 1) {
            return ResponseFactory.sucMsg("修改成功");
        }
        return ResponseFactory.err("修改失败");
    }
}
