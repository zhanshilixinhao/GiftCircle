package com.chouchongkeji.service.v4.vo;

import com.yichen.auth.jackson.ImgUrl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/10/22 15:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreVoV4 {
    private Integer id;
    private String name;
    private String address;
    private String area;
    @ImgUrl
    private String avatar;
    private Integer adminId;
}
