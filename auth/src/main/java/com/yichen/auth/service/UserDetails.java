package com.yichen.auth.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author yichenshanren
 * @date 2017/12/3
 */

public class UserDetails extends User {

    private Integer userId;

    public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                       Integer memberId) {
        super(username, password, authorities);
        this.userId = memberId;
    }

    public UserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public Integer getMemberId() {
        return userId;
    }

    public Integer getUserId(){
        return userId;
    }
}
