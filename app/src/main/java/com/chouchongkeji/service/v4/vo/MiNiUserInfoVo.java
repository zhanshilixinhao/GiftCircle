package com.chouchongkeji.service.v4.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/9/8 19:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MiNiUserInfoVo {
    private String avatarUrl;
    private String city;
    private String country;
    private Integer gender;
    private String language;
    private String nickname;
    private String province;
    
}
