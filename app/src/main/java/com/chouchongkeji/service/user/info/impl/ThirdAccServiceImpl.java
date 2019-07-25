package com.chouchongkeji.service.user.info.impl;

import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.dao.user.ThirdAccountMapper;
import com.yichen.auth.redis.MRedisTemplate;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.dial.pojo.user.ThirdAccount;
import com.chouchongkeji.service.wxapi.WXCodeApi;
import com.chouchongkeji.service.wxapi.WXResult;
import com.chouchongkeji.util.Constants;
import com.yichen.auth.model.DefaultThirdAccDetail;
import com.yichen.auth.model.ThirdAccDetail;
import com.yichen.auth.service.ThirdAccService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 第三方账号相关
 * 登录
 * 绑定手机
 *
 * @author linqin
 * @date 2018/5/21
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class ThirdAccServiceImpl implements ThirdAccService {

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private ThirdAccountMapper thirdAccountMapper;

    /**
     * 获取第三方账号信息
     *
     * @param openId openId
     * @param type   账号类型（默认1） 1-微信app,2-微信小程序
     * @return
     * @author linqin
     * @date 2018/5/21
     */
    @Override
    public ThirdAccDetail getOpenAccDetail(String openId, int type) {
        //获取第三方账号信息
        ThirdAccount thirdAccount = thirdAccountMapper.selectByOpenIdAndType(openId, type);
        if (thirdAccount == null) {
            return null;
        }
        DefaultThirdAccDetail detail = new DefaultThirdAccDetail(
                thirdAccount.getPhone(),
                thirdAccount.getOpenId(),
                thirdAccount.getType()
        );
        return detail;
    }

    /**
     * 创建第三方账号
     * 绑定手机号
     *
     * @param openId 第三代账号
     * @param type   账号类型（默认1） 1-微信app,2-微信小程序
     * @param phone  用户名
     * @return
     * @author linqin
     * @date 2018/5/21
     */
    @Override
    public Response addOpenAccDetail(String openId, int type, String phone) {
        // 获取第三方账号信息
        ThirdAccount userThird1 = thirdAccountMapper.selectByPhoneAndType(phone, type);
        if (userThird1 != null) {
            return ResponseFactory.errMsg(ErrorCode.PHONE_EXISTED.getCode(), "该手机号已经绑定其他账号");
        }
         userThird1 = thirdAccountMapper.selectByOpenIdAndType(openId, type);
        // 如果账号已经存在，不能再绑定
        if (userThird1 != null) {
            return ResponseFactory.errMsg(ErrorCode.PHONE_EXISTED.getCode(), "该手机号已经绑定其他账号");
        }
        // 判断用户名是否被注册
        AppUser userBase = appUserMapper.selectByPhone(phone);
        // 该手机号未被注册
        int count = 0;
        Integer id = null;
        if (userBase == null) {
            // 直接注册一个账号

            AppUser user = assembleMember(phone, type, openId);
            count = appUserMapper.insert(user);
            if (count == 0) {
                return ResponseFactory.err("绑定手机号失败，创建账号失败");
            }
            id = user.getId();
        }else {
            id = userBase.getId();
        }
        // 添加第三方账号信息
        userThird1 = new ThirdAccount();
        userThird1.setOpenId(openId);
        userThird1.setPhone(phone);
        userThird1.setType((byte) type);
        count = thirdAccountMapper.insert(userThird1);
        if (count == 0) {
            // 如果添加失败，回滚
            throw new ServiceException(ErrorCode.ERROR.getCode(), "绑定失败");
        }
        // 绑定成功
        return ResponseFactory.sucData(id);
    }



    @Autowired
    private MRedisTemplate mRedisTemplate;

    /**
     * 创建一个用户账号
     *
     * @param phone
     * @param type
     * @param openId
     * @return
     * @author linqin
     * @date 2018/5/21
     */
    private AppUser assembleMember(String phone, int type, String openId) {
        // 如果是app登录获取用户信息
        WXResult userInfo = new WXResult();
        if (type == 1) {
            String access = mRedisTemplate.getString(openId);
            userInfo = WXCodeApi.getUserInfo(access, openId);
        }


        //创建新用户
        AppUser memberInfo = new AppUser();
        //账号
        memberInfo.setAccount(phone);
        //密码
        memberInfo.setPassword(RandomStringUtils.randomNumeric(10));
        //电话
        memberInfo.setPhone(phone);
        //默认头像、
        memberInfo.setAvatar(StringUtils.isBlank(userInfo.getHeadimgurl()) ? Constants.DEFALUT_AVATAR : userInfo.getHeadimgurl());
        //默认昵称
        memberInfo.setNickname(StringUtils.isBlank(userInfo.getNickname()) ? Constants.getRandomName() : userInfo.getNickname());
        //年龄
        memberInfo.setAge(0);
        //性别
        memberInfo.setGender(userInfo.getSex() == null ? 1 : userInfo.getSex());
        //个性签名
        memberInfo.setSignature("个性签名");
        //地区
        memberInfo.setDistrict( Constants.DEFALUT_DISTRICT );
        //状态
        memberInfo.setStatus((byte) 1);
        return memberInfo;
    }

}
