package com.chouchongkeji.service.v4.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/10/23 10:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonVo {
    private String data;
    private String errCode;
    private String result;
    private Long time;
}
