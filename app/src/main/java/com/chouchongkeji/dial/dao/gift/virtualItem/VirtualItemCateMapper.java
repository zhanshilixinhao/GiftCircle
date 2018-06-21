package com.chouchongkeji.dial.dao.gift.virtualItem;

import com.chouchongkeji.dial.pojo.gift.virtualItem.VirtualItemCate;
import com.chouchongkeji.service.gift.virtualItem.vo.VirtualItemCateVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VirtualItemCateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VirtualItemCate record);

    int insertSelective(VirtualItemCate record);

    VirtualItemCate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VirtualItemCate record);

    int updateByPrimaryKey(VirtualItemCate record);

    /**
     * 获得虚拟商品分类列表
     *
     * @param: [status 虚拟商品分类状态]
     * @return: java.util.List<com.chouchongkeji.service.gift.virtualItem.vo.VirtualItemCateVo>
     * @author: yy
     * @Date: 2018/6/11
     */
    List<VirtualItemCateVo> selectByStatus(@Param("status") Integer status);
}