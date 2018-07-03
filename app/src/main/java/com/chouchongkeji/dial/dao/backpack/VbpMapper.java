package com.chouchongkeji.dial.dao.backpack;

import com.chouchongkeji.dial.pojo.backpack.Vbp;
import org.apache.ibatis.annotations.Param;

/**
 * @author linqin
 * @date 2018/7/2
 */
public interface VbpMapper {

    Vbp selectByUserIdBpId(@Param("userId") Integer userId,@Param("bpId") Long bpId);

}