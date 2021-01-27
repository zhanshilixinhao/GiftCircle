package com.chouchongkeji.service.v3.vo;


import com.chouchongkeji.dial.pojo.v3.Store;
import com.yichen.auth.jackson.ImgUrl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author linqin
 * @date 2019/10/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardVo {
    /**
     * 会员卡用户关联表id
     */
    private Integer id;

    private Integer membershipCardId;

    private Integer userId;

    private String phone;

    private BigDecimal balance;

    private Date created;

    private Long cardNo;

    private String title;

    private String summary;

    private String colour;

    @ImgUrl
    private String logo;

    private Byte type;

    private String storeIds;

    private List<Store> stores;

    private String grade;

    private String summaryGrade;// 等级说明

    private BigDecimal capital;

    private BigDecimal send;

    private String code;

    private Date updated;


}
