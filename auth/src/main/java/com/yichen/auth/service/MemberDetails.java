package com.yichen.auth.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author yichenshanren
 * @date 2017/12/3
 */

public class MemberDetails extends User {

    private Integer userId;

    public MemberDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                         Integer memberId) {
        super(username, password, authorities);
        this.userId = memberId;
    }

    public MemberDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public Integer getMemberId() {
        return userId;
    }

}
