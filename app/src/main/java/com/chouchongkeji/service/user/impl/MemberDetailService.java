package com.chouchongkeji.service.user.impl;

import com.chouchongkeji.dao.user.UserBaseMapper;
import com.chouchongkeji.pojo.user.UserBase;
import com.yichen.auth.service.MemberDetails;
import com.yichen.auth.verify.VerifyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author linqin
 * @date 2018/5/18
 */
@Service
public class MemberDetailService implements UserDetailsService {

    @Autowired
    private UserBaseMapper userBaseMapper;

    /**
     * 用户认证逻辑
     * @param username
     * @return
     * @throws UsernameNotFoundException
     * @author linqin
     * @date 2018/5/18
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)){
            throw new VerifyException("username is empty");
        }
        UserBase memberInfo = userBaseMapper.selectByAccount(username);
        if (memberInfo == null){
            throw new VerifyException("该账号未注册");
        }
        return new MemberDetails(username,memberInfo.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("member, ROLE_USER"),
                memberInfo.getId());
    }
}
