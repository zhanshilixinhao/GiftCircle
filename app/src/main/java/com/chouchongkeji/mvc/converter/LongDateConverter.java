package com.chouchongkeji.mvc.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * @author yichenshanren
 * @date 2018/6/25
 */

public class LongDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String str) {
        if (!StringUtils.isNumeric(str)) {
            return null;
        }
        Long time = Long.parseLong(str);
//        if (time < 1529815765455L) return null;
        return new Date(time);
    }
}
