package com.chouchongkeji.service.v4.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author Lxh
 * @Date 2021/1/21 21:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReVo {
    private BigDecimal rebateNew;
    private BigDecimal rebateOld;
}
