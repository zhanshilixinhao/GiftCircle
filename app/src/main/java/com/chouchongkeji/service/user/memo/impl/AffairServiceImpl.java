package com.chouchongkeji.service.user.memo.impl;

import com.chouchongkeji.dial.dao.user.memo.MemoEventTypeMapper;
import com.chouchongkeji.dial.pojo.friend.FriendGroup;
import com.chouchongkeji.dial.pojo.user.memo.MemoAffair;
import com.chouchongkeji.dial.pojo.user.memo.MemoEventType;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.user.memo.AffairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

/**
 * @author linqin
 * @date 2019/3/11 16:20
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class AffairServiceImpl implements AffairService {

    @Autowired
    private MemoEventTypeMapper memoEventTypeMapper;

    /**
     * 添加备忘录事件类型
     *
     * @param userId
     * @param name        类型名称
     * @return
     * @author linqin
     * @date 2019/3/11
     */
    @Override
    public Response addEventType(Integer userId, String name) {
        //判断事件类型是否相同
        MemoEventType type = memoEventTypeMapper.selectByUserIdAndName(userId,name);
        if (type != null){
            return ResponseFactory.err("事件类型重复");
        }
        // 添加事件类型
        MemoEventType memoEventType = new MemoEventType();
        memoEventType.setUserId(userId);
        memoEventType.setName(name);
        int insert = memoEventTypeMapper.insert(memoEventType);
        if (insert< 1){
            return ResponseFactory.err("添加失败");
        }
        return ResponseFactory.sucMsg("添加成功");
    }


    /**
     * 修改备忘录事件类型
     *
     * @param userId
     * @param name        类型名称
     * @param eventId       事件类型id
     * @return
     * @author linqin
     * @date 2019/3/11
     */
    @Override
    public Response updateEventType(Integer userId, String name, Integer eventId) {
        //判断事件类型是否存在
        MemoEventType type = memoEventTypeMapper.selectByPrimaryKey(eventId);
        if (type == null){
            return ResponseFactory.err("事件类型不存在");
        }
        if (!type.getUserId().equals(userId)){
            return ResponseFactory.err("无权修改");
        }
        //判断事件类型是否相同
        type = memoEventTypeMapper.selectByUserIdAndName(userId,name);
        if (type != null){
            return ResponseFactory.err("事件类型重复");
        }
        type = new MemoEventType();
        type.setId(eventId);
        type.setName(name);
        int i = memoEventTypeMapper.updateByPrimaryKeySelective(type);
        if (i< 1){
            return ResponseFactory.err("修改失败");
        }
        return ResponseFactory.sucMsg("修改成功!");
    }


    /**
     * 删除备忘录事件类型
     *
     * @param userId
     * @param eventId       事件类型id
     * @return
     * @author linqin
     * @date 2019/3/11
     */
    @Override
    public Response deleteEventType(Integer userId, Integer eventId) {
        //判断事件类型是否存在
        MemoEventType type = memoEventTypeMapper.selectByPrimaryKey(eventId);
        if (type == null){
            return ResponseFactory.err("事件类型不存在");
        }
        if (!type.getUserId().equals(userId)){
            return ResponseFactory.err("无权删除");
        }
        int i = memoEventTypeMapper.deleteByPrimaryKey(eventId);
        if (i< 1){
            return ResponseFactory.err("删除失败");
        }
        return ResponseFactory.sucMsg("删除成功!");
    }

    /**
     * 添加事件
     *
     * @param userId
     * @param memoAffair
     * @return
     * @author linqin
     * @date 2019/3/11
     */
    @Override
    public Response addAffair(Integer userId, MemoAffair memoAffair, HashSet<Integer> idSet) {
        return null;
    }


}
