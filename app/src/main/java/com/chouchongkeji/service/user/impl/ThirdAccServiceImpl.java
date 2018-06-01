package com.chouchongkeji.service.user.impl;

import com.chouchongkeji.dao.user.UserBaseMapper;
import com.chouchongkeji.dao.user.UserThirdMapper;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.pojo.user.UserBase;
import com.chouchongkeji.pojo.user.UserThird;
import com.chouchongkeji.util.Constants;
import com.yichen.auth.model.DefaultThirdAccDetail;
import com.yichen.auth.model.ThirdAccDetail;
import com.yichen.auth.service.ThirdAccService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 第三方账号相关
 *登录
 * 绑定手机
 * @author linqin
 * @date 2018/5/21
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class ThirdAccServiceImpl implements ThirdAccService {

    @Autowired
    private UserThirdMapper userThirdMapper;

    @Autowired
    private UserBaseMapper userBaseMapper;

    /**
     * 获取第三方账号信息
     * @param openId openId
     * @param type   账号类型 1-手机，2-qq，3-微信，4-微博
     * @return
     * @author linqin
     * @date 2018/5/21
     */
    @Override
    public ThirdAccDetail getOpenAccDetail(String openId, int type) {
        //获取第三方账号信息
        UserThird userThird = userThirdMapper.selectByOpenIdAndType(openId,type);
        if (userThird ==null){
            return null;
        }
        DefaultThirdAccDetail detail = new DefaultThirdAccDetail(
                userThird.getOpenId(),
                userThird.getPhone(),
                userThird.getType()
        );
        return detail;
    }

    /**
     * 创建第三方账号
     * 绑定手机号
     * @param openId  第三代账号
     * @param type  账号类型 1-手机，2-qq，3-微信，4-微博
     * @param phone 用户名
     * @return
     * @author linqin
     * @date 2018/5/21
     */
    @Override
    public Response addOpenAccDetail(String openId, int type, String phone) {
        //获取第三方账号信息
        UserThird userThird1 = userThirdMapper.selectByOpenIdAndType(openId,type);
        //如果账号已经存在，不能再绑定
        if (userThird1 != null){
            return ResponseFactory.errMsg(ErrorCode.PHONE_EXISTED.getCode(),"该手机号已经绑定其他账号");
        }
        //判断用户名是否被注册
        UserBase userBase = userBaseMapper.selectByAccount(phone);
        //该手机号未被注册
        int count = 0;
        if (userBase == null){
            //直接注册一个账号
            UserBase user = assembleMember(phone,type);
            count = userBaseMapper.insert(user);
            if (count == 0){
                return ResponseFactory.err("绑定手机号失败，创建账号失败");
            }
        }
        //添加第三方账号信息
        userThird1 = new UserThird();
        userThird1.setOpenId(openId);
        userThird1.setPhone(phone);
        userThird1.setType((byte)type);
        count = userThirdMapper.insert(userThird1);
        if (count == 0){
            //如果添加失败，回滚
            throw new ServiceException(ErrorCode.ERROR.getCode(), "绑定失败");
        }
            //绑定成功
        return ResponseFactory.suc();
    }


    /**
     * 创建一个用户账号
     * @param phone
     * @param type
     * @return
     * @author linqin
     * @date 2018/5/21
     */
    private UserBase assembleMember(String phone,int type){
        //创建新用户
        UserBase memberInfo = new UserBase();
        //用户名
        memberInfo.setAccount(phone);
        //密码
        memberInfo.setPassword(RandomStringUtils.randomNumeric(10));
        //电话
        memberInfo.setPhone(phone);
        //默认头像
        memberInfo.setAvatar(Constants.DEFALUT_AVATAR);
        //默认昵称
        memberInfo.setNickname(Constants.getRandomName());
        //注册类型
        memberInfo.setType((byte)type);
        return memberInfo;
    }

}
