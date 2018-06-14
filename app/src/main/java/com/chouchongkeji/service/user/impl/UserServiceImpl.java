package com.chouchongkeji.service.user.impl;

import com.chouchongkeji.dao.user.AppUserMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.utils.KeyGenUtils;
import com.chouchongkeji.pojo.user.AppUser;
import com.chouchongkeji.properties.ServiceProperties;
import com.chouchongkeji.redis.MRedisTemplate;
import com.chouchongkeji.service.user.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linqin
 * @date 2018/6/5
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AppUserMapper appUserMapper;

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
     * @param s1     随机数
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @Override
    public Response preSentPwd(Integer userId, String s1) {
        // 取出用户信息
        AppUser user = appUserMapper.selectByPrimaryKey(userId);
        String iv = RandomStringUtils.randomAscii(16).trim();
        Map<String, Object> map = new HashMap<>();
        map.put("status", StringUtils.isBlank(user.getSentPwd()) ? 2 : 1);
        map.put("iv", iv);
        // 保存数据
        map.put("s1", s1);
        mRedisTemplate.set("dsfdsgewew" + userId, map, 300);
        return ResponseFactory.sucData(map);
    }


    /**
     * 赠送密码之前 请求获取赠送密码状态
     *
     * @param userId 用户信息
     * @param de     加密后的密码
     * @param time   随机字符串
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @Override
    public Response setSentPwd(Integer userId, String de, String time) {
        return null;
    }
}
