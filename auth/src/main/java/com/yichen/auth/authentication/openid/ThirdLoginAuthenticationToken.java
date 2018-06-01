package com.yichen.auth.authentication.openid;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 第三方登录 认证token
 *
 * @author yichenshanren
 * @date 2017/11/30
 */

public class ThirdLoginAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -6698452004710616425L;

    // ~ Instance fields
    // ================================================================================================

    // 第三方登录的openId
    private Object principal;
    // 第三方账号类型 2 qq 3 微信 4 微博
    private int accType;

    // ~ Constructors
    // ===================================================================================================

    /**
     * This constructor can be safely used by any code that wishes to create a
     * <code>SmsAuthenticationToken</code>, as the {@link #isAuthenticated()}
     * will return <code>false</code>.
     */
    public ThirdLoginAuthenticationToken(String phone, int accType) {
        super(null);
        this.principal = phone;
        this.accType = accType;
        setAuthenticated(false);
    }

    /**
     * This constructor should only be used by <code>AuthenticationManager</code> or
     * <code>AuthenticationProvider</code> implementations that are satisfied with
     * producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
     * authentication token.
     *
     * @param principal
     */
    public ThirdLoginAuthenticationToken(Object principal, int accType,
                                         Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.accType = accType;
        super.setAuthenticated(true); // must use super, as we override
    }

    // ~ Methods
    // ========================================================================================================

    public Object getCredentials() {
        return accType;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }

    public int getAccType() {
        return accType;
    }

    public void setAccType(int accType) {
        this.accType = accType;
    }
}
