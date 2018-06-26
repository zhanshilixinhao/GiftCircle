package com.chouchongkeji.dial.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.service.user.friend.vo.MediaVo;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/6/26
 */

public class MediaTypeHandler extends BaseTypeHandler<List<MediaVo>> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<MediaVo> mediaVos, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSON.toJSONString(mediaVos));
    }

    @Override
    public List<MediaVo> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return toList(resultSet.getString(s));
    }

    private List<MediaVo> toList(String string) {

        try {
            return JSON.parseObject(string, new TypeReference<List<MediaVo>>() {
            });
        } catch (Throwable e) {

        }
        return null;

    }

    @Override
    public List<MediaVo> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return toList(resultSet.getString(i));
    }

    @Override
    public List<MediaVo> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return toList(callableStatement.getString(i));
    }
}
