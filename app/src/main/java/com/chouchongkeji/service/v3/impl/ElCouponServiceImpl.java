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
import com.chouchongkeji.goexplore.utils.AESUtils;
import com.chouchongkeji.service.v3.ElCouponService;
import com.chouchongkeji.service.v3.vo.ElCouponVo;
import com.chouchongkeji.util.Constants;
import com.chouchongkeji.util.OrderHelper;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author linqin
 * @date 2020/2/10 10:26
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class ElCouponServiceImpl implements ElCouponService {

    @Autowired
    private ElUserCouponMapper elUserCouponMapper;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private ElectronicCouponsMapper electronicCouponsMapper;

    @Autowired
    private ElCouponSendMapper elCouponSendMapper;

    @Autowired
    private ElSendDetailMapper elSendDetailMapper;

    @Autowired
    private OrderHelper orderHelper;

    @Autowired
    private AppUserMapper appUserMapper;
//86430000

    /**
     * 定时器 按时取消优惠券赠送（1分钟）
     */
    @Scheduled(fixedRate = 60000)
    public void cancelCoupon() {
        List<ElCouponSend> sends = elCouponSendMapper.selectByAll();
        if (CollectionUtils.isNotEmpty(sends)) {
            for (ElCouponSend send : sends) {
                if (System.currentTimeMillis() - send.getCreated().getTime() >= 86430000) {
                    // 超时退回
                    ElUserCoupon userCoupon = elUserCouponMapper.selectByPrimaryKey(send.getNum());
                    if (userCoupon != null && userCoupon.getUserId().equals(send.getUserId())) {
                        int quantity = userCoupon.getQuantity() + send.getQuantity();
                        userCoupon.setQuantity(quantity);
                        if (quantity > 0) {
                            userCoupon.setStatus((byte) 1);
                        }
                        elUserCouponMapper.updateByPrimaryKeySelective(userCoupon);
                    }
                    send.setStatus(Constants.TRANSFER_SEND.CANCEL);
                    elCouponSendMapper.updateByPrimaryKeySelective(send);
                }
            }
        }
    }


    /**
     * 获取电子券列表
     *
     * @param userId
     * @param page
     * @return
     */
    @Override
    public Response getElCouponList(Integer userId, PageQuery page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<ElCouponVo> elCouponVos = elUserCouponMapper.selectByUserId(userId);
        if (!CollectionUtils.isEmpty(elCouponVos)) {
            for (ElCouponVo elCouponVo : elCouponVos) {
                StringBuilder titles = new StringBuilder();
                if (StringUtils.isNotBlank(elCouponVo.getStoreIds())) {
                    String[] split = elCouponVo.getStoreIds().split(",");
                    for (String s : split) {
                        Store store = storeMapper.selectByPrimaryKey(Integer.parseInt(s));
                        if (store != null) {
                            titles.append(store.getName()).append("/");
                        }
                    }
                }
                elCouponVo.setStoreName(titles.delete(titles.length() - 1, titles.length()).toString());
                // 生成二维码 id + 当前时间
                elCouponVo.setCode(AESUtils.encrypt("zheshishenmemima",
                        String.format("2,%s,%s", elCouponVo.getNum(), System.currentTimeMillis())));
                // 状态
                if (elCouponVo.getStartTime().getTime() > System.currentTimeMillis()) {
                    // 未开始
                    elCouponVo.setStatus((byte) 2);
                } else if (elCouponVo.getDate().getTime() < System.currentTimeMillis()) {
                    // 已结束
                    elCouponVo.setStatus((byte) 3);
                } else {
                    // 正常
                    elCouponVo.setStatus((byte) 1);
                }
            }
            Collections.sort(elCouponVos, new Comparator<ElCouponVo>() {
                @Override
                public int compare(ElCouponVo o1, ElCouponVo o2) {
                    return o1.getStatus().compareTo(o2.getStatus());
                }
            });
        }
        return ResponseFactory.sucData(elCouponVos);
    }

    /**
     * 获取电子券详情
     *
     * @param userId
     * @return
     */
    @Override
    public Response getElCouponDetail(Integer userId, Long num) {
        ElCouponVo vo = elUserCouponMapper.selectByNum(num);
        if (vo != null) {
            StringBuilder titles = new StringBuilder();
            if (StringUtils.isNotBlank(vo.getStoreIds())) {
                String[] split = vo.getStoreIds().split(",");
                for (String s : split) {
                    Store store = storeMapper.selectByPrimaryKey(Integer.parseInt(s));
                    if (store != null) {
                        titles.append(store.getName()).append("/");
                    }
                }
            }
            vo.setStoreName(titles.delete(titles.length() - 1, titles.length()).toString());
            // 生成二维码 id + 当前时间
            vo.setCode(AESUtils.encrypt("zheshishenmemima",
                    String.format("2,%s,%s", vo.getNum(), System.currentTimeMillis())));
            // 状态
            if (vo.getStartTime().getTime() > System.currentTimeMillis()) {
                // 未开始
                vo.setStatus((byte) 2);
            } else if (vo.getDate().getTime() < System.currentTimeMillis()) {
                // 已结束
                vo.setStatus((byte) 3);
            } else {
                // 正常
                vo.setStatus((byte) 1);
            }
        }
        return ResponseFactory.sucData(vo);
    }

    /**
     * 赠送优惠券
     *
     * @param userId
     * @param num      优惠券编号
     * @param quantity 数量
     * @return
     */
    @Override
    public Response sendCoupon(Integer userId, Long num, Integer quantity) {
        // 查询优惠券是否足够
        ElUserCoupon elCoupon = elUserCouponMapper.selectByPrimaryKey(num);
        if (elCoupon == null || !elCoupon.getUserId().equals(userId) || elCoupon.getStatus() != 1) {
            throw new ServiceException("优惠券出错");
        }
        if (quantity > elCoupon.getQuantity()) {
            throw new ServiceException("优惠券数量不足");
        }
        ElectronicCoupons coupon = electronicCouponsMapper.selectByPrimaryKey(elCoupon.getCouponId());
        if (coupon == null || coupon.getStatus() != 1) {
            throw new ServiceException("该优惠券不存在");
        }
        //创建优惠券转赠记录
        ElCouponSend send = new ElCouponSend();
        send.setUserId(userId);
        send.setNum(num);
        send.setQuantity(quantity);
        send.setStatus(Constants.TRANSFER_SEND.WAIT);
        send.setCouponId(elCoupon.getCouponId());
        int insert = elCouponSendMapper.insert(send);
        if (insert < 1) {
            throw new ServiceException("创建优惠券转赠记录失败");
        }
        // 扣减赠送者优惠券数量
        int quantity1 = elCoupon.getQuantity() - quantity;
        elCoupon.setQuantity(quantity1);
        int i = elUserCouponMapper.updateByPrimaryKeySelective(elCoupon);
        if (i == 0) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "赠送者更新优惠券失败！");
        }
        String name = "好友";
        AppUser appUser = appUserMapper.selectByPrimaryKey(userId);
        if (appUser != null) {
            name = appUser.getNickname();
        }
        // 返回转赠记录id，用于分享给微信好友
        Map<String, Object> result = new HashMap<>();
        result.put("couponSendId", send.getId());
        result.put("title", name + "给您送了" + quantity + "张" + coupon.getTitle() + "优惠券");
        result.put("quantity", quantity);
        return ResponseFactory.sucData(result);
    }

    /**
     * 判断是否可以领取优惠券
     *
     * @param userId
     * @param couponSendId 优惠券赠送id
     * @return
     */
    @Override
    public Response judgeSendCoupon(Integer userId, Integer couponSendId) {
        ElCouponSend send = elCouponSendMapper.selectByPrimaryKey(couponSendId);
        if (send == null) {
            return ResponseFactory.errMsg(666, "该优惠券转赠不存在或已被转赠者撤回!");
        }
        ElectronicCoupons coupon = electronicCouponsMapper.selectByNum(send.getNum());
        if (coupon == null) {
            throw new ServiceException("该优惠券不存在");
        }
        ElCouponVo vo = couponDetail(coupon, send);
        // 自己领取
        if (send.getUserId().equals(userId)) {
            if (send.getStatus() == Constants.TRANSFER_SEND.SEND) {
                // 对方已接收
                vo.setStatus((byte) 4);
            } else if (System.currentTimeMillis() - send.getCreated().getTime() >= 86400000) {
                vo.setSummary("超过24小时，已退回");
                vo.setStatus((byte) 0);
            } else {
                // 等待对方接收
                vo.setStatus((byte) 5);
            }
            return ResponseFactory.sucData(vo);
        }
        if (send.getStatus() == Constants.TRANSFER_SEND.SEND) {
            ElSendDetail detail = elSendDetailMapper.selectByUserIdSendId(couponSendId, userId);
            if (detail != null) {
                vo.setSummary("已接收");
                vo.setStatus((byte) 2);
            } else {
                // 被别人领取
                vo.setSummary("已被接收");
                vo.setStatus((byte) 3);
            }
            return ResponseFactory.sucData(vo);
        }
        if (send.getStatus() == Constants.TRANSFER_SEND.CANCEL) {
            // 被别人领取
            vo.setSummary("超过24小时，已退回");
            vo.setStatus((byte) 0);
            return ResponseFactory.sucData(vo);
        }
        // 状态为1 超过24小时
        if (System.currentTimeMillis() - send.getCreated().getTime() >= 86400000) {
            // 被别人领取
            vo.setSummary("超过24小时，已退回");
            vo.setStatus((byte) 0);
            return ResponseFactory.sucData(vo);
        }
        // 可以领取
        vo.setStatus((byte) 1);
        return ResponseFactory.sucData(vo);

    }

    private ElCouponVo couponDetail(ElectronicCoupons coupon, ElCouponSend send) {
        StringBuilder titles = new StringBuilder();
        if (StringUtils.isNotBlank(coupon.getStoreIds())) {
            String[] split = coupon.getStoreIds().split(",");
            for (String s : split) {
                Store store = storeMapper.selectByPrimaryKey(Integer.parseInt(s));
                if (store != null) {
                    titles.append(store.getName()).append("/");
                }
            }
        }
        ElCouponVo vo = new ElCouponVo();
        vo.setStoreName(titles.delete(titles.length() - 1, titles.length()).toString());
        vo.setCouponId(coupon.getId());
        vo.setDate(coupon.getDate());
        vo.setLogo(coupon.getLogo());
        vo.setNum(send.getNum());
        vo.setQuantity(send.getQuantity());
        vo.setStoreIds(coupon.getStoreIds());
        vo.setSummary(coupon.getSummary());
        vo.setTitle(coupon.getTitle());
        vo.setUserId(send.getUserId());
        return vo;
    }

    /**
     * 领取优惠券
     *
     * @param userId
     * @param couponSendId 优惠券赠送id
     * @return
     */
    @Override
    public Response getElCoupon(Integer userId, Integer couponSendId) {
        ElCouponSend send = elCouponSendMapper.selectByPrimaryKey(couponSendId);
        if (send == null) {
            return ResponseFactory.errMsg(666, "该会员卡转赠不存在或已被转赠者撤回!");
        }
        Response response = judgeSendCoupon(userId, couponSendId);
        if (((ElCouponVo) response.getData()).getStatus() != 1) {
            return response;
        }
        // 赠送者的优惠券信息
        ElUserCoupon el = elUserCouponMapper.selectByPrimaryKey(send.getNum());
        if (el == null) {
            throw new ServiceException("原来优惠券不存在");
        }
//        if (el.getQuantity() < send.getQuantity()) {
//            throw new ServiceException("优惠券已被该好友使用，无法领取");
//        }
        // 查询优惠券信息
        ElectronicCoupons coupon = electronicCouponsMapper.selectByNum(send.getNum());
        if (coupon == null) {
            throw new ServiceException("该优惠券不存在");
        }
        // 领取优惠券
        // 1添加领取者优惠券
        ElUserCoupon userCoupon = new ElUserCoupon();
        userCoupon.setId(orderHelper.genOrderNo(8, 8));
        userCoupon.setCouponId(coupon.getId());
        userCoupon.setUserId(userId);
        userCoupon.setTotalQuantity(send.getQuantity());
        userCoupon.setQuantity(send.getQuantity());
        userCoupon.setStatus((byte) 1);
        userCoupon.setCode("");
        userCoupon.setStoreId(el.getStoreId());
        userCoupon.setAdminId(el.getAdminId());
        int insert = elUserCouponMapper.insert(userCoupon);
        if (insert < 1) {
            throw new ServiceException("添加优惠券失败");
        }
        // 添加领取记录
        ElSendDetail detail = new ElSendDetail();
        detail.setUserId(userId);
        detail.setCouponSendId(couponSendId);
        detail.setNum(send.getNum());
        detail.setQuantity(send.getQuantity());
        detail.setStatus((byte) 1);
        detail.setCouponId(coupon.getId());
        insert = elSendDetailMapper.insert(detail);
        if (insert < 1) {
            throw new ServiceException("添加领取优惠券记录失败");
        }
        // 更新转赠记录
        send.setStatus(Constants.TRANSFER_SEND.SEND);
        int i = elCouponSendMapper.updateByPrimaryKeySelective(send);
        if (i == 0) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "更新转赠记录失败！");
        }
        // 更新赠送者优惠券状态
        if (el.getQuantity() <= 0) {
            el.setStatus((byte) -1);
            elUserCouponMapper.updateByPrimaryKeySelective(el);
        }
        ElCouponVo vo = couponDetail(coupon, send);
        vo.setStatus((byte) 2);
        return ResponseFactory.sucData(vo);
    }
}
