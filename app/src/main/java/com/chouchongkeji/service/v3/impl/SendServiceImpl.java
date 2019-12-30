package com.chouchongkeji.service.v3.impl;

import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.dao.v3.*;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.dial.pojo.v3.*;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.goexplore.utils.BigDecimalUtil;
import com.chouchongkeji.service.backpack.gift.vo.SendWXVo;
import com.chouchongkeji.service.backpack.gift.vo.WXGetGiftResVo;
import com.chouchongkeji.service.v3.ChargeCardService;
import com.chouchongkeji.service.v3.MemberCardService;
import com.chouchongkeji.service.v3.SendService;
import com.chouchongkeji.service.v3.vo.SendVo;
import com.chouchongkeji.service.v3.vo.TransferSendVo;
import com.chouchongkeji.util.Constants;
import com.github.pagehelper.PageHelper;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linqin
 * @date 2019/12/2
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class SendServiceImpl implements SendService {

    @Autowired
    private UserMemberCardMapper userMemberCardMapper;

    @Autowired
    private MembershipCardMapper membershipCardMapper;

    @Autowired
    private StoreMemberChargeMapper storeMemberChargeMapper;

    @Autowired
    private TransferSendMapper transferSendMapper;

    @Autowired
    private TransferSendDetailMapper transferSendDetailMapper;

    @Autowired
    private TransferSendExpenseMapper transferSendExpenseMapper;

    @Autowired
    private ChargeCardService chargeCardService;

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private MemberCardService memberCardService;

    /**
     * 会员卡余额赠送
     *
     * @param userId    用户(赠送者)
     * @param cardId    卡片id
     * @param sendMoney 赠送金额
     * @return
     */
    @Override
    public Response cardSend(Integer userId, Integer cardId, BigDecimal sendMoney) {
        MembershipCard membershipCard = membershipCardMapper.selectByPrimaryKey(cardId);
        if (membershipCard == null){
            throw new ServiceException("该会员卡不存在");
        }
        if (membershipCard.getType() == 11){
            throw new ServiceException("活动卡不可以转赠");
        }
        // 查询金额是否足够
        UserMemberCard card = userMemberCardMapper.selectByCardIdUserId(userId, cardId);
        if (card == null) {
            throw new ServiceException("该会员信息不存在");
        }
        if (sendMoney.compareTo(card.getBalance()) > 0) {
            throw new ServiceException("余额不足");
        }
        //创建会员卡转赠记录
        TransferSend send = new TransferSend();
        send.setUserId(userId);
        send.setMembershipCardId(cardId);
        send.setSendMoney(sendMoney);
        send.setStatus(Constants.TRANSFER_SEND.WAIT);
        int insert = transferSendMapper.insert(send);
        if (insert < 1) {
            throw new ServiceException("创建会员卡转赠记录失败");
        }
        // 返回转赠记录id，用于分享给微信好友
        String title = membershipCard.getTitle();
        Map<String, Object> result = new HashMap<>();
        result.put("transferSendId", send.getId());
        result.put("title", title);
        return ResponseFactory.sucData(result);
    }


    /**
     * 判断是否可以领取会员卡
     *
     * @param userId         用户（接收者）
     * @param transferSendId 转赠记录id
     * @return
     */
    @Override
    public Response judgeCardSend(Integer userId, Integer transferSendId) {
        TransferSend send = transferSendMapper.selectByPrimaryKey(transferSendId);
        if (send == null) {
            return ResponseFactory.errMsg(666, "该会员卡转赠不存在或已被转赠者撤回!");
        }
        // 转赠的会员卡信息
        UserMemberCard card = userMemberCardMapper.selectByCardIdUserId(send.getUserId(), send.getMembershipCardId());
        if (card == null) {
            throw new ServiceException("该用户会员卡不存在");
        }
        MembershipCard membership = membershipCardMapper.selectByPrimaryKey(send.getMembershipCardId());
        if (membership == null) {
            throw new ServiceException("该会员卡不存在");
        }
        // 赠送者用户信息
        AppUser appUser = appUserMapper.selectByPrimaryKey(send.getUserId());
        SendVo vo = new SendVo();
        // 自己领取
        if (send.getUserId().equals(userId)) {
            vo.setTitle("你给别人的余额转赠");
            vo.setSendMoney(send.getSendMoney());
            if (send.getStatus() == Constants.TRANSFER_SEND.SEND) {
                vo.setSummary("已被对方接收");
            } else {
                vo.setSummary("等待对方接收");
            }
            vo.setDetail("来自" + card.getCardNo() + " " + membership.getTitle() + "账户");
            vo.setStatus((byte) 3);
            return ResponseFactory.sucData(vo);
        }
        if (send.getStatus() == Constants.TRANSFER_SEND.SEND) {
            TransferSendDetail detail = transferSendDetailMapper.selectBytransferSendIdUserId(transferSendId, userId);
            vo.setTitle("你收到来自" + appUser.getNickname() + "的余额转赠");
            vo.setSendMoney(send.getSendMoney());
            vo.setDetail("来自" + card.getCardNo() + " " + membership.getTitle() + "账户");
            if (detail != null) {
                vo.setSummary("已接收");
                vo.setStatus((byte) 2);
            } else {
                // 被别人领取
                vo.setSummary("已被接收");
                vo.setStatus((byte) 4);
            }
            return ResponseFactory.sucData(vo);
        } else {
            // 可以领取
            vo.setTitle("你收到来自" + appUser.getNickname() + "的余额转赠");
            vo.setSendMoney(send.getSendMoney());
            vo.setDetail("来自" + card.getCardNo() + " " + membership.getTitle() + "账户");
            vo.setSummary("接收");
            vo.setStatus((byte) 1);
            return ResponseFactory.sucData(vo);
        }
    }

    /**
     * 微信领取会员卡
     *
     * @param userId         用户（接收者）
     * @param transferSendId 转赠记录id
     * @return
     */
    @Override
    public Response getCardSend(Integer userId, Integer transferSendId) {
        TransferSend send = transferSendMapper.selectByPrimaryKey(transferSendId);
        if (send == null) {
            return ResponseFactory.errMsg(666, "该会员卡转赠不存在或已被转赠者撤回!");
        }
        Response response = judgeCardSend(userId, transferSendId);
        if (((SendVo) response.getData()).getStatus() != 1) {
            return response;
        }
        // 查询会员卡信息
        MembershipCard membership = membershipCardMapper.selectByPrimaryKey(send.getMembershipCardId());
        // 赠送者用户信息
        AppUser appUser = appUserMapper.selectByPrimaryKey(send.getUserId());
        // 领取会员卡
        // 1判断用户是否有该会员卡
        UserMemberCard card = userMemberCardMapper.selectByCardIdUserId(userId, send.getMembershipCardId());
        //查询原来用户的会员卡信息
        UserMemberCard card1 = userMemberCardMapper.selectByCardIdUserId(send.getUserId(), send.getMembershipCardId());
        if (card1 == null) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "原来用户的会员卡信息不存在");
        }
        if (card1.getBalance().compareTo(send.getSendMoney()) < 0){
            throw new ServiceException(ErrorCode.ERROR.getCode(), "转赠着余额不足");
        }
        if (card == null) {
            //添加新的会员卡，
            int i = memberCardService.addMemberShipCard(userId, send.getSendMoney(), send.getSendMoney(), new BigDecimal("0"),
                    send.getMembershipCardId(), card1.getStoreId());
            if (i == 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "添加会员卡失败");
            }
        } else {
            // 更新会员卡余额
            card.setBalance(BigDecimalUtil.add(card.getBalance().doubleValue(), send.getSendMoney().doubleValue()));
            card.setTotalAmount(BigDecimalUtil.add(card.getTotalAmount().doubleValue(), send.getSendMoney().doubleValue()));
            int i = userMemberCardMapper.updateByPrimaryKeySelective(card);
            if (i == 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新余额失败！");
            }
        }
        // 添加领取记录
        int detailId = addTransferSendDetail(userId, transferSendId, send.getMembershipCardId(), send.getSendMoney());
        // 添加转赠扣款关联表
        addTransferSendEx(send.getUserId(), send.getMembershipCardId(), transferSendId, send.getSendMoney(), userId, detailId);
        // 更新转赠记录
        send.setStatus(Constants.TRANSFER_SEND.SEND);
        int i = transferSendMapper.updateByPrimaryKeySelective(send);
        if (i == 0) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "更新转赠记录失败！");
        }
        // 更新赠送者余额
        card1.setBalance(BigDecimalUtil.sub(card1.getBalance().doubleValue(), send.getSendMoney().doubleValue()));
        i = userMemberCardMapper.updateByPrimaryKeySelective(card1);
        if (i == 0) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "赠送者更新余额失败！");
        }
        SendVo vo = new SendVo();
        vo.setTitle("你收到来自" + appUser.getNickname() + "的余额转赠");
        vo.setSendMoney(send.getSendMoney());
        if (membership != null) {
            vo.setDetail("来自" + card1.getCardNo() + " " + membership.getTitle() + "账户");
        }
        vo.setSummary("已接收");
        vo.setStatus((byte) 2);
        return ResponseFactory.sucData(vo);
    }

    /**
     * 添加领取记录
     *
     * @param userId         领取者id
     * @param transferSendId 转赠id
     * @param cardId         会员卡id
     * @param send           赠送金额
     */
    private int addTransferSendDetail(Integer userId, Integer transferSendId, Integer cardId, BigDecimal send) {
        TransferSendDetail detail = new TransferSendDetail();
        detail.setUserId(userId);
        detail.setTransferSendId(transferSendId);
        detail.setMembershipCardId(cardId);
        detail.setStatus(Constants.TRANSFER_SEND.SEND);
        detail.setSendMoney(send);
        int insert = transferSendDetailMapper.insert(detail);
        if (insert < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "失败！");
        }
        return detail.getId();
    }


    /**
     * 添加转赠扣款关联表
     *
     * @param userId           赠送着用户id
     * @param cardId           卡片id
     * @param transferSendId   转赠记录id
     * @param sendMoney        转赠金额
     * @param targetUserId     接受者用户id
     * @param transferDetailId 转赠记录详情id
     */
    private void addTransferSendEx(Integer userId, Integer cardId, Integer transferSendId,
                                   BigDecimal sendMoney, Integer targetUserId, Integer transferDetailId) {
        //取出之前充值的记录
        List<StoreMemberCharge> charges = storeMemberChargeMapper.selectByUserIdCardId(userId, cardId);
        if (!CollectionUtils.isEmpty(charges)) {
            BigDecimal send = sendMoney;
            for (StoreMemberCharge charge : charges) {
                //判断此次充值还剩余的余额
                if (charge.getBalance().compareTo(send) == 0) {
                    // 余额与消费金额相等,更新详细记录，添加消费营业额记录
                    BigDecimal sub = BigDecimalUtil.sub(charge.getBalance().doubleValue(), send.doubleValue());
                    chargeCardService.updateDetailCharge(charge.getId(), sub, (byte) 3);
                    addTransferSendExpense(transferSendId, send, charge.getId());
                    //添加门店金额详情表（转赠）
                    chargeCardService.addStoreMountDetail(targetUserId, charge.getMerchantId(), charge.getStoreId(), new BigDecimal("0"),
                            new BigDecimal("0"), new BigDecimal("0"), (byte) 3, "余额转赠", send, charge.getScale(), cardId, send, (byte) 1,
                            charge.getMemberEventId(), transferDetailId.longValue());
                    break;
                } else if (charge.getBalance().compareTo(send) > 0) {
                    //余额大于消费金额，更新详细记录，添加消费营业额记录
                    BigDecimal sub = BigDecimalUtil.sub(charge.getBalance().doubleValue(), send.doubleValue());
                    chargeCardService.updateDetailCharge(charge.getId(), sub, (byte) 2);
                    addTransferSendExpense(transferSendId, send, charge.getId());
                    //添加门店金额详情表（转赠）
                    chargeCardService.addStoreMountDetail(targetUserId, charge.getMerchantId(), charge.getStoreId(), new BigDecimal("0"),
                            new BigDecimal("0"), new BigDecimal("0"), (byte) 3, "余额转赠", send, charge.getScale(), cardId, send, (byte) 1,
                            charge.getMemberEventId(), transferDetailId.longValue());
                    break;
                } else {
                    //余额小于消费金额
                    //扣除第一次充值的余额后还不够的钱
                    BigDecimal sub = BigDecimalUtil.sub(send.doubleValue(), charge.getBalance().doubleValue());
                    chargeCardService.updateDetailCharge(charge.getId(), new BigDecimal("0"), (byte) 3);
                    addTransferSendExpense(transferSendId, charge.getBalance(), charge.getId());
                    //添加门店金额详情表（转赠）
                    chargeCardService.addStoreMountDetail(targetUserId, charge.getMerchantId(), charge.getStoreId(), new BigDecimal("0"),
                            new BigDecimal("0"), new BigDecimal("0"), (byte) 3, "余额转赠", send, charge.getScale(), cardId, send, (byte) 1,
                            charge.getMemberEventId(), transferDetailId.longValue());
                    send = sub;
                }
            }
        }
    }

    /**
     * 添加转赠扣款关联表
     *
     * @param transferSendId 转赠记录id
     * @param sendMoney      赠送金额
     * @param storeMemberId  门店金额详情id
     */
    private void addTransferSendExpense(Integer transferSendId, BigDecimal sendMoney, Integer storeMemberId) {
        TransferSendExpense expense = new TransferSendExpense();
        expense.setTransferSendId(transferSendId);
        expense.setStoreMemberId(storeMemberId);
        expense.setSendMoney(sendMoney);
        int insert = transferSendExpenseMapper.insert(expense);
        if (insert < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "失败");
        }
    }


    /**
     * 转赠记录列表
     *
     * @param userId 用户
     * @return
     */
    @Override
    public Response getCardSendList(Integer userId, Integer cardId, PageQuery page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<TransferSendVo> vos = transferSendMapper.selectByUserIdCardId(userId, cardId);
        if (!CollectionUtils.isEmpty(vos)) {
            for (TransferSendVo vo : vos) {
                vo.setTitle(vo.getTitle() + "转赠");
                if (StringUtils.isEmpty(vo.getNickname())) {
                    vo.setNickname("未接收");
                }
            }
        }
        return ResponseFactory.sucData(vos);
    }


    /**
     * 转赠记录详情
     *
     * @param transferSendId 转赠记录id
     * @return
     */
    @Override
    public Response getCardSendDetail(Integer userId, Integer transferSendId) {
        TransferSendVo vo = transferSendMapper.selectById(transferSendId);
        if (vo != null) {
            vo.setTitle(vo.getTitle() + "转赠");
            if (StringUtils.isEmpty(vo.getNickname())) {
                vo.setNickname("未接收");
            }
        }
        return ResponseFactory.sucData(vo);
    }
}
