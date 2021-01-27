package com.chouchongkeji.dial.dao.v4;

import com.chouchongkeji.dial.pojo.v4.ElCouponListPayment;
import org.bouncycastle.crypto.Mac;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/10/19 15:42
 */
public interface ElCouponListPaymentMapper extends Mapper<ElCouponListPayment> , MySqlMapper<ElCouponListPayment> {
}
