package com.chouchongkeji.service.v4.Impl;

import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.dao.v3.StoreMapper;
import com.chouchongkeji.dial.dao.v4.ShareCouponMapper;
import com.chouchongkeji.dial.dao.v4.ShareCouponUserMapper;
import com.chouchongkeji.dial.dao.v4.StoreSysAdminIdVoMapper;
import com.chouchongkeji.dial.dao.v4.SysAdminVoMapper;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.dial.pojo.v3.Store;
import com.chouchongkeji.dial.pojo.v4.ShareCoupon;
import com.chouchongkeji.dial.pojo.v4.ShareCouponUser;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.utils.AESUtils;
import com.chouchongkeji.service.v4.ShareCouponService;
import com.chouchongkeji.service.v4.vo.StoreVoV4;
import com.chouchongkeji.service.v4.vo.SysAdminIdVo;
import com.chouchongkeji.service.v4.vo.SysAdminVo;
import com.chouchongkeji.util.OrderHelper;
import com.chouchongkeji.util.Qrcode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.chouchongkeji.dial.pojo.v4.ShareCoupon.ADMIN_ID;
import static com.chouchongkeji.dial.pojo.v4.ShareCoupon.TYPE;
import static com.chouchongkeji.dial.pojo.v4.ShareCouponUser.*;

/**
 * @description:
 * @author: LxH
 * @time: 2020/10/15 0015 下午 6:13
 */
@Service
public class ShareCouponServiceImpl implements ShareCouponService {

    @Resource
    private ShareCouponMapper shareCouponMapper;

    @Resource
    private ShareCouponUserMapper shareCouponUserMapper;

    @Resource
    private AppUserMapper appUserMapper;

    @Resource
    private OrderHelper orderHelper;

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private SysAdminVoMapper sysAdminVoMapper;

    @Resource
    private StoreSysAdminIdVoMapper storeSysAdminIdVoMapper;

    /**
     * @param: userId
     * @param: shareCouponId
     * @description: 用户领取分享劵
     * @author: LxH
     * @time: 2020/10/15 0015 下午 6:20
     */
    @Override
    @Transactional
    public Response giveShareCoupon(Integer userId, Integer shareCouponId ,Integer storeId) {
        AppUser appUser = appUserMapper.selectByPrimaryKey(userId);
        if (appUser == null) {
            throw new ServiceException("该用户还未注册");
        }
        ShareCoupon shareCoupon = shareCouponMapper.selectByPrimaryKey(shareCouponId);
        if (shareCoupon == null) {
            throw new ServiceException("该分享劵不存在");
        }
        if (shareCoupon.getStatus() == 10) {
            throw new ServiceException("分享卷已删除");
        }
        Example example = new Example(ShareCouponUser.class);
        example.createCriteria().andEqualTo(USER_ID,userId).andEqualTo(SHARE_COUPON_ID,shareCouponId).
                andEqualTo(STATUS,1);
        List<ShareCouponUser> shareCouponUsers = shareCouponUserMapper.selectByExample(example);
        if (shareCouponUsers!=null) {
            for (ShareCouponUser shareCouponUser : shareCouponUsers) {
                if (shareCouponUser.getQuantity() >= shareCoupon.getCeiling()) {
                    throw  new ServiceException("该用户分享劵以达到上限");
                } else {
                    shareCouponUser.setQuantity(shareCouponUser.getTotalQuantity()+1);
                    shareCouponUser.setTotalQuantity(shareCouponUser.getTotalQuantity()+1);
                    shareCouponUserMapper.updateByPrimaryKeySelective(shareCouponUser);
                }
            }
            //发送分享劵
        }
        bindShareCouponByUser(appUser,shareCoupon,new ShareCouponUser().setStoreId(storeId));
        return ResponseFactory.sucMsg("分享劵领取成功");
    }

    /**
     * @param: userId
     * @param: page
     * @description: 获取用户分享劵列表
     * @author: LxH
     * @time: 2020/10/16 0016 上午 11:28
     */
    @Override
    public Response getShareCouponList(Integer userId) {
        Example example = new Example(ShareCouponUser.class);
        example.createCriteria().andEqualTo(USER_ID,userId).andEqualTo(STATUS,1);
        List<ShareCouponUser> shareCouponUsers = shareCouponUserMapper.selectByExample(example);
        ArrayList<ShareCouponUser> list = new ArrayList<>();
        if (shareCouponUsers != null) {
            for (ShareCouponUser shareCouponUser : shareCouponUsers) {
                ShareCoupon shareCoupon = shareCouponMapper.selectByPrimaryKey(shareCouponUser.getShareCouponId());
                //分享劵logo
                shareCouponUser.setLogo(shareCoupon.getLogo());
                StringBuilder titles = new StringBuilder();
                if (StringUtils.isNotBlank(shareCoupon.getStoreIds())) {
                    String[] split = shareCoupon.getStoreIds().split(",");
                    for (String s : split) {
                        //查询店铺信息
                        Store store = storeMapper.selectByPrimaryKey(Integer.parseInt(s));
                        if (store !=null) {
                            titles.append(store.getName()).append("/");
                        }
                    }
                }
                //店铺名称
                shareCouponUser.setStoreName(titles.delete(titles.length()-1,titles.length()).toString());
                // 生成二维码 id + 当前时间
                String code = AESUtils.encrypt("zheshishenmemima",
                        String.format("3,%s,%s", shareCouponUser.getId(), System.currentTimeMillis()));
                shareCouponUser.setCode(code);
                String imgName = UUID.randomUUID().toString();
                Qrcode.generate(code, imgName);
                shareCouponUser.setCodeImg("/qrcodeImg/" + imgName + ".png");
                // 状态
                long time = shareCoupon.getStartTime().getTime();
                long l = System.currentTimeMillis();
                System.out.println(time+"\\"+l);
                if (shareCoupon.getStartTime().getTime() > System.currentTimeMillis()) {
                    // 未开始
                    shareCouponUser.setStatus((byte) 2);
                } else if (shareCouponUser.getEndTime().getTime() < System.currentTimeMillis()) {
                    // 已结束
                    shareCouponUser.setStatus((byte) 10);
                    shareCouponUserMapper.updateByPrimaryKeySelective(shareCouponUser);
                } else {
                    // 正常

                    shareCouponUser.setStatus((byte) 1);
                    list.add(shareCouponUser);

                }
            }
            Collections.sort(shareCouponUsers, new Comparator<ShareCouponUser>() {
                @Override
                public int compare(ShareCouponUser o1, ShareCouponUser o2) {
                    return o1.getStatus().compareTo(o2.getStatus());
                }
            });
        }
        return ResponseFactory.sucData(list);
    }

    /**
     * @Description: 获取拥有分享券的店铺
     * @Author: LxH
     * @Date: 2020/10/22 14:44
     */
    @Override
    public Response findStore() {
        Example example = new Example(ShareCoupon.class);
        example.createCriteria().andEqualTo(TYPE,1).andEqualTo(STATUS, 1);
        List<ShareCoupon> shareCoupons = shareCouponMapper.selectByExample(example);
        StringBuilder stores = new StringBuilder();
        if (shareCoupons.size()>0) {
            for (ShareCoupon shareCoupon : shareCoupons) {
                if (shareCoupon.getDate().getTime() >= System.currentTimeMillis()) {
                    stores.append(shareCoupon.getStoreIds());
                }
            }
        }
        if (stores.length() == 0) {
            return ResponseFactory.sucMsg("所有店铺都还未添加分享券！");
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
                    storeVoV4s.add(toreVoV4);
                }
            }
            return ResponseFactory.sucData(storeVoV4s);
        }
       return ResponseFactory.sucMsg("所有店铺都还未添加分享券！");
    }

    /**
     * @param： storeId
     * @Description: 查找分享
     * @Author: LxH
     * @Date: 2020/10/22 15:45
     */
    @Override
    public Response findShareCouponByStore(Integer storeId) {
        StoreVoV4 store = storeMapper.findStore(storeId);
        if (store == null) {
            return ResponseFactory.err("没有这个店铺！");
        }
        Integer adminId = storeMapper.findAdmin(store.getAdminId());
        Example example = new Example(ShareCoupon.class);
        example.createCriteria().andEqualTo(ADMIN_ID, adminId).
                andEqualTo(TYPE, 1).andEqualTo(STATUS, 1);
        ShareCoupon shareCoupon = shareCouponMapper.selectOneByExample(example);
        if (shareCoupon == null) {
            return ResponseFactory.err("该店铺还未设置分享券！");
        }
        String[] split = shareCoupon.getStoreIds().split(",");
        StringBuilder builder = new StringBuilder();
        for (String s : split) {
            Store select = storeMapper.selectByPrimaryKey(Integer.parseInt(s));
            builder.append(select.getName()).append("/");
        }
        shareCoupon.setStoreName(builder.delete(builder.length()-1,builder.length()).toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        shareCoupon.setStart(simpleDateFormat.format(shareCoupon.getStartTime())).
                setEnd(simpleDateFormat.format(shareCoupon.getDate())).
                setAmount(shareCoupon.getTotality()+"/"+shareCoupon.getTotal());

        return ResponseFactory.sucData(shareCoupon);
    }

    @Override
    public Response findShareCoupon(Integer shareCouponId) {
        ShareCoupon shareCoupon = shareCouponMapper.selectByPrimaryKey(shareCouponId);
        if (shareCoupon == null) {
            return ResponseFactory.err("没有这张分享券");
        }
        String[] split = shareCoupon.getStoreIds().split(",");
        StringBuilder builder = new StringBuilder();
        for (String s : split) {
            Store select = storeMapper.selectByPrimaryKey(Integer.parseInt(s));
            builder.append(select.getName()).append("/");
        }
        shareCoupon.setStoreName(builder.delete(builder.length()-1,builder.length()).toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        shareCoupon.setStart(simpleDateFormat.format(shareCoupon.getStartTime())).
                setEnd(simpleDateFormat.format(shareCoupon.getDate())).
                setAmount(shareCoupon.getTotality()+"/"+shareCoupon.getTotal());
        return ResponseFactory.sucData(shareCoupon);
    }

    private void bindShareCouponByUser(AppUser appUser, ShareCoupon shareCoupon, ShareCouponUser shareCouponUser) {
        Long aLong = orderHelper.genOrderNo(4, 12);
        shareCouponUser.setId(aLong).setPhone(appUser.getPhone()).setUserId(appUser.getId()).setTitle(shareCoupon.getTitle()).
                setQuantity(1).setTotalQuantity(1).setStatus((byte) 1).setShareCouponId(shareCoupon.getId()).setCreated(new Date()).
                setUpdated(new Date());
        int i = shareCouponUserMapper.insertSelective(shareCouponUser);
        if (i < 1) {
            throw new ServiceException("赠送失败");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(shareCouponUser.getCreated());
        calendar.add(Calendar.DATE,shareCoupon.getDay());
        Date endTime = calendar.getTime();
        Date reEndTime = endTime.after(shareCoupon.getDate()) ? endTime : shareCoupon.getDate();
        shareCouponUser.setEndTime(reEndTime);
        shareCouponUserMapper.updateByPrimaryKeySelective(shareCouponUser);
        if (shareCoupon.getTotality() == 0) {
            throw new ServiceException("该分享劵已用尽");
        }
        if (shareCoupon.getTotality() - 1 < 1) {
            shareCoupon.setType((byte) 10);
        }
        shareCoupon.setTotality(shareCoupon.getTotality() - 1);
        int i1 = shareCouponMapper.updateByPrimaryKeySelective(shareCoupon);
        if (i1 < 1) {
            throw new ServiceException("修改分享劵数量失败");
        }
    }
}
