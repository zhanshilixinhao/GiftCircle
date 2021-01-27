package com.chouchongkeji.service.v4.vo;

import com.chouchongkeji.dial.pojo.v3.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/10/21 14:06
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store")
public class SysAdminIdVo extends Store {
    private Integer adminId;
}
