package com.chouchongkeji.dial.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.service.backpack.gift.vo.GiftItemVo;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/7/6
 */

public class GiftItemVoTypeHandler extends BaseTypeHandler<List<GiftItemVo>> {


    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<GiftItemVo> giftItemVos, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSON.toJSONString(giftItemVos));
    }

    @Override
    public List<GiftItemVo> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return toList(resultSet.getString(s));
    }

    @Override
    public List<GiftItemVo> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return toList(resultSet.getString(i));
    }

    @Override
    public List<GiftItemVo> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return toList(callableStatement.getString(i));
    }

    private List<GiftItemVo> toList(String string) {

        try {
            return JSON.parseObject(string, new TypeReference<List<GiftItemVo>>() {
            });
        } catch (Throwable e) {

        }
        return null;

    }
}
