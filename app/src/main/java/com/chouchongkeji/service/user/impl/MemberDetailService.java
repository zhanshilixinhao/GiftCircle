package com.chouchongkeji.service.user.impl;

import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.yichen.auth.service.UserDetails;
import com.yichen.auth.verify.VerifyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
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
    private AppUserMapper appUserMapper;

    /**
     * 用户认证逻辑
     *
     * @param phone
     * @return
     * @throws UsernameNotFoundException
     * @author linqin
     * @date 2018/5/18
     */
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        if (StringUtils.isBlank(phone)) {
            throw new VerifyException("phone is empty");
        }
        AppUser memberInfo = appUserMapper.selectByPhone(phone);
        if (memberInfo == null) {
            throw new VerifyException("该账号未注册");
        }
        return new UserDetails(phone, memberInfo.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("member, ROLE_USER"),
                memberInfo.getId());
    }
}
