package com.chouchongkeji.dial.dao.user.memo;

import com.chouchongkeji.dial.pojo.user.memo.MemoFestivalItem;
import com.chouchongkeji.service.mall.item.vo.ItemListVo;

import java.util.List;

public interface MemoFestivalItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemoFestivalItem record);

    int insertSelective(MemoFestivalItem record);

    MemoFestivalItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemoFestivalItem record);

    int updateByPrimaryKey(MemoFestivalItem record);

    /**
     * 查询商品
     * @param id
     * @return
     */
    List<ItemListVo> selectByFestival(Integer id);
}
