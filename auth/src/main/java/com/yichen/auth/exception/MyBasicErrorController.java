package com.yichen.auth.exception;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorProperties.IncludeStacktrace;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.boot.context.embedded.AbstractEmbeddedServletContainerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Basic global error {@link Controller}, rendering {@link ErrorAttributes}. More specific
 * errors can be handled either using Spring MVC abstractions (e.g.
 * {@code @ExceptionHandler}) or by adding servlet
 * {@link AbstractEmbeddedServletContainerFactory#setErrorPages container error pages}.
 *
 * @author Dave Syer
 * @author Phillip Webb
 * @author Michael Stummvoll
 * @author Stephane Nicoll
 * @fix yichen
 * @date 2017/11/29
 * @see ErrorAttributes
 * @see ErrorProperties
 */
//@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class MyBasicErrorController extends AbstractErrorController {

    private Logger logger = LoggerFactory.getLogger(MyBasicErrorController.class);

    private final ErrorProperties errorProperties;

    /**
     * Create a new {@link MyBasicErrorController} instance.
     *
     * @param errorAttributes the error attributes
     * @param errorProperties configuration properties
     */
    public MyBasicErrorController(ErrorAttributes errorAttributes,
                                  ErrorProperties errorProperties) {
        this(errorAttributes, errorProperties,
                Collections.<ErrorViewResolver>emptyList());
    }

    /**
     * Create a new {@link MyBasicErrorController} instance.
     *
     * @param errorAttributes    the error attributes
     * @param errorProperties    configuration properties
     * @param errorViewResolvers error view resolvers
     */
    public MyBasicErrorController(ErrorAttributes errorAttributes,
                                  ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
        Assert.notNull(errorProperties, "ErrorProperties must not be null");
        this.errorProperties = errorProperties;
    }

    @Override
    public String getErrorPath() {
        return this.errorProperties.getPath();
    }

    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
                request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
        response.setStatus(status.value());
        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
        return (modelAndView == null ? new ModelAndView("error", model) : modelAndView);
    }

    @RequestMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Response error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
//		logger.info("保存");
//		ResponseEntity entity = new ResponseEntity<ExploreMapTheme<String, Object>>(body, status);
        int code = status != null ? status.value() : 1;
        return ResponseFactory.errMsgPath(code, String.format("错误:%s,异常:%s,message:%s",
                String.valueOf(body.get("error")), String.valueOf(body.get("exception")),
                String.valueOf(body.get("message"))), String.valueOf(body.get("path")));
    }

    /**
     * Determine if the stacktrace attribute should be included.
     *
     * @param request  the source request
     * @param produces the media type produced (or {@code MediaType.ALL})
     * @return if the stacktrace attribute should be included
     */
    protected boolean isIncludeStackTrace(HttpServletRequest request,
                                          MediaType produces) {
        IncludeStacktrace include = getErrorProperties().getIncludeStacktrace();
        if (include == IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    /**
     * Provide access to the error properties.
     *
     * @return the error properties
     */
    protected ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }

}
