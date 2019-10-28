package com.chouchongkeji.service.v3.impl;

import com.chouchongkeji.dial.dao.v3.MemberChargeRecordMapper;
import com.chouchongkeji.dial.dao.v3.MemberExpenseRecordMapper;
import com.chouchongkeji.dial.dao.v3.StoreMapper;
import com.chouchongkeji.dial.dao.v3.UserMemberCardMapper;
import com.chouchongkeji.dial.pojo.v3.MemberExpenseRecord;
import com.chouchongkeji.dial.pojo.v3.Store;
import com.chouchongkeji.dial.pojo.v3.UserMemberCard;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.goexplore.utils.BigDecimalUtil;
import com.chouchongkeji.service.v3.MemberCardService;
import com.chouchongkeji.service.v3.vo.*;
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
    public Response getMemberCardList(UserDetails userDetails, PageQuery page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        // 查询该用户是否有礼遇圈卡
        HashSet<Integer> cardIds = userMemberCardMapper.selectCardIdsByUserId(userDetails.getUserId());
        if (cardIds.size() == 0 || !cardIds.contains(0)){
         // 不包含0，没有礼遇圈卡则加一张
            addMemberShipCard(userDetails.getUserId());
        }
        List<CardVo> cardVos = userMemberCardMapper.selectByUserId(userDetails.getUserId());
        if (CollectionUtils.isNotEmpty(cardVos)) {
            // 添加店铺信息
            for (CardVo cardVo : cardVos) {
                List<Store> list = new ArrayList<>();
                if (StringUtils.isNotBlank(cardVo.getStoreIds())) {
                    String[] split = cardVo.getStoreIds().split(",");
                    for (String s : split) {
                        Store store = storeMapper.selectByPrimaryKey(Integer.parseInt(s));
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
    private int addMemberShipCard(Integer userId) {
        UserMemberCard card = new UserMemberCard();
        card.setMembershipCardId(0);
        card.setUserId(userId);
        card.setBalance(new BigDecimal("0"));
        card.setTotalAmount(new BigDecimal("0"));
        card.setConsumeAmount(new BigDecimal("0"));
        card.setStatus((byte) 1);
        card.setStoreId(0);
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
       if (vo != null){
           if (!vo.getUserId().equals(userDetails.getUserId())){
               return ResponseFactory.err("该会员卡不是该用户的");
           }
           List<Store> stores = new ArrayList<>();
           if (StringUtils.isNotBlank(vo.getStoreIds())) {
               String[] split = vo.getStoreIds().split(",");
               for (String s : split) {
                   Store store = storeMapper.selectByPrimaryKey(Integer.parseInt(s));
                   stores.add(store);
               }
           }
           vo.setStores(stores);
       }
       return ResponseFactory.sucData(vo);
    }


    /**
     * 会员卡充值记录
     * @param userDetails
     * @param id 会员卡id
     * @return
     * @author linqin
     * @date 2019/10/23
     */
    @Override
    public Response chargeRecordList(UserDetails userDetails, Integer id,PageQuery page) {
        List<ChargeListVo> listVos = memberChargeRecordMapper.selectByMembertCardId(userDetails.getUserId(),id);
        if (CollectionUtils.isNotEmpty(listVos)){
            for (ChargeListVo listVo : listVos) {
                if (listVo.getSendMoney() != null && listVo.getRechargeMoney() !=null){
                    listVo.setTotalAmount(BigDecimalUtil.add(listVo.getRechargeMoney().doubleValue(),listVo.getSendMoney().doubleValue()));
                } else if (listVo.getSendMoney() == null && listVo.getRechargeMoney() !=null){
                    listVo.setTotalAmount(listVo.getRechargeMoney());
                }else {
                    listVo.setTotalAmount(new BigDecimal("0"));
                }
            }
        }
        return ResponseFactory.sucData(listVos);
    }


    /**
     * 会员卡充值记录详情
     * @param userDetails
     * @param id 会员卡充值记录id
     * @return
     * @author linqin
     * @date 2019/10/23
     */
    @Override
    public Response chargeRecordDetail(UserDetails userDetails, Integer id) {
        ChargeDetailVo detailVo = memberChargeRecordMapper.selectByKeyUserId(id,userDetails.getUserId());
        if (detailVo != null){
            detailVo.setTitle("会员卡充值");
            if (detailVo.getType() == 1){
                detailVo.setAddress("APP");
            }
            if (detailVo.getSendMoney() != null && detailVo.getRechargeMoney() !=null){
                detailVo.setTotalAmount(BigDecimalUtil.add(detailVo.getRechargeMoney().doubleValue(),detailVo.getSendMoney().doubleValue()));
            } else if (detailVo.getSendMoney() == null && detailVo.getRechargeMoney() !=null){
                detailVo.setTotalAmount(detailVo.getRechargeMoney());
            }else {
                detailVo.setTotalAmount(new BigDecimal("0"));
            }
        }
        return ResponseFactory.sucData(detailVo);
    }

    /**
     * 会员卡消费记录
     * @param userId
     * @param id 会员卡id
     * @param page
     * @return
     */
    @Override
    public Response expenseRecordList(Integer userId, Integer id, PageQuery page) {
     List<ExpenseListVo> listVos = memberExpenseRecordMapper.selectByIdUserId(userId,id);
     return ResponseFactory.sucData(listVos);
    }


    /**
     * 会员卡消费记录详情
     * @param userId
     * @param id 会员卡消费记录id
     * @return
     */
    @Override
    public Response expenseRecordDetail(Integer userId, Integer id) {
      ExpenseDetailVo detail = memberExpenseRecordMapper.selectByKeyUserId(userId,id);
        if (detail != null){
            if (detail.getType() == 1){
                detail.setAddress("APP");
            }
            if (detail.getTargetId() == null){
                detail.setTitle("会员卡线下消费");
            }
        }
      return ResponseFactory.sucData(detail);
    }

}
