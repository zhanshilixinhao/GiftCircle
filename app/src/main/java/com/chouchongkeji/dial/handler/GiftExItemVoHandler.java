package com.chouchongkeji.dial.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.service.backpack.gift.vo.GiftExItemVo;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author linqin
 * @date 2018/7/15
 */
public class GiftExItemVoHandler extends BaseTypeHandler<List<GiftExItemVo>> {


    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<GiftExItemVo> giftExItemVos, JdbcType jdbcType)
            throws SQLException {
        preparedStatement.setString(i,JSON.toJSONString(giftExItemVos));
    }

    @Override
    public List<GiftExItemVo> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return toList(resultSet.getString(s));
    }


    @Override
    public List<GiftExItemVo> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return toList(resultSet.getString(i));
    }

    @Override
    public List<GiftExItemVo> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return toList(callableStatement.getString(i));
    }


    private List<GiftExItemVo> toList(String string) {
        try {
            return JSON.parseObject(string,new TypeReference<List<GiftExItemVo>>(){});
        }catch (Throwable e){

        }
            return null;
    }



}
