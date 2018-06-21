package com.chouchongkeji.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.dial.dao.friend.FriendMapper;
import com.chouchongkeji.dial.dao.user.UserPreferenceMapper;
import com.chouchongkeji.dial.dao.user.UserTagDictMapper;
import com.chouchongkeji.dial.pojo.friend.Friend;
import com.chouchongkeji.dial.pojo.user.UserPreference;
import com.chouchongkeji.dial.pojo.user.UserTagDict;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.user.UserPreferenceService;
import com.chouchongkeji.service.user.vo.UserTagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * @author yichenshanren
 * @date 2018/6/21
 */

@Service
public class UserPreferenceServiceImpl implements UserPreferenceService {

    @Autowired
    private UserTagDictMapper userTagDictMapper;

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private UserPreferenceMapper userPreferenceMapper;

    /**
     * 获取标签列表
     *
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response getTags() {
        return ResponseFactory.sucData(userTagDictMapper.selectAll());
    }

    /**
     * 给好友添加一个标签
     *
     * @param userId       用户id
     * @param friendUserId 好友用户id
     * @param tagId        标签id
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response addTag(Integer userId, Integer friendUserId, Integer tagId) {
        // 判断是不是好友关系
        Friend friend = friendMapper.selectByUserIdAndFriendUserId(userId, friendUserId);
        if (friend == null) {
            return ResponseFactory.err("添加好友才能添加好友标签!");
        }
        // 判断标是否存在
        UserTagDict dict = userTagDictMapper.selectByPrimaryKey(tagId);
        if (dict == null) {
            return ResponseFactory.err("添加的标签不存在!");
        }
        // 取出用户的标签列表
        UserPreference preference = userPreferenceMapper.selectByPrimaryKey(friendUserId);
        int udpate = 1;
        if (preference == null) {
            udpate = 0;
            preference = new UserPreference();
        }
        // 取出用户的标签列表
        HashSet<UserTagVo> tagVos = JSON.parseObject(preference.getTags(), new TypeReference<HashSet<UserTagVo>>() {
        });
        // 如果标签列表不存在创建
        if (tagVos == null) {
            tagVos = new HashSet<>();
        }
        // 判断用户是否有这个标签
        UserTagVo vo = null;
        for (UserTagVo tagVo : tagVos) {
            if (tagVo.getTagId().compareTo(tagId) == 0) {
                vo = tagVo;
                break;
            }
        }
        // 如果不存在这个标签，添加
        if (vo == null) {
            vo = new UserTagVo();
            vo.setTagId(tagId);
            vo.setNum(1);
            vo.setTag(dict.getTag());
            tagVos.add(vo);
        } else {
            // 如果存在修改数量
            vo.setNum(vo.getNum() + 1);
        }
        preference.setTags(JSON.toJSONString(tagVos));
        // 更新
        if (udpate == 1) {
            preference.setGiftPreference(null);
            userPreferenceMapper.updateByPrimaryKeySelective(preference);
        } else {
            // 新增数据
            userPreferenceMapper.insert(preference);
        }
        return ResponseFactory.sucMsg("添加成功!");
    }
}
