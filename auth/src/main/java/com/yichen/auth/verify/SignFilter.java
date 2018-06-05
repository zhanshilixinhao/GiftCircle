package com.yichen.auth.verify;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.yichen.auth.properties.SecurityProperties;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 接口签名过滤器
 *
 * @author yichenshanren
 * @date 2017/12/3
 */
@Component
public class SignFilter extends OncePerRequestFilter {

    private boolean needSign;

    private static List<AntPathRequestMatcher> urls = new ArrayList<>();

    @Autowired
    public SignFilter(SecurityProperties securityProperties) {
        super();
        needSign = securityProperties.getSign().isNeedSign();
        if (ArrayUtils.isNotEmpty(securityProperties.getSign().getUrls())) {
            for (String url : securityProperties.getSign().getUrls()) {
                urls.add(new AntPathRequestMatcher(url));
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!needSign || isMatch(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        String msg = "签名错误:";
        try {
            // 检查必传参数
            String usign = request.getParameter(ApiSignUtil.SIGN);
            String time = request.getParameter(ApiSignUtil.TIME);
            if (StringUtils.isNoneBlank(usign, time)) {
                // 获取请求中的所有参数
                Map<String, String> map = ApiSignUtil.getParams(request);
                // 获取签名值
                Map<String, String> sign = ApiSignUtil.sign(map,
                        ApiSignUtil.ANDROID, ApiSignUtil.IOS, ApiSignUtil.WXMINI);
                // 用户传的签名
                // 校验是否和android签名匹配
                int re = ApiSignUtil.validateSign(usign, sign);
                if (re != 0) {
                    request.setAttribute("client", re);
                    filterChain.doFilter(request, response);
                    return;
                }
            } else {
                msg = msg + "缺少签名参数";
            }
        } catch (Throwable e) {
            e.printStackTrace();
            msg = msg + e.getMessage();
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(ResponseFactory.errSign(msg)));
    }

    private boolean isMatch(HttpServletRequest request) {
        for (AntPathRequestMatcher matcher : urls) {
            if (matcher.matches(request)) {
                return true;
            }
        }
        return false;
    }
}
