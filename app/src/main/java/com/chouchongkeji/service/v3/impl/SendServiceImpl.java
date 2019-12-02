package com.chouchongkeji.service.v3.impl;

import com.chouchongkeji.dial.dao.v3.TransferSendDetailMapper;
import com.chouchongkeji.dial.dao.v3.TransferSendExpenseMapper;
import com.chouchongkeji.dial.dao.v3.TransferSendMapper;
import com.chouchongkeji.dial.dao.v3.UserMemberCardMapper;
import com.chouchongkeji.dial.pojo.v3.TransferSend;
import com.chouchongkeji.dial.pojo.v3.UserMemberCard;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.v3.SendService;
import com.chouchongkeji.util.Constants;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author linqin
 * @date 2019/12/2
 */
@Service
@Transactional(rollbackFor = Exception.class,isolation = Isolation.REPEATABLE_READ)
public class SendServiceImpl implements SendService {

    @Autowired
    private UserMemberCardMapper userMemberCardMapper;

    @Autowired
    private TransferSendMapper transferSendMapper;

    @Autowired
    private TransferSendDetailMapper transferSendDetailMapper;

    @Autowired
    private TransferSendExpenseMapper transferSendExpenseMapper;

    /**
     * 会员卡余额赠送
     * @param userDetails 用户(赠送者)
     * @param cardId 卡片id
     * @param sendMoney 赠送金额
     * @return
     */
    @Override
    public Response cardSend(UserDetails userDetails, Integer cardId, BigDecimal sendMoney) {
        // 查询金额是否足够
        UserMemberCard card = userMemberCardMapper.selectByCardIdUserId(userDetails.getUserId(), cardId);
        if (card == null){
            throw new ServiceException("该会员卡不存在");
        }
        if (sendMoney.compareTo(card.getBalance()) > 0){
            throw new ServiceException("余额不足");
        }
        //创建会员卡转赠记录
        TransferSend send = new TransferSend();
        send.setUserId(userDetails.getUserId());
        send.setMembershipCardId(cardId);
        send.setSendMoney(sendMoney);
        send.setStatus(Constants.TRANSFER_SEND.WAIT);
        int insert = transferSendMapper.insert(send);
        if (insert < 1){
            throw new ServiceException("创建会员卡转赠记录失败");
        }


    }
}
