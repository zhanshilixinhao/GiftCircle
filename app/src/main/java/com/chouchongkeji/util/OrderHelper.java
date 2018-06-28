package com.chouchongkeji.util;

import com.chouchongkeji.goexplore.utils.DateUtil;
import com.chouchongkeji.dial.redis.MRedisTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yichenshanren
 * @date 2018/6/6
 */

@Component
public class OrderHelper {

    private static final String KEY_PREFIX = "JFK434-JDKS.32432-rewr432432";

    private static final long INIT_NO = 100;

    @Autowired
    private MRedisTemplate mRedisTemplate;

    /**
     * 生成订单号
     *
     * @param client 客户端类型 1 Android 2 iOS 3 小程序
     * @param type   订单类型 1 充值 2 商品 3 虚拟商品 4 提货订单
     * @return
     * @author yichenshanren
     * @date 2018/6/6
     */
    public Long genOrderNo(Integer client, Integer type) {
        String no = mRedisTemplate.getString(KEY_PREFIX);
        if (StringUtils.isBlank(no)) {
            no = String.valueOf(INIT_NO);
            mRedisTemplate.setString(KEY_PREFIX, no, (int) (DateUtil.tomZeroDiff() / 1000));
        }
        mRedisTemplate.template().opsForValue().increment(KEY_PREFIX, 1);
        return Long.parseLong(String.format("%s%s%s%s", client, type, DateUtil.today("yyMMddHH"), no));
    }


}
