package com.chouchongkeji.service.v4.Impl;

import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.dao.v3.StoreMapper;
import com.chouchongkeji.dial.dao.v4.*;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.dial.pojo.v3.Store;
import com.chouchongkeji.dial.pojo.v4.RebateCoupon;
import com.chouchongkeji.dial.pojo.v4.RebateCouponBeInvited;
import com.chouchongkeji.dial.pojo.v4.RebateCouponManage;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.utils.AESUtils;
import com.chouchongkeji.service.v4.RebateCouponService;
import com.chouchongkeji.service.v4.vo.*;
import com.chouchongkeji.util.CountDownUtils;
import com.chouchongkeji.util.OrderHelper;
import com.chouchongkeji.util.Qrcode;

import com.yichen.auth.service.UserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.chouchongkeji.dial.pojo.v4.RebateCoupon.*;
import static com.chouchongkeji.dial.pojo.v4.RebateCouponBeInvited.REBATE_COUPON_ID;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/10/21 11:15
 */
@Service
public class RebateCouponServiceImpl implements RebateCouponService {

    @Resource
    private RebateCouponManageMapper rebateCouponManageMapper;

    @Resource
    private RebateCouponMapper rebateCouponMapper;

    @Resource
    private RebateCouponBeInvitedMapper rebateCouponBeInvitedMapper;

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private AppUserMapper appUserMapper;

    @Resource
    private SysAdminVoMapper sysAdminVoMapper;

    @Resource
    private StoreSysAdminIdVoMapper storeSysAdminIdVoMapper;

    @Resource
    private OrderHelper orderHelper;

    /**
     * @param： userId
     * @Description: 創建折扣劵
     * @Author: LxH
     * @Date: 2020/10/21 11:23
     */
    @Override
    public Response createRebateCoupon(Integer userId,Integer storeId) {
        Example example = new Example(RebateCoupon.class);
        example.createCriteria().andEqualTo(USER_ID, userId).andEqualTo(STATUS, (byte)1).
                andEqualTo(STORE_ID, storeId);
        RebateCoupon rebateCoupon = rebateCouponMapper.selectOneByExample(example);

        if (rebateCoupon != null) {
            if (rebateCoupon.getIsEnd().equals((byte)1)&&getAbortTime(rebateCoupon.getEndTime())&&
                    rebateCoupon.getIsEnd()!=null&&rebateCoupon.getEndTime()!=null) {
                //添加店铺和用户信息
                rebateCoupon = setRebateCoupon(rebateCoupon,userId,null);
                return ResponseFactory.suc("该用户已经生成核销码", rebateCoupon);
            }
            if (rebateCoupon.getIsEnd().equals((byte)1)&&!getAbortTime(rebateCoupon.getEndTime())&&
                    rebateCoupon.getIsEnd()!=null&&rebateCoupon.getEndTime()!=null) {
                //倒计时结束并未生成核销码  清空数据重新生成
                Example e = new Example(RebateCouponBeInvited.class);
                e.createCriteria().andEqualTo(REBATE_COUPON_ID, rebateCoupon.getId());
                List<RebateCouponBeInvited> rebateCouponBeInviteds = rebateCouponBeInvitedMapper.selectByExample(e);
                if (rebateCouponBeInviteds.size() > 0) {
                    rebateCouponBeInvitedMapper.deleteByExample(e);
                }
                rebateCouponMapper.deleteByPrimaryKey(rebateCoupon.getId());
                //创建折扣卷
                //creatRebateCouponByUser(userId,storeId);
                return ResponseFactory.sucMsg("到期删除");
            }
            if (rebateCoupon.getFirstTime()==null || getAbortTime(rebateCoupon.getFirstTime()) ) {
                if (rebateCoupon.getFirstTime() != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(rebateCoupon.getFirstTime());
                    calendar.add(Calendar.HOUR, 10);
                    String countDown = CountDownUtils.getCountDown(calendar.getTime(), new Date());
                    rebateCoupon = setRebateCoupon(rebateCoupon,userId,countDown);
                    return ResponseFactory.suc("该用户倒计时正在进行中！", rebateCoupon);
                }
                rebateCoupon = setRebateCoupon(rebateCoupon,userId,null);
                return ResponseFactory.suc("该用户倒计时还未结束", rebateCoupon);
            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(rebateCoupon.getFirstTime());
                calendar.add(Calendar.HOUR, 10);
                rebateCoupon.setEndTime(calendar.getTime()).setIsEnd((byte) 1);
                rebateCouponMapper.updateByPrimaryKeySelective(rebateCoupon);
                rebateCoupon = setRebateCoupon(rebateCoupon,userId,null);
                return ResponseFactory.suc("该用户倒计时已经结束", rebateCoupon);

            }
        } else {
            //创建折扣卷
            return creatRebateCouponByUser(userId, storeId);
        }
    }

    /**
     * @param: userId
     * @param: rebateCouponId
     * @Description: 生成核销码
     * @Author: LxH
     * @Date: 2020/10/21 15:18
     */
    @Override
    public Response appliedRebateCoupon(Integer userId, Long rebateCouponId) {
        RebateCoupon rebateCoupon = rebateCouponMapper.selectByPrimaryKey(rebateCouponId);
        if (rebateCoupon == null) {
            return ResponseFactory.sucMsg("折扣卷不存在");
        }
        // 生成二维码 id + 当前时间
        String code = AESUtils.encrypt("zheshishenmemima",
                String.format("4,%s,%s", rebateCouponId, System.currentTimeMillis()));
        String imgName = UUID.randomUUID().toString();
        Qrcode.generate(code, imgName);
        rebateCoupon.setIsEnd((byte) 1);
        if (rebateCoupon.getEndTime()!=null) {
            rebateCoupon.setEndTime(new Date());
        }
        int i = rebateCouponMapper.updateByPrimaryKey(rebateCoupon);
        System.out.println(i);
        String applied = "https://liyuquan.cn/static/qrcodeImg/" + imgName + ".png";
        HashMap<String, String> map = new HashMap<>();
        map.put("折扣卷核销码url",applied);
        map.put("code", code);
        return ResponseFactory.suc("折扣卷核销码", map);
    }

    @Override
    public Response addRebate(BigDecimal userRebate, Integer userId, Integer superId,Integer storeId,BigDecimal rebateTopLimit) {
        if (userId.equals(superId)) {
            return ResponseFactory.err("不能助力自己！");
        }
        BigDecimal divide = userRebate.divide(new BigDecimal(100), 2, RoundingMode.UP);
        System.out.println(divide);
        Example example = new Example(RebateCoupon.class);
        example.createCriteria().andEqualTo(USER_ID, superId).
                andEqualTo(STATUS, 1).andEqualTo(IS_END, 0).andEqualTo(STORE_ID, storeId);
        RebateCoupon rebateCoupon = rebateCouponMapper.selectOneByExample(example);

        if (rebateCoupon.getRebate().compareTo(rebateTopLimit.divide(new BigDecimal(100), 2, RoundingMode.UP)) == 0) {
            return ResponseFactory.err("当前折扣已经到达上限！");
        }
        if (rebateCoupon.getFirstTime() != null) {
            if (!getAbortTime(rebateCoupon.getFirstTime())) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "该用户折扣卷倒计时已结束！");
            }
        }
        //添加被邀请记录
        RebateCouponBeInvited rebateCouponBeInvited = new RebateCouponBeInvited();
        rebateCouponBeInvited.setRebateCouponId(rebateCoupon.getId()).setLowUserId(userId);
        rebateCoupon.setRebate(rebateCoupon.getRebate().subtract(divide));
        rebateCouponBeInvited.setHelpPercentage(divide);
        rebateCouponBeInvited.setCreated(new Date()).setUpdated(new Date());
        rebateCouponBeInvitedMapper.insertSelective(rebateCouponBeInvited);
        if (rebateCoupon.getFirstTime() == null) {
            rebateCoupon.setFirstTime(rebateCouponBeInvited.getCreated());
        }
        rebateCoupon.setUpdated(new Date());
        if (rebateCoupon.getRebate().compareTo(rebateTopLimit.divide(new BigDecimal(100), 2, RoundingMode.UP)) == -1) {
            rebateCoupon.setRebate(rebateTopLimit.divide(new BigDecimal(100), 2, RoundingMode.UP));
        }
        rebateCouponMapper.updateByPrimaryKeySelective(rebateCoupon);
        //return getSuperUserInfo(superId,rebateCoupon.getStoreId());
        AppUser appUser = appUserMapper.selectByPrimaryKey(superId);
        if (rebateCoupon == null) {
            return ResponseFactory.err("该邀请已失效！");
        }
        Store store = storeMapper.selectByPrimaryKey(storeId);
        SysAdminIdVo sysAdminIdVo = storeSysAdminIdVoMapper.selectByPrimaryKey(storeId);
        SysAdminVo sysAdminVo = sysAdminVoMapper.selectByPrimaryKey(sysAdminIdVo.getAdminId());
        SuperUserVo userVo = new SuperUserVo();
        userVo.setAvatar(appUser.getAvatar()).setNickname(appUser.getNickname()).
                setStoreName(store.getName()).setStoreAddress(store.getArea() + store.getAddress()).
                setRebate(rebateCoupon.getRebate().multiply(new BigDecimal(100))).
                setUserRebate(userVo.getRebate());
        if (sysAdminVo != null) {
            userVo.setStoreLogo(sysAdminVo.getAvatar());
        }
        return ResponseFactory.sucData(userVo);

    }

    /**
     * @Description: 查询所有店铺
     * @Author: LxH
     * @Date: 2020/10/26 12:15
     */
    @Override
    public Response findStores() {
        Example example = new Example(RebateCouponManage.class);
        example.createCriteria().andEqualTo("status", 1);
        List<RebateCouponManage> manages = rebateCouponManageMapper.selectByExample(example);
        StringBuilder stores = new StringBuilder();
        for (RebateCouponManage manage : manages) {
            stores.append(manage.getStoreIds());
        }
        if (stores.length() == 0) {
            return ResponseFactory.sucMsg("所有店铺都还未添加折扣券！");
        }
        String[] split = stores.toString().split(",");
        ArrayList<String> integers = new ArrayList<>();
        for (int i = 0; i < split.length ; i++) {
            if (!integers.contains(split[i])) {
                integers.add(split[i]);
            }
        }
        if (integers.size()>0) {
            List<StoreVoV4> storeVoV4s = new ArrayList<>();
            for (String integer : integers) {
                StoreVoV4 toreVoV4 = null;
                if (integer != null) {
                    toreVoV4 = storeMapper.findStore(Integer.valueOf(integer));
                }
                if (toreVoV4!=null) {
                    SysAdminIdVo sysAdminIdVo = storeSysAdminIdVoMapper.selectByPrimaryKey(toreVoV4.getId());
                    SysAdminVo sysAdminVo = sysAdminVoMapper.selectByPrimaryKey(sysAdminIdVo.getAdminId());
                    if (sysAdminVo != null) {
                        toreVoV4.setAvatar(sysAdminVo.getAvatar());
                    }
                    storeVoV4s.add(toreVoV4);
                }
            }
            return ResponseFactory.sucData(storeVoV4s);
        }
        return ResponseFactory.sucMsg("所有店铺都还未添加折扣券！");
    }

    /**
     * @param: superId
     * @Description: 获取上级用户信息
     * @Author: LxH
     * @Date: 2020/11/16 11:43
     */
    @Override
    public Response getSuperUserInfo(Integer superId,Integer storeId) {
        AppUser appUser = appUserMapper.selectByPrimaryKey(superId);
        Example example = new Example(RebateCoupon.class);
        example.createCriteria().andEqualTo(STATUS,1).
                andEqualTo(IS_END,0).andEqualTo(USER_ID, superId).andEqualTo(STORE_ID, storeId);
        RebateCoupon rebateCoupon = rebateCouponMapper.selectOneByExample(example);
        if (rebateCoupon==null) {
            return ResponseFactory.err("该邀请已失效！");
        }
        Store store = storeMapper.selectByPrimaryKey(storeId);
        SysAdminIdVo sysAdminIdVo = storeSysAdminIdVoMapper.selectByPrimaryKey(storeId);
        SysAdminVo sysAdminVo = sysAdminVoMapper.selectByPrimaryKey(sysAdminIdVo.getAdminId());
        Integer adminId = storeSysAdminIdVoMapper.findAdminId(store.getMerchantId());
        ReVo reVo = storeSysAdminIdVoMapper.findReVo(adminId);
        SuperUserVo userVo = new SuperUserVo();
        userVo.setAvatar(appUser.getAvatar()).setNickname(appUser.getNickname()).
                setStoreName(store.getName()).setStoreAddress(store.getArea()+store.getAddress()).
                setRebate(rebateCoupon.getRebate().multiply(new BigDecimal(100))).setUserRebate(userVo.getRebate()).setNewRebate(reVo.getRebateNew().multiply(new BigDecimal(100))).setOldRebate(reVo.getRebateOld().multiply(new BigDecimal(100)));
        if (sysAdminVo != null) {
            userVo. setStoreLogo(sysAdminVo.getAvatar());
        }
        return ResponseFactory.sucData(userVo);
    }


    private Response creatRebateCouponByUser(Integer userId, Integer storeId) {
        RebateCoupon rebateCoupon = new RebateCoupon();
        Long aLong = orderHelper.genOrderNo(4, 12);
        rebateCoupon.setId(aLong).setStoreId(storeId).setUserId(userId).setRebate(new BigDecimal("1.00")).
                setStatus((byte) 1).setIsEnd((byte) 0).setCreated(new Date()).setUpdated(new Date());
        rebateCouponMapper.insertSelective(rebateCoupon);
        rebateCoupon = setRebateCoupon(rebateCoupon,userId,null);
        return ResponseFactory.suc("折扣卷创建成功！",rebateCoupon);
    }

    private Boolean getAbortTime(Date firstTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstTime);
        calendar.add(Calendar.HOUR, 10);
        Date time = calendar.getTime();
        Date date = new Date();
        return time.after(date);
    }

    private RebateCoupon setRebateCoupon(RebateCoupon rebateCoupon,Integer userId,String countDown) {
        Integer storeId = rebateCoupon.getStoreId();
        Store store = storeMapper.selectByPrimaryKey(storeId);
        SysAdminIdVo sysAdminIdVo = storeSysAdminIdVoMapper.selectByPrimaryKey(storeId);
        SysAdminVo sysAdminVo = sysAdminVoMapper.selectByPrimaryKey(sysAdminIdVo.getAdminId());
        Integer adminId = storeSysAdminIdVoMapper.findAdminId(store.getMerchantId());
        ReVo reVo = storeSysAdminIdVoMapper.findReVo(adminId);
        AppUser user = appUserMapper.selectByPrimaryKey(userId);
        rebateCoupon.setStoreName(store.getName()).
                setStoreAddress(store.getArea()+store.getAddress()).
                setRebate(rebateCoupon.getRebate().multiply(new BigDecimal(100))).
                setSuperId(userId).setNewRebate(reVo.getRebateNew().multiply(new BigDecimal(100))).setOldRebate(reVo.getRebateOld().multiply(new BigDecimal(100))).
        setTitle(user.getNickname()+"邀请您助力折扣");
        if (sysAdminVo !=null) {
            rebateCoupon.setStoreLogo(sysAdminVo.getAvatar());
        }
        Example example = new Example(RebateCouponBeInvited.class);
        example.createCriteria().andEqualTo(REBATE_COUPON_ID, rebateCoupon.getId());
        List<RebateCouponBeInvited> rebateCouponBeInviteds = rebateCouponBeInvitedMapper.selectByExample(example);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (rebateCouponBeInviteds.size() >0) {
            for (RebateCouponBeInvited rebateCouponBeInvited : rebateCouponBeInviteds) {
                AppUser appUser = appUserMapper.selectByPrimaryKey(rebateCouponBeInvited.getLowUserId());
                rebateCouponBeInvited.setLowUserName(appUser.getNickname()).setLowUserLogo(appUser.getAvatar()).
                        setLowUserPhone(appUser.getPhone()).setHelpPercentage(rebateCouponBeInvited.
                        getHelpPercentage().multiply(new BigDecimal(100))).setCreatedTime(simpleDateFormat.format(rebateCouponBeInvited.getCreated()));
            }
        }
        rebateCoupon.setRebateCouponBeInvitedList(rebateCouponBeInviteds);
        if (countDown !=null) {
            rebateCoupon.setCountDown(countDown);
        }
        return rebateCoupon;
    }
}
