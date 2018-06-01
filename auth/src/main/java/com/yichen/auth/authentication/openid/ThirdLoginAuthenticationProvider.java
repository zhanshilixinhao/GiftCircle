package com.yichen.auth.authentication.openid;

import com.chouchongkeji.goexplore.common.ErrorCode;
import com.yichen.auth.model.ThirdAccDetail;
import com.yichen.auth.service.ThirdAccService;
import com.yichen.auth.verify.VerifyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 第三方登录
 *
 * @author yichenshanren
 * @date 2017/11/26
 */
public class ThirdLoginAuthenticationProvider implements AuthenticationProvider {

    private Logger logger = LoggerFactory.getLogger(ThirdLoginAuthenticationProvider.class);

    // 获取用户信息
    private UserDetailsService userDetailsService;
    // 获取第三方账号信息
    private ThirdAccService thirdAccService;

    /**
     * Performs authentication with the same contract as
     * {@link org.springframework.security.authentication.AuthenticationManager#authenticate(Authentication)}
     * .
     *
     * @param authentication the authentication request object.
     * @return a fully authenticated object including credentials. May return
     * <code>null</code> if the <code>AuthenticationProvider</code> is unable to support
     * authentication of the passed <code>Authentication</code> object. In such a case,
     * the next <code>AuthenticationProvider</code> that supports the presented
     * <code>Authentication</code> class will be tried.
     * @throws AuthenticationException if authentication fails.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ThirdLoginAuthenticationToken token = (ThirdLoginAuthenticationToken) authentication;
        // 获取第三方账号绑定信息
        ThirdAccDetail thirdAccDetail = thirdAccService.getOpenAccDetail((String) token.getPrincipal(), token.getAccType());
        // 如果第三方账号不存在
        if (thirdAccDetail == null) {
            throw new VerifyException(ErrorCode.NEED_BIND_PHONE.getCode(), "需要绑定手机号!");
        }
        // 获取用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(thirdAccDetail.getUsername());
        // 如果用户信息不存在
        if (userDetails == null) {
            throw new VerifyException(ErrorCode.USER_NOT_EXIST.getCode(), "用户信息不存在");
        }
        // 构造新的的SmsAuthenticationToken
//        UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken();
        ThirdLoginAuthenticationToken thirdLoginAuthenticationToken =
                new ThirdLoginAuthenticationToken(
                        userDetails, // 开放平台di
                        thirdAccDetail.getAccType(), // 第三方账号类型
                        userDetails.getAuthorities()
                );
        thirdLoginAuthenticationToken.setDetails(userDetails);
        return thirdLoginAuthenticationToken;
    }

    /**
     * Returns <code>true</code> if this <Code>AuthenticationProvider</code> supports the
     * indicated <Code>Authentication</code> object.
     * <p>
     * Returning <code>true</code> does not guarantee an
     * <code>AuthenticationProvider</code> will be able to authenticate the presented
     * instance of the <code>Authentication</code> class. It simply indicates it can
     * support closer evaluation of it. An <code>AuthenticationProvider</code> can still
     * return <code>null</code> from the {@link #authenticate(Authentication)} method to
     * indicate another <code>AuthenticationProvider</code> should be tried.
     * </p>
     * <p>
     * Selection of an <code>AuthenticationProvider</code> capable of performing
     * authentication is conducted at runtime the <code>ProviderManager</code>.
     * </p>
     *
     * @param authentication
     * @return <code>true</code> if the implementation can more closely evaluate the
     * <code>Authentication</code> class presented
     * <p>
     * 由AuthenticationManager调用
     * 此处就判断传入的authentication是不是SmsAuthenticationToken
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return ThirdLoginAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void setThirdAccService(ThirdAccService thirdAccService) {
        this.thirdAccService = thirdAccService;
    }
}