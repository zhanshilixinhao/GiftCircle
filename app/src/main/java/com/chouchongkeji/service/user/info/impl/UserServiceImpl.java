package com.chouchongkeji.service.user.info.impl;

import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.chouchongkeji.goexplore.utils.K;

import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.properties.ServiceProperties;
import com.chouchongkeji.dial.redis.MRedisTemplate;
import com.chouchongkeji.service.user.info.UserService;
import com.chouchongkeji.util.SentPwdUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author linqin
 * @date 2018/6/5
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ServiceProperties serviceProperties;

    @Autowired
    private MRedisTemplate mRedisTemplate;

    /**
     * 获取用户详细信息
     *
     * @param userId 用户id
     * @return
     * @author linqin
     * @date 2018/6/5
     */
    @Override
    public Response getProfile(Integer userId) {
        //根据用户信息获取用户详细信息
        AppUser appUser = appUserMapper.selectByUserId(userId);
        //判断用户信息是否存在
        if (appUser == null) {
            return ResponseFactory.err("用户信息不存在");
        }
        // 头像要加上服务器地址
        appUser.setPassword(null);
        appUser.setSentPwd(null);
        appUser.setAvatar(serviceProperties.getImgHost() + appUser.getAvatar());
        return ResponseFactory.sucData(appUser);

    }

    /**
     * 修改用户信息
     *
     * @param userId  用户id
     * @param appUser
     * @return
     */
    @Override
    public Response updateProfile(Integer userId, AppUser appUser) {
        AppUser newUser = new AppUser();
        newUser.setId(userId);

        // 判断字段是否为空
        if (StringUtils.isNotBlank(appUser.getNickname())) {
            newUser.setNickname(appUser.getNickname());
        }
        //判断头像是否为空
        if (StringUtils.isNotBlank(appUser.getAvatar())) {
            newUser.setAvatar(appUser.getAvatar());
        }//判断签名是否为空
        if (StringUtils.isNotBlank(appUser.getSignature())) {
            newUser.setSignature(appUser.getSignature());
        }//判断地区是否为空
        if (StringUtils.isNotBlank(appUser.getDistrict())) {
            newUser.setDistrict(appUser.getDistrict());
        }
        //判断年龄
        Integer age = appUser.getAge();
        if (age != null) {
            if (age >= 0) {
                newUser.setAge(age);
            } else {
                return ResponseFactory.err("年龄不能为负数，请重新填写");
            }
        }
        //判断性别
        Byte gender = appUser.getGender();
        if (gender != null) {
            if (gender <= 2 && gender >= 0) {
                newUser.setGender(gender);
            } else {
                return ResponseFactory.err("性别填写错误");
            }
        }
        int re = appUserMapper.updateByPrimaryKeySelective(newUser);
        if (re == 1) {
            return ResponseFactory.sucMsg("修改成功");
        }
        return ResponseFactory.err("修改失败");
    }

    /**
     * 赠送密码之前 请求获取赠送密码状态
     *
     * @param userId 用户信息
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @Override
    public Response preSentPwd(Integer userId) {
        // 取出用户信息
        AppUser user = appUserMapper.selectByPrimaryKey(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("status", StringUtils.isBlank(user.getSentPwd()) ? 2 : 1);
        map.put("s1", getRandomNum(userId));
        return ResponseFactory.sucData(map);
    }

    /**
     * 获取随机数字 32位
     *
     * @param userId 用户id
     * @return
     */
    private String getRandomNum(Integer userId) {
        String s1 = RandomStringUtils.randomNumeric(32);
        // 保存数据
        mRedisTemplate.setString(K.genKey(K.USER_SENT_PWD, userId), s1, 120);
        return s1;
    }


    /**
     * 赠送密码之前 请求获取赠送密码状态
     *
     * @param userId 用户信息
     * @param de     加密后的密码
     * @param time   随机字符串
     * @param client
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @Override
    public Response setSentPwd(Integer userId, String de, String time, Integer client) {
        // 取出加密数据
        String s1 = verifyRandomNum(userId);
        if (StringUtils.isBlank(s1)) {
            return ResponseFactory.err("操作过期,请重试!");
        }
        // 取出签名密钥
        String apiKey = ApiSignUtil.apiKey(client);
        // 解码
        String pwd = SentPwdUtil.decrypt(de, s1, apiKey, time);
        if (pwd == null) {
            return ResponseFactory.err("密码设置错误!");
        }
        // 更新用户的赠送密码
        AppUser user = new AppUser();
        user.setId(userId);
        user.setSentPwd(passwordEncoder.encode(pwd));
        int count = appUserMapper.updateByPrimaryKey(user);
        if (count == 1) {
            return ResponseFactory.sucMsg("密码设置成功!");
        }
        return ResponseFactory.err("密码设置失败!");
    }

    /**
     * 修改密码之前验证原密码是否正确
     *
     * @param userId 用户信息
     * @param de     加密后的密码
     * @param time   随机字符串
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @Override
    public Response changePwdVerify(Integer userId, String de, String time, Integer client) {
        // 取出加密数据
        String s1 = verifyRandomNum(userId);
        if (StringUtils.isBlank(s1)) {
            return ResponseFactory.err("操作过期,请重试!");
        }
        // 取出签名密钥
        String apiKey = ApiSignUtil.apiKey(client);
        // 解码
        String pwd = SentPwdUtil.decrypt(de, s1, apiKey, time);
        if (pwd == null) {
            return ResponseFactory.err("密码错误!");
        }
        // 取出用户的赠送密码
        AppUser user = appUserMapper.selectByPrimaryKey(userId);
        // 验证密码
        if (passwordEncoder.matches(pwd, user.getSentPwd())) {
            // 保存验证状态
            String key = UUID.randomUUID().toString();
            mRedisTemplate.setString(key, "true", 60);
            Map<String, String> map = new HashMap<>();
            map.put("key", key);
            map.put("s1", getRandomNum(userId));
            return ResponseFactory.suc("验证成功!", map);
        }
        return ResponseFactory.err("密码错误!");
    }

    /**
     * 验证随机字符串
     *
     * @param userId 用户id
     * @return
     */
    private String verifyRandomNum(Integer userId) {
        String key = K.genKey(K.USER_SENT_PWD, userId);
        String s1 = mRedisTemplate.getString(key);
        mRedisTemplate.del(key);
        return s1;
    }

    /**
     * 修改赠送密码
     *
     * @param userId 用户信息
     * @param de     加密后的密码
     * @param time   随机字符串
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @Override
    public Response changePwd(Integer userId, String de, String time, Integer client, String key) {
        // 判断是否校验过密码
        String value = mRedisTemplate.getString(key);
        if (StringUtils.isBlank(value)) {
            return ResponseFactory.err("操作无效或超时!");
        }
        // 取出加密数据
        String s1 = verifyRandomNum(userId);
        if (StringUtils.isBlank(s1)) {
            return ResponseFactory.err("操作过期,请重试!");
        }
        // 取出签名密钥
        String apiKey = ApiSignUtil.apiKey(client);
        // 解码
        String pwd = SentPwdUtil.decrypt(de, s1, apiKey, time);
        if (pwd == null) {
            return ResponseFactory.err("密码无效!");
        }
        // 更新用户的赠送密码
        AppUser user = new AppUser();
        user.setId(userId);
        user.setSentPwd(passwordEncoder.encode(pwd));
        int count = appUserMapper.updateByPrimaryKey(user);
        if (count == 1) {
            return ResponseFactory.sucMsg("密码修改成功!");
        }
        return ResponseFactory.err("密码修改失败!");
    }
}
