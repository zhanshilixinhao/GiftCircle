package com.chouchongkeji.service.user.impl;

import com.chouchongkeji.dao.user.AppUserMapper;
import com.chouchongkeji.pojo.user.AppUser;
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
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        if (StringUtils.isBlank(phone)) {
            throw new VerifyException("phone is empty");
        }
        AppUser memberInfo = appUserMapper.selectByPhone(phone);
        if (memberInfo == null) {
            throw new VerifyException("该账号未注册");
        }
        return new MemberDetails(phone, memberInfo.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("member, ROLE_USER"),
                memberInfo.getId());
    }
}
