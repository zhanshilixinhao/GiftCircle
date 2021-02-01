package com.chouchongkeji.service.user.info.impl;

import com.alibaba.fastjson.JSONObject;
import com.chouchongkeji.dial.dao.gift.item.ItemOrderDetailMapper;
import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.dao.user.ThirdAccountMapper;
import com.chouchongkeji.dial.dao.v4.RebateCouponManageMapper;
import com.chouchongkeji.dial.pojo.gift.item.ItemOrderDetail;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.dial.pojo.user.ThirdAccount;
import com.chouchongkeji.dial.pojo.v4.RebateCoupon;
import com.chouchongkeji.dial.pojo.v4.RebateCouponManage;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.backpack.base.BpService;
import com.chouchongkeji.service.iwant.wallet.FireworksService;
import com.chouchongkeji.service.message.MessageService;
import com.chouchongkeji.service.user.friend.FriendService;
import com.chouchongkeji.service.user.info.UserLoginService;
import com.chouchongkeji.service.user.info.UserService;
import com.chouchongkeji.service.v3.MemberCardService;
import com.chouchongkeji.service.v4.ElCouponListService;
import com.chouchongkeji.service.v4.RebateCouponService;
import com.chouchongkeji.service.v4.vo.SuperUserVo;
import com.chouchongkeji.service.wxapi.WXCodeApi;
import com.chouchongkeji.service.wxapi.WXResult;
import com.chouchongkeji.util.Constants;
import com.yichen.auth.properties.SecurityProperties;
import com.yichen.auth.redis.MRedisTemplate;
import com.yichen.auth.service.ThirdAccService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.*;

/**
 * @author linqin
 * @date 2018/6/5
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MRedisTemplate mRedisTemplate;

    @Autowired
    private ThirdAccService thirdAccService;

    @Autowired
    private FireworksService fireworksService;

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private FriendService friendService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private BpService bpService;

    @Autowired
    private ItemOrderDetailMapper itemOrderDetailMapper;

    @Autowired
    private MemberCardService memberCardService;

    @Resource
    private ThirdAccountMapper thirdAccountMapper;

    @Resource
    private ElCouponListService elCouponListService;

    @Resource
    private RebateCouponService rebateCouponService;

    @Resource
    private RebateCouponManageMapper rebateCouponManageMapper;

    /**
     * 微信授权登录
     *
     * @param client 1-android,2-ios,3-小程序
     * @param code   微信code
     * @return
     */
    @Override
    public Response wxLogin(Integer client, String code) {
        //换取openId
        WXResult result = WXCodeApi.getSession(client, code);

        // 如果换取openid失败
        if (result.getErrcode() != 0) {
            return ResponseFactory.errMsg(result.getErrcode(), result.getErrmsg());
        }

        //换取openId后登录
        Response response = WXCodeApi.login(result.getOpenid(),
                securityProperties.getOauth2().getClient()[0].getClientId(),
                securityProperties.getOauth2().getClient()[0].getClientSecret(),
                client == 3 ? 2 : 1);
        // 登录成功!
        if (!response.isSuccessful()) {
            String key = UUID.randomUUID().toString();
            mRedisTemplate.setString(key, result.getOpenid(), 300);

            if (client != 3) {
                mRedisTemplate.setString(result.getOpenid(), result.getAccess_token(), 600);
            }
            if (client == 1) {
                Map<String, String> map = new HashMap<>();
                map.put("key", key);
                return ResponseFactory.errData(response.getErrCode(), response.getMsg(), map);
            } else {
                return ResponseFactory.errData(response.getErrCode(), response.getMsg(), key);
            }

        }
        return response;
    }

    /**
     * 绑定手机号
     *
     * @param phone  手机号
     * @param openid openid
     * @param client 客户端id
     * @return
     */
    @Override
    public Response bindPhone(String phone, String openid, Integer client, Integer userId, AppUser user) {
        // 绑定手机号
        Response response = thirdAccService.addOpenAccDetail(openid, client == 3 ? 2 : 1, phone);
        if (!response.isSuccessful()) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), response.getMsg());
        }
        //好友用户id(被邀请者)
        Integer id = (Integer) response.getData();
        if (id != 0) {
            // 修改用户信息(被邀请者)
            if (user != null) {
                userService.updateProfile(id, user);
            }
            // 添加好友
            if (userId != null) {
                friendService.WXAddFriend(id, userId);
            }
        }
        bindPhone1(phone, openid, client, userId,id);
        // 绑定成功后登录
        response = WXCodeApi.login(openid,
                securityProperties.getOauth2().getClient()[0].getClientId(),
                securityProperties.getOauth2().getClient()[0].getClientSecret(), client == 3 ? 2 : 1);

        return response;
    }

    /**
     * @param： request client = 3
     * @Description: 微信小程序一键登录
     * @Author: LxH
     * @Date: 2020/10/20 13:07
     */
    @Override
    //@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
    public Response wxAppletLogin(Integer client,HttpServletRequest request) {
        String code = request.getParameter("code");
        String encryptedData = request.getParameter("encryptedData");
        String iv = request.getParameter("iv");
        String userInfo = request.getParameter("userInfo");
        String Id = request.getParameter("superId");
        String store = request.getParameter("storeId");
        Integer storeId = null;
        Integer superId = null;
        if (store != null&& !"".equals(store)) {
            storeId = Integer.valueOf(store);
        }
        if (Id != null && !"".equals(Id)) {
            superId = Integer.valueOf(Id);
        }
        //MiNiUserInfoVo userInfoVo = JSONObject.parseObject(userInfo, MiNiUserInfoVo.class);
        //System.out.println(userInfoVo);
        if (StringUtils.isBlank(code)) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "参数不能为空！");
        }
        //换取openId
        WXResult result = WXCodeApi.getSession(client, code);
        // 如果换取openid失败
        if (result.getErrcode() != 0) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "登录失败！");
        }
        String openid = result.getOpenid();
        String session_key = result.getSession_key();
        JSONObject jsonObject = null;
        jsonObject = getPhoneNumber(session_key, encryptedData, iv);
        if (jsonObject == null) {
            jsonObject = getPhoneNumber(session_key, encryptedData, iv);
        }
        String phoneNumber = jsonObject.get("purePhoneNumber").toString().trim();
        AppUser appUser = appUserMapper.selectByPhone(phoneNumber);
        Integer type = 2;
        RebateCoupon coupon = new RebateCoupon();
        if (appUser == null) {
            //创建用户信息
            // 获取第三方账号信息
            ThirdAccount userThird1 = thirdAccountMapper.selectByPhoneAndType(phoneNumber, type);
            if (userThird1 != null) {
                return ResponseFactory.errMsg(ErrorCode.PHONE_EXISTED.getCode(), "该手机号已经绑定其他账号");
            }
            userThird1 = thirdAccountMapper.selectByOpenIdAndType(openid, type);
            // 如果账号已经存在，不能再绑定
            if (userThird1 != null) {
                return ResponseFactory.errMsg(ErrorCode.PHONE_EXISTED.getCode(), "该微信号已经绑定其他账号");
            }
            Integer userId = userService.bindUser(userInfo,openid,phoneNumber,type);
            //赠送新人优惠券礼包
            elCouponListService.sendElCouponList(userId);
            //折扣卷折扣
            if (superId !=null && superId != 0 && storeId != null ) {
                Response superUserInfo = rebateCouponService.getSuperUserInfo(superId,storeId);
                SuperUserVo userVo = (SuperUserVo) superUserInfo.getData();
                Example example = new Example(RebateCouponManage.class);
                example.createCriteria().andEqualTo("status", 1);
                List<RebateCouponManage> couponManages = rebateCouponManageMapper.selectByExample(example);
                RebateCouponManage manage = new RebateCouponManage();
                for (RebateCouponManage couponManage : couponManages) {
                    String[] split = couponManage.getStoreIds().split(",");
                    for (String s : split) {
                        Integer i = Integer.valueOf(s);
                        if (i.equals(storeId)) {
                            manage = couponManage;
                        }
                    }
                }
                userVo.setUserRebate(manage.getRebateNew().multiply(new BigDecimal("100")));
                userVo.setSuperId(superId).setRebateTopLimit(manage.getRebate().multiply(new BigDecimal("100"))).setType(1);
                return WXCodeApi.loginV4(openid,
                        securityProperties.getOauth2().getClient()[0].getClientId(),
                        securityProperties.getOauth2().getClient()[0].getClientSecret(), type,
                        userVo);
            }
            // 绑定成功后登录
            return WXCodeApi.login(openid,
                    securityProperties.getOauth2().getClient()[0].getClientId(),
                    securityProperties.getOauth2().getClient()[0].getClientSecret(), type);

        } else {
            //折扣卷折扣
            if (superId !=null && superId != 0 && storeId != null) {
                Response superUserInfo = rebateCouponService.getSuperUserInfo(superId,storeId);
                SuperUserVo userVo = (SuperUserVo) superUserInfo.getData();
                Example example = new Example(RebateCouponManage.class);
                example.createCriteria().andEqualTo("status", 1);
                List<RebateCouponManage> couponManages = rebateCouponManageMapper.selectByExample(example);
                RebateCouponManage manage = new RebateCouponManage();
                for (RebateCouponManage couponManage : couponManages) {
                    String[] split = couponManage.getStoreIds().split(",");
                    for (String s : split) {
                        Integer i = Integer.valueOf(s);
                        if (i.equals(storeId)) {
                            manage = couponManage;
                        }
                    }
                }
                userVo.setUserRebate(manage.getRebateOld().multiply(new BigDecimal("100")));
                userVo.setSuperId(superId).setRebateTopLimit(manage.getRebate().multiply(new BigDecimal("100"))).setType(0);
                return WXCodeApi.loginV4(openid,
                        securityProperties.getOauth2().getClient()[0].getClientId(),
                        securityProperties.getOauth2().getClient()[0].getClientSecret(), type,
                        userVo);
            }
            // 绑定成功后登录
            return WXCodeApi.login(openid,
                    securityProperties.getOauth2().getClient()[0].getClientId(),
                    securityProperties.getOauth2().getClient()[0].getClientSecret(), type);
        }
    }
    //解析电话号码
    public JSONObject getPhoneNumber(String session_key, String encryptedData, String iv) {
        byte[] dataByte = Base64.decode(encryptedData);

        byte[] keyByte = Base64.decode(session_key);

        byte[] ivByte = Base64.decode(iv);
        try {

            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    @Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
    public int bindPhone1(String phone, String openid, Integer client, Integer userId,Integer id) {
        if (userId != null) {
            // 添加邀请好友记录
            Integer inviteId = fireworksService.addInviteUser(id, userId);
            if (inviteId == 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "不能邀请自己或添加邀请记录失败");
            }
            // 邀请者获取礼花、添加使用记录
            AppUser user = appUserMapper.selectByPrimaryKey(id);
            int integer1 = fireworksService.updateFireworks(userId, 10, Constants.FIREWORKS_RECORD.ADDFRIEND, "邀请好友:" + user.getNickname(), inviteId);
            if (integer1 != 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "邀请者获得礼花失败");
            }
        }
        // 添加礼遇圈会员卡
        memberCardService.addMemberShipCard(id,new BigDecimal("0"),new BigDecimal("0"),new BigDecimal("0"),0,0);
        // 测试账号（添加测试商品到背包）
        if ("13110487948".equals(phone)) {
            //物品添加到背包
            List<ItemOrderDetail> list = itemOrderDetailMapper.selectByUserIdAndOrderNo(7, 1219091010108L);
            if (CollectionUtils.isNotEmpty(list)) {
                for (ItemOrderDetail itemOrderDetail : list) {
                    itemOrderDetail.setUserId(id);
                }
                bpService.addFromItemOrder(list);
            }
        }
        return id;
    }
}
