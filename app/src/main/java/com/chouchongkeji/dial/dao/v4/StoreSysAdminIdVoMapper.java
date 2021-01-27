package com.chouchongkeji.dial.dao.v4;

import com.chouchongkeji.service.v4.vo.ReVo;
import com.chouchongkeji.service.v4.vo.SysAdminIdVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/10/21 14:08
 */
public interface StoreSysAdminIdVoMapper extends Mapper<SysAdminIdVo>, MySqlMapper<SysAdminIdVo> {



    @Select("SELECT admin_id FROM merchant WHERE id = #{merchantId}")
    Integer findAdminId(@Param("merchantId") Integer merchantId);

    @Select("SELECT rebate_new,rebate_old FROM rebate_coupon_manage WHERE `status`=1 AND admin_id = #{adminId}")
    ReVo findReVo(Integer adminId);
}
