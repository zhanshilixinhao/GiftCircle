package com.chouchongkeji.dial.pojo.v4;
import	java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/12/29 22:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "rebate_coupon_manage")
public class RebateCouponManage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String summary;

    private String storeIds;

    private Integer adminId;

    private Byte status;

    private BigDecimal rebateNew;

    private BigDecimal rebateOld;

    private Date created;

    private Date updated;

    private BigDecimal rebate;
}
