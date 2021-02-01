package com.chouchongkeji.service.v4.vo;


import com.yichen.auth.jackson.ImgUrl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/11/16 11:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SuperUserVo {
    private String avatar;
    private String nickname;
    private String storeName;
    private String storeAddress;
    @ImgUrl
    private String storeLogo;
    private BigDecimal rebate;
    private BigDecimal userRebate;
    private String accessToken;
    private String refreshToken;
    private long expire;
    private Integer superId;
    private BigDecimal rebateTopLimit;
    private BigDecimal newRebate;
    private BigDecimal oldRebate;
    private Integer type;
}
