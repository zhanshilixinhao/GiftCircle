package com.chouchongkeji.dial.dao.gift.virtualItem;

import com.chouchongkeji.dial.pojo.gift.virtualItem.VirItemOrder;
import org.apache.ibatis.annotations.Param;

public interface VirItemOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VirItemOrder record);

    int insertSelective(VirItemOrder record);

    VirItemOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VirItemOrder record);

    int updateByPrimaryKey(VirItemOrder record);

    /**
     * 根据订单号取出订单信息
     *
     * @param: [orderNo]
     * @return: com.chouchongkeji.dial.pojo.gift.virtualItem.VirItemOrder
     * @author: yy
     * @Date: 2018/6/12
     */
    VirItemOrder selectByOrderNo(@Param("orderNo") Long orderNo);
}