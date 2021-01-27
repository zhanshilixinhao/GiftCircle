package com.chouchongkeji.dial.pojo.v4;



import com.yichen.auth.jackson.ImgUrl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;


/**
 * @description: 分享劵实体类
 * @author: LxH
 * @time: 2020/10/15 0015 下午 2:02
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "share_coupon")
public class ShareCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String summary;

    @ImgUrl
    private String logo;

    private String storeIds;

    private Integer adminId;

    private Byte type;

    private Byte status;

    private Date date;
    private Date startTime;

    private Date updated;

    private Date created;

    private Integer day;

    private Long totality;

    private Integer ceiling;

    private String rule;

    @Transient
    private String storeName;

    private Long total;

    @Transient
    private String start;

    @Transient
    private String end;

    @Transient
    private String amount;

    public static final String TYPE = "type";

    public static final String STATUS = "status";

    public static final String ADMIN_ID = "adminId";

}
