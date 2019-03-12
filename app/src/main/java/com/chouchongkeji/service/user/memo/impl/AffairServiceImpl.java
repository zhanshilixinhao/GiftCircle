package com.chouchongkeji.service.user.memo.impl;

import com.chouchongkeji.dial.dao.friend.FriendMapper;
import com.chouchongkeji.dial.dao.user.memo.MemoAffairMapper;
import com.chouchongkeji.dial.dao.user.memo.MemoEventTypeMapper;
import com.chouchongkeji.dial.pojo.friend.Friend;
import com.chouchongkeji.dial.pojo.friend.FriendGroup;
import com.chouchongkeji.dial.pojo.user.memo.MemoAffair;
import com.chouchongkeji.dial.pojo.user.memo.MemoEventType;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.user.friend.vo.FriendVo;
import com.chouchongkeji.service.user.memo.AffairService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

/**
 * @author linqin
 * @date 2019/3/11 16:20
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class AffairServiceImpl implements AffairService {

    @Autowired
    private MemoEventTypeMapper memoEventTypeMapper;

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private MemoAffairMapper memoAffairMapper;

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
     * 备忘录事件类型列表
     *
     * @param userId
     * @author linqin
     * @date 2019/3/11
     */
    @Override
    public Response getEventTypeList(Integer userId) {
        List<MemoEventType> list = memoEventTypeMapper.selectByUserId(userId);
        return ResponseFactory.sucData(list);
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
    public Response addAffair(Integer userId, MemoAffair memoAffair, HashSet<Integer> idSet, Byte isCirculation) {
        if (CollectionUtils.isNotEmpty(idSet)) {
            //判断被邀请的用户是不是好友关系
            for (Integer id : idSet) {
                FriendVo friend = friendMapper.selectByUserIdAndFriendUserId(userId, id);
                if (friend == null) {
                    return ResponseFactory.err(String.format("你和用户%s的用户不是好友关系，不能邀请Ta", id));
                }
            }
            memoAffair.setCount(idSet.size());
        }
        // 事件类型
        Integer eventTypeId = memoAffair.getEventTypeId();
        if (eventTypeId != null){
            List<MemoEventType> list = memoEventTypeMapper.selectByUserId(userId);
            if (!CollectionUtils.isEmpty(list)){
                for (MemoEventType memoEventType : list) {
                    if (!memoEventType.getId().equals(eventTypeId)){
                        return ResponseFactory.err("事件类型id不正确");
                    }
                }
            }
        }
        memoAffair.setUserId(userId);
        memoAffair.setIsCirculation(isCirculation);
        memoAffairMapper.insert(memoAffair);
        return ResponseFactory.sucMsg("添加成功!");
    }


    /**
     * 修改备忘录事件
     *
     * @param userId
     * @param affair
     * @return
     * @author linqin
     * @date 2019/3/12
     */
    @Override
    public Response modifyAffair(Integer userId, MemoAffair affair, HashSet<Integer> idSet) {
        // 判断修改的活动是否存在
        MemoAffair memoAffair = memoAffairMapper.selectByPrimaryKey(affair.getId());
        if (memoAffair == null) {
            return ResponseFactory.err("修改的活动不存在");
        }
        //判断是否有权限
        if (!memoAffair.getUserId().equals(userId)) {
            return ResponseFactory.err("无权修改");
        }
        // 如过修改了被邀请的用户
        if (CollectionUtils.isNotEmpty(idSet)) {
            // 判断被邀请的用户是不是好友关系
            FriendVo friend;
            for (Integer id : idSet) {
                friend = friendMapper.selectByUserIdAndFriendUserId(userId, id);
                if (friend == null) {
                    return ResponseFactory.err(String.format("你和用户id为%s的 用户不是好友关系，不能邀请Ta", id));
                }
            }
            affair.setCount(idSet.size());
        }
        // 循环
        if (affair.getIsCirculation()!= null &&(affair.getIsCirculation()<0 || affair.getIsCirculation()>4)){
            return ResponseFactory.err("循环参数错误");
        }
        // 事件类型
        Integer eventTypeId = affair.getEventTypeId();
        if (eventTypeId != null){
            List<MemoEventType> list = memoEventTypeMapper.selectByUserId(userId);
            if (!CollectionUtils.isEmpty(list)){
                for (MemoEventType memoEventType : list) {
                    if (!memoEventType.getId().equals(eventTypeId)){
                        return ResponseFactory.err("事件类型id不正确");
                    }
                }
            }
        }
        // 修改活动信息
        affair.setUserId(null);
        memoAffairMapper.updateByPrimaryKeySelective(affair);
        return ResponseFactory.sucMsg("修改成功!");
    }

    /**
     * 删除一个备忘录信息
     *
     * @param userId 用户信息
     * @param id          备忘录 id
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    @Override
    public Response delMemo(Integer userId, Integer id) {
        // 查询记录
        MemoAffair memoAffair = memoAffairMapper.selectByPrimaryKey(id);
        if (!memoAffair.getUserId().equals(userId)) {
            return ResponseFactory.err("没有删除权限");
        }
        // 删除活动
        int count = memoAffairMapper.deleteByPrimaryKey(id);
        if (count > 0) {
            return ResponseFactory.sucMsg("删除成功!");
        }
        return ResponseFactory.err("删除失败!");
    }



}
