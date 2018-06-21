package com.chouchongkeji.dial.dao.gift.item;

import com.chouchongkeji.dial.pojo.gift.item.Theme;
import com.chouchongkeji.service.gift.item.vo.ThemeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThemeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Theme record);

    int insertSelective(Theme record);

    Theme selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Theme record);

    int updateByPrimaryKey(Theme record);

    /**
     * 根据主题状态获得主题列表
     *
     * @param: [status 主题状态]
     * @return: java.util.List<com.chouchongkeji.service.gift.item.vo.ThemeVo>
     * @author: yy
     * @Date: 2018/6/20
     */
    List<ThemeVo> selectByStatus(@Param("status") Integer status);
}