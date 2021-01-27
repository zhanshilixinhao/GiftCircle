package com.chouchongkeji.dial.pojo.v4;

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
 * @Description: 礼包支付流水
 * @Author Lxh
 * @Date 2020/10/19 15:35
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "el_coupon_list_payment")
public class ElCouponListPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Byte type;

    private Long orderNo;

    private BigDecimal totalFee;

    private String platformNumber;

    private String seller;

    private String buyer;

    private Date created;

    private Date updated;


}
