package com.chouchongkeji.service.user.info.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.dao.user.ThirdAccountMapper;
import com.chouchongkeji.dial.dao.user.UserPreferenceMapper;
import com.chouchongkeji.dial.dao.user.memo.MomentMapper;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.dial.pojo.user.ThirdAccount;
import com.chouchongkeji.dial.pojo.user.UserPreference;
import com.chouchongkeji.dial.pojo.user.memo.Moment;
import com.chouchongkeji.register.UserRegister;
import com.yichen.auth.redis.MRedisTemplate;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.chouchongkeji.goexplore.utils.K;
import com.chouchongkeji.goexplore.utils.Utils;
import com.chouchongkeji.service.user.friend.FriendService;
import com.chouchongkeji.service.user.friend.vo.FriendVo;
import com.chouchongkeji.service.user.friend.vo.MediaVo;
import com.chouchongkeji.service.user.info.UserService;
import com.chouchongkeji.service.user.info.vo.UserInfoVo;
import com.chouchongkeji.service.user.info.vo.UserTagVo;
import com.chouchongkeji.util.SentPwdUtil;
import com.chouchongkeji.util.UserInfoUtils;
import io.rong.models.response.TokenResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private MRedisTemplate mRedisTemplate;

    @Autowired
    private UserPreferenceMapper userPreferenceMapper;

    @Autowired
    private MomentMapper momentMapper;

    @Autowired
    private FriendService friendService;

    @Autowired
    private ThirdAccountMapper thirdAccountMapper;


    /**
     * 获取用户的详细信息
     *
     * @param userId       用户信息
     * @param targetUserId 查看的用户id
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @Override
    public Response getInfo(Integer userId, Integer targetUserId) {
        // 取出用户信息
        AppUser user = appUserMapper.selectByUserId(targetUserId);
        if (user == null){
            return ResponseFactory.err("该用户不存在");
        }
        UserInfoVo vo = new UserInfoVo();
        vo.setUserId(user.getId());
        vo.setPhone(Utils.getPhone(user.getPhone()));
        vo.setAge(user.getAge());
        vo.setNickname(user.getNickname());
        vo.setAvatar(user.getAvatar());
        vo.setGender(user.getGender());
        vo.setSignature(user.getSignature());
        vo.setDistrict(user.getDistrict());
        vo.setWxid(user.getWxid());
        // 取出标签信息
        UserPreference preference = userPreferenceMapper.selectByPrimaryKey(targetUserId);
        if (preference != null && StringUtils.isNotBlank(preference.getTags())) {
            vo.setTags(JSON.parseObject(preference.getTags(), new TypeReference<HashSet<UserTagVo>>() {
            }));
        } else {
            vo.setTags(new HashSet<>());
        }
        int isFriend = userId.equals(targetUserId) ? 1 : 2;
        if (isFriend == 2) {
            FriendVo friend = friendService.isFriend(userId, targetUserId);
            if (friend != null) {
                isFriend = 1;
                vo.setRemark(friend.getRemark());
                vo.setRelationship(friend.getRelationship());
                vo.setGroupId(friend.getGroupId());
                vo.setGroupName(friend.getGroupName());
            }
        }
        vo.setIsFriend(isFriend);
        // 判断是不是好友关系
        if (isFriend == 1) {
            // 取出最新的三张照片
            List<Moment> moments = momentMapper.selectRecentByUserId(targetUserId);
            if (CollectionUtils.isNotEmpty(moments)) {
                List<MediaVo> recentMedias = new ArrayList<>();
                List<MediaVo> mediaVos;
                // 遍历最新三个秀秀
                for (Moment moment : moments) {
                    boolean f = false;
                    // 取出秀秀里面的图片或视频
                    if (StringUtils.isNotBlank(moment.getMedias())) {
                        mediaVos = JSON.parseObject(moment.getMedias(), new TypeReference<List<MediaVo>>() {
                        });
                        for (MediaVo mediaVo : mediaVos) {
                            // 加入最新的视频或图片里面
                            if (recentMedias.size() < 3) {
                                recentMedias.add(mediaVo);
                            } else {
                                f = true;
                                break;
                            }
                        }
                    }
                    if (f) {
                        break;
                    }
                }
                vo.setRecentMoments(recentMedias);
            }
        }
        return ResponseFactory.sucData(vo);
    }

    /**
     * 扫描用户的二位啊
     *
     * @param userId 用户id
     * @param qrcode 二维码内容
     * @return
     */
    @Override
    public Response scanQrcode(Integer userId, String qrcode) {
        // 1.1-解析二维码内容
        Integer targetUserId = UserInfoUtils.decrypt(qrcode);
        if (targetUserId == null) {
            return ResponseFactory.err("无效的二维码!");
        }
        return getInfo(userId, targetUserId);
    }

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
        appUser.setAvatar(appUser.getAvatar());
        appUser.setQrcode(UserInfoUtils.encrypt(userId));
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
     * 修改电话号码
     *
     * @param userId
     * @return
     * @author linqin
     * @date 2018/6/5
     */
    @Override
    public Response modifyPhone(Integer userId, String phone) {
        AppUser appUser = appUserMapper.selectByUserId(userId);
        if (appUser == null) {
            return ResponseFactory.err("该账号不存在");
        }
        if (StringUtils.isNotBlank(appUser.getPhone()) && appUser.getPhone().equals(phone)) {
            return ResponseFactory.err("该账号已绑定该手机号");
        }
        AppUser user = appUserMapper.selectByPhone(phone);
        if (user != null) {
            return ResponseFactory.err("该手机号已绑定其他账号");
        }
        //修改第三方账号
        List<ThirdAccount>  thirdAccount = thirdAccountMapper.selectByPhone(appUser.getPhone());
        if (CollectionUtils.isNotEmpty(thirdAccount)) {
            for (ThirdAccount account : thirdAccount) {
                account.setPhone(phone);
                int i1 = thirdAccountMapper.updateByPrimaryKeySelective(account);
                if (i1 < 1) {
                    return ResponseFactory.err("修改失败");
                }
            }
        }
        //修改用户账号和手机号
        appUser.setPhone(phone);
        appUser.setAccount(phone);
        int i = appUserMapper.updateByPrimaryKeySelective(appUser);
        if (i < 1) {
            return ResponseFactory.err("修改失败");
        }
        return ResponseFactory.sucMsg("修改成功");
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
        if (pwd == null) { // 670B14728AD9902AECBA32E22FA4F6BD
            return ResponseFactory.err("密码设置错误!");
        }
        // 更新用户的赠送密码
        AppUser user = new AppUser();
        user.setId(userId);
        user.setSentPwd(passwordEncoder.encode(pwd));
        int count = appUserMapper.updateByPrimaryKeySelective(user);
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
            return ResponseFactory.err("密码无效!");
        }
        // 取出用户的赠送密码
        AppUser user = appUserMapper.selectByPrimaryKey(userId);
        // 验证密码
        if (passwordEncoder.matches(pwd, user.getSentPwd())) {
            // 保存验证状态
            String key = UUID.randomUUID().toString();
            mRedisTemplate.setString(key, "true", 6000);
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
        int count = appUserMapper.updateByPrimaryKeySelective(user);
        if (count == 1) {
            return ResponseFactory.sucMsg("密码修改成功!");
        }
        return ResponseFactory.err("密码修改失败!");
    }

    /**
     * 找回密码
     *
     * @param userId 用户信息
     * @param phone  电话号码
     * @param code   短信验证码
     * @param de     加密后的密码
     * @param time   随机字符串
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @Override
    public Response findSendPwd(Integer userId, Integer client, String phone, String code, String de, String time) {
        // 根据userId查询用户信息
        AppUser appUser = appUserMapper.selectByUserId(userId);
        if (!StringUtils.equals(appUser.getPhone(), phone)) {
            return ResponseFactory.err("该号码与注册号码不符合");
        }
        Response response = setSentPwd(userId, de, time, client);
        return response;
    }

    /**
     * 用户隐私设置
     *
     * @param userId
     * @param isHide      隐私设置 1 不隐藏，2 隐藏
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @Override
    public Response setHide(Integer userId, Byte isHide) {
        AppUser user = appUserMapper.selectByPrimaryKey(userId);
        if (user == null){
            return ResponseFactory.err("该账号不存在");
        }
        user.setIsHide(isHide);
        int i = appUserMapper.updateByPrimaryKeySelective(user);
        if (i<1){
            return ResponseFactory.err("设置失败");
        }
        return ResponseFactory.sucMsg("设置成功");
    }



    /**
     * 融云聊天
     *
     * @param userId   用户id
     * @param nickname 用户昵称
     * @param avatar   用户头像
     * @return
     * @author linqin
     * @date 2019/6/21
     */
    @Override
    public TokenResult ryUserRegister(Integer userId, String nickname, String avatar) throws Exception{
        TokenResult register = UserRegister.register(userId, nickname, avatar);
        return register;
    }


}
