package com.chouchongkeji.service.v4.vo;

import com.yichen.auth.jackson.ImgUrl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author Lxh
 * @Date 2021/1/20 10:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoVo {
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

    private Date updated;

    private Date created;

    private Integer day;
}
