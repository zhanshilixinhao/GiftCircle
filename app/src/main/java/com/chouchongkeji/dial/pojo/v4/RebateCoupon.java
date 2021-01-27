package com.chouchongkeji.dial.pojo.v4;

import com.yichen.auth.jackson.ImgUrl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/10/21 11:03
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rebate_coupon")
public class RebateCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal rebate;

    private Integer userId;

    private Integer storeId;

    private Date created;

    private Date updated;

    private Byte status;

    private Byte isEnd;

    @ImgUrl
    private String qrCode;

    private Date firstTime;

    private Date endTime;

    @Transient
    private String storeName;

    @ImgUrl
    @Transient
    private String storeLogo;

    @Transient
    private String storeAddress;

    @Transient
    private Integer superId;

    @Transient
    private String countDown;

    @Transient
    private List<RebateCouponBeInvited> rebateCouponBeInvitedList;

    @Transient
    private BigDecimal newRebate;

    @Transient
    private BigDecimal oldRebate;

    @Transient
    private String title;

    public static final String USER_ID = "userId";

    public static final String STATUS = "status";

    public static final String STORE_ID = "storeId";

    public static final String IS_END = "isEnd";

}
