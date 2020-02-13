package com.chouchongkeji.service.v3.impl;

import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.dao.v3.*;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.dial.pojo.v3.MemberExpenseRecord;
import com.chouchongkeji.dial.pojo.v3.Store;
import com.chouchongkeji.dial.pojo.v3.StoreMemberEvent;
import com.chouchongkeji.dial.pojo.v3.UserMemberCard;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.goexplore.utils.AESUtils;
import com.chouchongkeji.goexplore.utils.BigDecimalUtil;
import com.chouchongkeji.goexplore.utils.Utils;
import com.chouchongkeji.properties.ServiceProperties;
import com.chouchongkeji.service.v3.MemberCardService;
import com.chouchongkeji.service.v3.vo.*;
import com.chouchongkeji.util.OrderHelper;
import com.github.pagehelper.PageHelper;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author linqin
 * @date 2019/10/23
 */
@Service
public class MemberCardServiceImpl implements MemberCardService {

    @Autowired
    private UserMemberCardMapper userMemberCardMapper;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private MemberChargeRecordMapper memberChargeRecordMapper;

    @Autowired
    private MemberExpenseRecordMapper memberExpenseRecordMapper;

    @Autowired
    private StoreMemberEventMapper storeMemberEventMapper;

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private ServiceProperties serviceProperties;

    @Autowired
    private OrderHelper orderHelper;

    /**
     * 获取用户会员卡列表
     *
     * @param userDetails
     * @param page
     * @return
     * @author linqin
     * @date 2019/10/23
     */
    @Override
    public Response getMemberCardList(UserDetails userDetails, PageQuery page, String keywords) {
        // 查询该用户是否有礼遇圈卡
        HashSet<Integer> cardIds = userMemberCardMapper.selectCardIdsByUserId(userDetails.getUserId());
        if (cardIds.size() == 0 || !cardIds.contains(0)) {
            // 不包含0，没有礼遇圈卡则加一张
            addMemberShipCard(userDetails.getUserId(), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), 0, 0);
        }
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<CardVo> cardVos = userMemberCardMapper.selectByUserId(userDetails.getUserId(), keywords);
        if (CollectionUtils.isNotEmpty(cardVos)) {
            // 添加店铺信息
            for (CardVo cardVo : cardVos) {
                cardVo.setSummary(serviceProperties.getCardDetail() + cardVo.getMembershipCardId());
                List<Store> list = new ArrayList<>();
                if (StringUtils.isNotBlank(cardVo.getStoreIds())) {
                    String[] split = cardVo.getStoreIds().split(",");
                    for (String s : split) {
                        Store store = storeMapper.selectByPrimaryKey(Integer.parseInt(s));
                        if (store != null) {
                            String s1 = store.getArea().replaceAll(",", "");
                            store.setArea(s1);
                        }
                        list.add(store);
                    }
                }
                cardVo.setStores(list);
            }
        }
        return ResponseFactory.sucData(cardVos);
    }


    /**
     * 给用户添加礼遇圈卡 （礼遇圈卡storeId也为0）
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public int addMemberShipCard(Integer userId, BigDecimal balance, BigDecimal total, BigDecimal consume,
                                 Integer cardId, Integer storeId) {
        String phone = null;
        String password = null;
        AppUser appUser = appUserMapper.selectByPrimaryKey(userId);
        if (appUser != null) {
            phone = appUser.getPhone();
            password = Utils.toMD5(phone + Utils.toMD5(phone.substring(phone.length() - 6)));
        }
        UserMemberCard card = new UserMemberCard();
        card.setMembershipCardId(cardId);
        card.setUserId(userId);
        card.setBalance(balance);
        card.setTotalAmount(total);
        card.setConsumeAmount(consume);
        card.setStatus((byte) 1);
        card.setStoreId(storeId);
        card.setPhone(phone);
        card.setCardNo(orderHelper.genOrderNo(7, 9));
        card.setPassword(password);
        return userMemberCardMapper.insert(card);
    }


    /**
     * 会员卡详情
     *
     * @param userDetails
     * @param id          用户会员卡关联id
     * @return
     * @author linqin
     * @date 2019/10/23
     */
    @Override
    public Response detailMemberCard(UserDetails userDetails, Integer id) {
        CardVo vo = userMemberCardMapper.selectByKey(id);
        if (vo != null) {
            if (!vo.getUserId().equals(userDetails.getUserId())) {
                return ResponseFactory.err("该会员卡不是该用户的");
            }
            List<Store> stores = new ArrayList<>();
            if (StringUtils.isNotBlank(vo.getStoreIds())) {
                String[] split = vo.getStoreIds().split(",");
                for (String s : split) {
                    Store store = storeMapper.selectByPrimaryKey(Integer.parseInt(s));
                    if (store != null) {
                        String s1 = store.getArea().replaceAll(",", "");
                        store.setArea(s1);
                    }
                    stores.add(store);
                }
            }
            vo.setStores(stores);
            vo.setSummary(serviceProperties.getCardDetail() + vo.getMembershipCardId());
            BigDecimal capital1 = new BigDecimal("0");
            BigDecimal send1 = new BigDecimal("0");
            if (vo.getType() == 11) {
                // 活动卡本金和赠送金额
                List<StoreMemberEvent> capitals = storeMemberEventMapper.selectByUserIdCardId(vo.getUserId(), vo.getMembershipCardId());
                if (CollectionUtils.isNotEmpty(capitals)) {
                    for (StoreMemberEvent capital : capitals) {
                        capital1 = BigDecimalUtil.add(capital1.doubleValue(), capital.getCapitalBalance().doubleValue());
                    }
                }
                List<StoreMemberEvent> sends = storeMemberEventMapper.selectByUserIdCardId1(vo.getUserId(), vo.getMembershipCardId());
                if (CollectionUtils.isNotEmpty(sends)) {
                    for (StoreMemberEvent send : sends) {
                        send1 = BigDecimalUtil.add(send1.doubleValue(), send.getSendBalance().doubleValue());
                    }
                }
            }
            vo.setCode(AESUtils.encrypt("zheshishenmemima",
                    String.format("%s,%s", vo.getId(), System.currentTimeMillis())));
            vo.setCapital(capital1);
            vo.setSend(send1);
        }
        return ResponseFactory.sucData(vo);
    }


    /**
     * 会员卡充值记录
     *
     * @param userDetails
     * @param id          会员卡id
     * @return
     * @author linqin
     * @date 2019/10/23
     */
    @Override
    public Response chargeRecordList(UserDetails userDetails, Integer id, PageQuery page) {
        List<ChargeListVo> listVos = memberChargeRecordMapper.selectByMembertCardId(userDetails.getUserId(), id);
        if (CollectionUtils.isNotEmpty(listVos)) {
            for (ChargeListVo listVo : listVos) {
                if (listVo.getSendMoney() != null && listVo.getRechargeMoney() != null) {
                    listVo.setTotalAmount(BigDecimalUtil.add(listVo.getRechargeMoney().doubleValue(), listVo.getSendMoney().doubleValue()));
                } else if (listVo.getSendMoney() == null && listVo.getRechargeMoney() != null) {
                    listVo.setTotalAmount(listVo.getRechargeMoney());
                } else {
                    listVo.setTotalAmount(new BigDecimal("0"));
                }
            }
        }
        return ResponseFactory.sucData(listVos);
    }


    /**
     * 会员卡充值记录详情
     *
     * @param userDetails
     * @param id          会员卡充值记录id
     * @return
     * @author linqin
     * @date 2019/10/23
     */
    @Override
    public Response chargeRecordDetail(UserDetails userDetails, Integer id) {
        ChargeDetailVo detailVo = memberChargeRecordMapper.selectByKeyUserId(id, userDetails.getUserId());
        if (detailVo != null) {
            detailVo.setTitle("会员卡充值");
            if (detailVo.getType() == 1) {
                detailVo.setAddress("APP");
            }
            if (detailVo.getSendMoney() != null && detailVo.getRechargeMoney() != null) {
                detailVo.setTotalAmount(BigDecimalUtil.add(detailVo.getRechargeMoney().doubleValue(), detailVo.getSendMoney().doubleValue()));
            } else if (detailVo.getSendMoney() == null && detailVo.getRechargeMoney() != null) {
                detailVo.setTotalAmount(detailVo.getRechargeMoney());
            } else {
                detailVo.setTotalAmount(new BigDecimal("0"));
            }
            if (detailVo.getBeforeMoney() == null) {
                detailVo.setBeforeMoney(new BigDecimal("0"));
            }
            detailVo.setLaterMoney(BigDecimalUtil.add(detailVo.getBeforeMoney().doubleValue(), detailVo.getTotalAmount().doubleValue()));
        }
        return ResponseFactory.sucData(detailVo);
    }

    /**
     * 会员卡消费记录
     *
     * @param userId
     * @param id     会员卡id
     * @param page
     * @return
     */
    @Override
    public Response expenseRecordList(Integer userId, Integer id, PageQuery page) {
        List<ExpenseListVo> listVos = memberExpenseRecordMapper.selectByIdUserId(userId, id);
        return ResponseFactory.sucData(listVos);
    }


    /**
     * 会员卡消费记录详情
     *
     * @param userId
     * @param id     会员卡消费记录id
     * @return
     */
    @Override
    public Response expenseRecordDetail(Integer userId, Integer id) {
        ExpenseDetailVo detail = memberExpenseRecordMapper.selectByKeyUserId(userId, id);
        if (detail != null) {
            if (detail.getType() == 1) {
                detail.setAddress("APP");
            }
            if (detail.getTargetId() == null) {
                detail.setTitle("线下用餐");
            }
            //扣款前/后余额
            if (detail.getBeforeMoney() == null) {
                detail.setBeforeMoney(new BigDecimal("0"));
            }
            detail.setLaterMoney(BigDecimalUtil.sub(detail.getBeforeMoney().doubleValue(), detail.getExpenseMoney().doubleValue()));
            // 本金扣款和赠送金额扣款
            StoreMemberEvent event = storeMemberEventMapper.selectByUserIdCardIdOrderNo(userId, detail.getMembershipCardId(), detail.getOrderNo());
            if (event != null) {
                detail.setCapital(event.getCapitalMoney());
                detail.setSend(event.getSendMoney());
            }
        }
        return ResponseFactory.sucData(detail);
    }


    /**
     * 会员卡修改密码
     * @param userId 用户
     * @param cardId 会员卡id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    @Override
    public Response updateCardPassword(Integer userId, Integer cardId, String oldPassword, String newPassword) {
        UserMemberCard card = userMemberCardMapper.selectByCardIdUserId(userId, cardId);
        if (card == null){
            return ResponseFactory.err("该会员卡不存在");
        }
        // 校验旧密码
        String phone = card.getPhone();
        String old = Utils.toMD5(phone + oldPassword);
        if (!card.getPassword().equals(old)){
            return ResponseFactory.err("旧密码输入错误");
        }
        // 更新密码
        card.setPassword(Utils.toMD5(phone + newPassword));
        int i = userMemberCardMapper.updateByPrimaryKeySelective(card);
        if (i < 1){
            return ResponseFactory.err("密码修改失败");
        }
        return ResponseFactory.sucMsg("密码修改成功");
    }

    /**
     * 会员卡找回密码
     * @param userId 用户
     * @param cardId 会员卡id
     * @param newPassword 新密码
     * @return
     */
    @Override
    public Response findCardPassword(Integer userId, Integer cardId, String newPassword) {
        UserMemberCard card = userMemberCardMapper.selectByCardIdUserId(userId, cardId);
        if (card == null){
            return ResponseFactory.err("该会员卡不存在");
        }
        // 更新密码
        card.setPassword(Utils.toMD5(card.getPhone() + newPassword));
        int i = userMemberCardMapper.updateByPrimaryKeySelective(card);
        if (i < 1){
            return ResponseFactory.err("密码更新失败");
        }
        return ResponseFactory.sucMsg("密码更新成功");
    }


}
