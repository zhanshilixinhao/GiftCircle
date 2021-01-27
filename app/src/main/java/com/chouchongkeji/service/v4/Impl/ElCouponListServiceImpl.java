package com.chouchongkeji.service.v4.Impl;

import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.dao.user.ThirdAccountMapper;
import com.chouchongkeji.dial.dao.v3.ElUserCouponMapper;
import com.chouchongkeji.dial.dao.v3.MembershipCardMapper;
import com.chouchongkeji.dial.dao.v3.UserMemberCardMapper;
import com.chouchongkeji.dial.dao.v4.*;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.dial.pojo.user.ThirdAccount;
import com.chouchongkeji.dial.pojo.v3.ElUserCoupon;
import com.chouchongkeji.dial.pojo.v3.ElectronicCoupons;
import com.chouchongkeji.dial.pojo.v3.MembershipCard;
import com.chouchongkeji.dial.pojo.v3.UserMemberCard;
import com.chouchongkeji.dial.pojo.v4.*;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.v4.ElCouponListService;
import com.chouchongkeji.service.wxapi.WXCodeApi;
import com.chouchongkeji.util.OrderHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.chouchongkeji.dial.pojo.v4.ElCouponListCoupon.COLUMN_EL_COUPON_ID;
import static com.chouchongkeji.dial.pojo.v4.ElUserCouponList.ADMIN_ID;
import static com.chouchongkeji.dial.pojo.v4.ShareCoupon.STATUS;
import static com.chouchongkeji.dial.pojo.v4.ShareCoupon.TYPE;

/**
 * @description:
 * @author: LxH
 * @time: 2020/10/17 0017 下午 3:42
 */
@Service
public class ElCouponListServiceImpl implements ElCouponListService {

    @Resource
    private ElCouponListMapper elCouponListMapper;

    @Resource
    private SecuritiesMapper securitiesMapper;

    @Resource
    private ElCouponListCouponMapper elCouponListCouponMapper;

    @Resource
    private ElUserCouponV4Mapper elUserCouponV4Mapper;

    @Resource
    private ElUserCouponListMapper elUserCouponListMapper;

    @Resource
    private AppUserMapper appUserMapper;

    @Resource
    private OrderHelper orderHelper;

    @Resource
    private ThirdAccountMapper thirdAccountMapper;

    @Resource
    private ElCouponListOrderMapper elCouponListOrderMapper;

    @Resource
    private UserMemberCardMapper userMemberCardMapper;

    @Resource
    private MembershipCardMapper membershipCardMapper;

    @Resource
    private ElCouponListTypeMapper elCouponListTypeMapper;

    /**
     * @param: userId
     * @param: elCouponListId
     * @param: thePaid
     * @description: 用户领取礼包
     * @author: LxH
     * @time: 2020/10/17 0017 下午 3:51
     */
    @Override
    @Transactional
    public Response bindElCouponListByUser(Integer userId, Integer elCouponListId, Byte thePaid) {
        ElCouponList elCouponList = elCouponListMapper.selectByPrimaryKey(elCouponListId);
        if (elCouponList ==null) {
            return ResponseFactory.err("礼包不存在");
        }
        if (elCouponList.getThePaid() != thePaid) {
            return ResponseFactory.err("礼包不匹配");
        }
        //取出用户信息
        AppUser appUser = appUserMapper.selectByPrimaryKey(userId);
        //无偿礼包
        if (thePaid == 0) {
            giveElCouponList(appUser,elCouponList);
            elCouponListTypeByUser(userId,elCouponListId);
            return ResponseFactory.sucMsg("用户领取礼包成功!");
        //有偿礼包
        } else if (thePaid == 1){
            String openId = null;
            //支付礼包
            List<ThirdAccount> thirdAccounts = thirdAccountMapper.selectByPhone(appUser.getPhone());
            thirdAccounts.removeIf(thirdAccount -> thirdAccount.getType() == 1);
            for (ThirdAccount thirdAccount : thirdAccounts) {
                openId = thirdAccount.getOpenId();
            }
            //创建用户礼包充值订单
            ElCouponListOrder elCouponListOrder = new ElCouponListOrder();
            Long aLong = orderHelper.genOrderNo(4, 12);
            elCouponListOrder.setId(aLong).setUserId(userId).setElCouponListId(elCouponListId).setQuantity(1).
                    setPrice(elCouponList.getPrice()).setStatus((byte) 1).setCreated(new Date()).setUpdated(new Date());
            elCouponListOrderMapper.insertSelective(elCouponListOrder);
            //绑定礼包给用户
            giveElCouponList(appUser,elCouponList);
            elCouponListTypeByUser(userId,elCouponListId);
            //微信小程序支付
            return appletsPay(elCouponListOrder,openId);
        }
        return null;
    }

    private void elCouponListTypeByUser(Integer userId, Integer elCouponListId) {
        ElCouponListType type = new ElCouponListType();
        type.setUserId(userId).setElCouponListId(elCouponListId);
        elCouponListTypeMapper.insert(type);
    }

    /**
     * @param： userMemberCardId
     * @Description: 获取获取礼包
     * @Author: LxH
     * @Date: 2020/10/22 16:23
     */
    @Override
    public Response getStore(Integer userMemberCardId) {
        UserMemberCard userMemberCard = userMemberCardMapper.selectByPrimaryKey(userMemberCardId);
        if (userMemberCard ==null) {
            return ResponseFactory.err("用户没有这张卡");
        }
        MembershipCard membershipCard = membershipCardMapper.selectByPrimaryKey(userMemberCard.getMembershipCardId());
        if (membershipCard == null) {
            return ResponseFactory.err("没有这张卡");
        }
        Example example = new Example(ElCouponList.class);
        example.createCriteria().andEqualTo(ADMIN_ID, membershipCard.getAdminId()).
                andEqualTo(TYPE, 1).andEqualTo(STATUS, 1);
        ElCouponList elCouponList = elCouponListMapper.selectOneByExample(example);
        if (elCouponList == null) {
            return ResponseFactory.err("没有对应的礼包！");
        }
        Example e = new Example(ElCouponListCoupon.class);
        e.createCriteria().andEqualTo(COLUMN_EL_COUPON_ID, elCouponList.getId());
        List<ElCouponListCoupon> elCouponListCoupons = elCouponListCouponMapper.selectByExample(e);
        for (ElCouponListCoupon elCouponListCoupon : elCouponListCoupons) {
            Securities securities = securitiesMapper.selectByPrimaryKey(elCouponListCoupon.getCouponId());
            elCouponListCoupon.setCouponName(securities.getTitle()).setLogo("https://liyuquan.cn/static"+securities.getLogo());
            if (securities.getDay()!=null) {
                elCouponListCoupon.setStartTime(new Date());
                elCouponListCoupon.setDay(securities.getDay());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(elCouponListCoupon.getStartTime());
                calendar.add(Calendar.DATE,securities.getDay());
                elCouponListCoupon.setEndTime(calendar.getTime());
            }else {
                elCouponListCoupon.setStartTime(securities.getStartTime()).
                        setEndTime(securities.getDate());
            }
        }
        elCouponList.setListCoupons(elCouponListCoupons);
        return ResponseFactory.sucData(elCouponList);
    }

    @Override
    public void sendElCouponList(Integer userId) {
        Example example = new Example(ElCouponList.class);
        example.createCriteria().andEqualTo(TYPE, 0).andEqualTo(STATUS, 1);
        List<ElCouponList> elCouponLists = elCouponListMapper.selectByExample(example);
        ArrayList<ElCouponListCoupon> listCoupons = new ArrayList<>();
        for (ElCouponList elCouponList : elCouponLists) {
            Example e = new Example(ElCouponListCoupon.class);
            e.createCriteria().andEqualTo(COLUMN_EL_COUPON_ID, elCouponList.getId());
            List<ElCouponListCoupon> elCouponListCoupons = elCouponListCouponMapper.selectByExample(e);
            for (ElCouponListCoupon elCouponListCoupon : elCouponListCoupons) {
                listCoupons.add(elCouponListCoupon);
            }
        }
        for (ElCouponListCoupon listCoupon : listCoupons) {
            ElUserCoupon elUserCoupon = new ElUserCoupon();
            Long aLong = orderHelper.genOrderNo(4, 12);
            elUserCoupon.setId(aLong);
            elUserCoupon.setUserId(userId);
            elUserCoupon.setCouponId(listCoupon.getCouponId());
            elUserCoupon.setTotalQuantity(listCoupon.getQuantity());
            elUserCoupon.setQuantity(listCoupon.getQuantity());
            elUserCoupon.setStatus((byte) 1);
            elUserCoupon.setCreated(new Date());
            elUserCoupon.setUpdated(new Date());
            elUserCouponV4Mapper.insertSelective(elUserCoupon);
        }
    }

    /**
     * @param id
     * @Description: 扫码获取礼包详情
     * @Author: LxH
     * @Date: 2020/10/26 13:01
     */
    @Override
    public Response getElCouponListById(Integer id) {
        ElCouponList elCouponList = elCouponListMapper.selectByPrimaryKey(id);
        Example e = new Example(ElCouponListCoupon.class);
        e.createCriteria().andEqualTo(COLUMN_EL_COUPON_ID, elCouponList.getId());
        List<ElCouponListCoupon> elCouponListCoupons = elCouponListCouponMapper.selectByExample(e);
        for (ElCouponListCoupon elCouponListCoupon : elCouponListCoupons) {
            Securities securities = securitiesMapper.selectByPrimaryKey(elCouponListCoupon.getCouponId());
            elCouponListCoupon.setCouponName(securities.getTitle()).setStartTime(securities.getStartTime()).
                    setEndTime(securities.getDate()).setLogo(securities.getLogo());
        }
        elCouponList.setListCoupons(elCouponListCoupons);
        return ResponseFactory.sucData(elCouponList);
    }

    /**
     * @param: userId
     * @param: elCouponListId
     * @Description: 查询是否弹礼包弹窗
     * @Author: LxH
     * @Date: 2021/1/25 9:15
     */
    @Override
    public Response queryState(Integer userId, Integer elCouponListId) {
        Example example = new Example(ElCouponListType.class);
        example.createCriteria().andEqualTo("userId", userId).
                andEqualTo("elCouponListId", elCouponListId);
        List<ElCouponListType> elCouponListTypes = elCouponListTypeMapper.selectByExample(example);
        if (elCouponListTypes.size()>0) {
            return ResponseFactory.suc("不弹弹窗", 0);
        }
        return ResponseFactory.suc("弹弹窗", 1);
    }

    private Response appletsPay(ElCouponListOrder elCouponListOrder, String openId) {
        String value = String.valueOf(new BigDecimal(String.valueOf(elCouponListOrder.getPrice())).movePointRight(2).intValue());
        return WXCodeApi.prepayment(openId, "ElCouponListOrder",value, elCouponListOrder.getId().toString());
    }


    private void giveElCouponList(AppUser appUser, ElCouponList elCouponList) {
        //添加礼包给用户
        ElUserCouponList elUserCouponList = new ElUserCouponList();
        Long aLong = orderHelper.genOrderNo(4, 12);
        elUserCouponList.setElCouponListId(elCouponList.getId()).setId(aLong).setPhone(appUser.getPhone()).
                setQuantity(1).setTitle(elCouponList.getTitle()).setUserId(appUser.getId()).setCreated(new Date()).
                setUpdated(new Date());
        int insert = elUserCouponListMapper.insert(elUserCouponList);
        if (insert < 1) {
            throw new ServiceException("用户添加礼包记录失败");
        }
        Example example = new Example(ElCouponListCoupon.class);
        example.createCriteria().andEqualTo(COLUMN_EL_COUPON_ID,elCouponList.getId());
        List<ElCouponListCoupon> elCouponListCoupons = elCouponListCouponMapper.selectByExample(example);
        //添加优惠券给用户
        if (elCouponListCoupons.size() < 1 || StringUtils.isNotBlank(elCouponListCoupons.toString())) {
            for (ElCouponListCoupon elCouponListCoupon : elCouponListCoupons) {
                Securities securities = securitiesMapper.selectByPrimaryKey(elCouponListCoupon.getCouponId());
                Example ex = new Example(ElUserCoupon.class);
                ex.createCriteria().andEqualTo("userId", appUser.getId()).
                        andEqualTo("couponId", securities.getId()).andEqualTo("status", 1);
                List<ElUserCoupon> elUserCoupons = elUserCouponV4Mapper.selectByExample(ex);
                if (elUserCoupons.size()>0) {
                    for (ElUserCoupon elUserCoupon : elUserCoupons) {
                        elUserCoupon.setQuantity(elUserCoupon.getQuantity()+elCouponListCoupon.getQuantity());
                        elUserCoupon.setTotalQuantity(elUserCoupon.getTotalQuantity()+elCouponListCoupon.getQuantity());
                        elUserCoupon.setUpdated(new Date());
                        elUserCouponV4Mapper.updateByPrimaryKeySelective(elUserCoupon);
                    }
                }else {
                    ElUserCoupon elUserCoupon = new ElUserCoupon();
                    Long Long = orderHelper.genOrderNo(4, 12);
                    elUserCoupon.setId(Long);
                    elUserCoupon.setTotalQuantity(elCouponListCoupon.getQuantity());
                    elUserCoupon.setQuantity(elCouponListCoupon.getQuantity());
                    elUserCoupon.setStatus((byte) 1);
                    elUserCoupon.setCouponId(securities.getId());
                    elUserCoupon.setUserId(appUser.getId());
                    elUserCoupon.setCreated(new Date());
                    elUserCoupon.setUpdated(new Date());
                    int i = elUserCouponV4Mapper.insertSelective(elUserCoupon);
                    if (i < 1) {
                        throw new ServiceException("用户添加优惠券记录失败");
                    }
                }
            }
        }
    }
}
