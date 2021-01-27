package com.chouchongkeji.wxpush.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description: 微信小程序推送实体类
 * @Author Lxh
 * @Date 2020/10/29 10:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TemplateParam {
    private String key;
    private String value;
}
