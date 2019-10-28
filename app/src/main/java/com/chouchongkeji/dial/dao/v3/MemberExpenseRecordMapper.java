package com.chouchongkeji.dial.dao.v3;

import com.chouchongkeji.dial.pojo.v3.MemberExpenseRecord;
import com.chouchongkeji.service.v3.vo.ExpenseDetailVo;
import com.chouchongkeji.service.v3.vo.ExpenseListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberExpenseRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberExpenseRecord record);

    int insertSelective(MemberExpenseRecord record);

    MemberExpenseRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberExpenseRecord record);

    int updateByPrimaryKey(MemberExpenseRecord record);

    /**
     * 消费记录
     * @param userId
     * @param id
     * @return
     */
    List<ExpenseListVo> selectByIdUserId(@Param("userId") Integer userId, @Param("id") Integer id);
    /**
     * 消费记录详情
     * @param userId
     * @param id
     * @return
     */
    ExpenseDetailVo selectByKeyUserId(@Param("userId") Integer userId, @Param("id") Integer id);
}
