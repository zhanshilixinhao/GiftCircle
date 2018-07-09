package com.chouchongkeji.dial.dao.gift.virtualItem;

import com.chouchongkeji.dial.pojo.gift.virtualItem.VirtualItem;
import com.chouchongkeji.service.gift.virtualItem.vo.VirtualItemVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VirtualItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VirtualItem record);

    int insertSelective(VirtualItem record);

    VirtualItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VirtualItem record);

    int updateByPrimaryKey(VirtualItem record);

    /**
     * 根据分类获得虚拟商品列表
     *
     * @param: [id 虚拟商品分类id, status 商品状态]
     * @return: java.util.List<com.chouchongkeji.service.mall.virtualItem.vo.VirtualItemVo>
     * @author: yy
     * @Date: 2018/6/11
     */
    List<VirtualItemVo> selectByCateId(@Param("id") Integer id, @Param("status") Integer status);
}