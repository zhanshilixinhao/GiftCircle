package com.chouchongkeji.service.user.info.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.dial.dao.friend.FriendMapper;
import com.chouchongkeji.dial.dao.user.GiftPreferenceDictMapper;
import com.chouchongkeji.dial.dao.user.UserPreferenceMapper;
import com.chouchongkeji.dial.dao.user.UserTagDictMapper;
import com.chouchongkeji.dial.dao.user.UserTagInfoMapper;
import com.chouchongkeji.dial.pojo.user.GiftPreferenceDict;
import com.chouchongkeji.dial.pojo.user.UserPreference;
import com.chouchongkeji.dial.pojo.user.UserTagDict;
import com.chouchongkeji.dial.pojo.user.UserTagInfo;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.user.friend.vo.FriendVo;
import com.chouchongkeji.service.user.info.UserPreferenceService;
import com.chouchongkeji.service.user.info.vo.TagVo;
import com.chouchongkeji.service.user.info.vo.UserTagVo;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author yichenshanren
 * @date 2018/6/21
 */

@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class UserPreferenceServiceImpl implements UserPreferenceService {

    @Autowired
    private UserTagDictMapper userTagDictMapper;

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private UserPreferenceMapper userPreferenceMapper;

    @Autowired
    private GiftPreferenceDictMapper giftPreferenceDictMapper;

    @Autowired
    private UserTagInfoMapper userTagInfoMapper;

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
     * 用户给好友添加的所有印象标签
     *
     * @param friendUserId
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response userTagList(Integer userId, Integer friendUserId) {
        List<TagVo> tagVos = new ArrayList<>();
        UserTagInfo info = userTagInfoMapper.selectByUserIdFriendUserId(userId, friendUserId);
        if (info != null) {
            HashSet<Integer> ids = JSON.parseObject(info.getTagIds(), new TypeReference<HashSet<Integer>>(){});
            for (Integer id : ids) {
                UserTagDict userTagDict = userTagDictMapper.selectByPrimaryKey(id);
                if (userTagDict != null){
                    TagVo vo = new TagVo();
                    vo.setUserId(userId);
                    vo.setFriendUserId(friendUserId);
                    vo.setId(userTagDict.getId());
                    vo.setTag(userTagDict.getTag());
                    vo.setType(userTagDict.getType());
                    vo.setCreated(userTagDict.getCreated());
                    vo.setUpdated(userTagDict.getUpdated());
                    tagVos.add(vo);
                }
            }
        }
        return ResponseFactory.sucData(tagVos);
    }


    /**
     * 给好友添加一个标签
     *
     * @param userId       用户id
     * @param friendUserId 好友用户id(被添加者)
     * @param tagIds       标签id
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response addTag(Integer userId, Integer friendUserId, HashSet<Integer> tagIds) {
        // 判断是不是好友关系
        FriendVo friend = friendMapper.selectByUserIdAndFriendUserId(userId, friendUserId);
        if (friend == null) {
            return ResponseFactory.err("添加好友才能添加好友标签!");
        }

        HashSet<Integer> add = new HashSet<>(); //新增
        HashSet<Integer> sub = new HashSet<>(); //减少
        UserTagInfo info = userTagInfoMapper.selectByUserIdFriendUserId(userId, friendUserId);
        if (info != null) {
            HashSet<Integer> ids = JSON.parseObject(info.getTagIds(), new TypeReference<HashSet<Integer>>() {
            });
            if (CollectionUtils.isNotEmpty(ids)) {
                for (Integer tagId : tagIds) {
                    if (!ids.contains(tagId)) {
                        add.add(tagId);
                    }
                }
                for (Integer id : ids) {
                    if (!tagIds.contains(id)) {
                        sub.add(id);
                    }
                }
            }
            info.setTagIds(JSON.toJSONString(tagIds));
            userTagInfoMapper.updateByPrimaryKeySelective(info);

        } else {
            add.addAll(tagIds);
            info = new UserTagInfo();
            info.setUserId(userId);
            info.setFriendUserId(friendUserId);
            info.setTagIds(JSON.toJSONString(tagIds));
            userTagInfoMapper.insert(info);
        }

        // 判断标是否存在
        List<UserTagDict> dict = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(add)){
            dict = userTagDictMapper.selectListByPrimaryKeys(add);
            if (dict.size() < add.size()) {
                return ResponseFactory.err("添加的标签不存在!");
            }
        }

        // 取出用户的标签列表
        UserPreference preference = userPreferenceMapper.selectByPrimaryKey(friendUserId);
        int udpate = 1;
        if (preference == null) {
            udpate = 0;
            preference = new UserPreference();
        }
        // 取出用户的标签列表
        HashSet<UserTagVo> tagVos = null;

        if (StringUtils.isNotBlank(preference.getTags())) {
            tagVos = JSON.parseObject(preference.getTags(), new TypeReference<HashSet<UserTagVo>>() {
            });
        }

        // 如果标签列表不存在创建
        if (tagVos == null) {
            tagVos = new HashSet<>();
        }
        // 判断用户是否有这个标签
        UserTagVo vo = null;
        for (UserTagDict tag : dict) {
            boolean has = false;
            for (UserTagVo tagVo : tagVos) {
                // 如果有这个标签
                if (tagVo.getTagId().compareTo(tag.getId()) == 0) {
                    // 数量增加
                    tagVo.setNum(tagVo.getNum() + 1);
                    has = true;
                    break;
                }
            }
            // 如果没有这个标签
            if (!has) {
                // 添加新的标签
                vo = new UserTagVo();
                vo.setTagId(tag.getId());
                vo.setType(tag.getType());
                vo.setNum(1);
                vo.setTag(tag.getTag());
                tagVos.add(vo);
            }
        }

        if (CollectionUtils.isNotEmpty(sub)) {

                // 删除
                Iterator<UserTagVo> iterator = tagVos.iterator();
                // 迭代
                while (iterator.hasNext()) {
                    UserTagVo next = iterator.next();

                    if (sub.contains(next.getTagId())) {
                        next.setNum(next.getNum() - 1);
                    }
                    if (next.getNum() <= 0) {
                        iterator.remove();
                    }

                }

        }

        preference.setTags(JSON.toJSONString(tagVos));
        // 更新
        if (udpate == 1) {
            preference.setGiftPreference(null);
            userPreferenceMapper.updateByPrimaryKeySelective(preference);
        } else {
            // 新增数据
            preference.setUserId(friendUserId);
            userPreferenceMapper.insert(preference);
        }
        return ResponseFactory.sucMsg("添加成功!");
    }


//  原来的
//    public Response addTag(Integer userId, Integer friendUserId, HashSet<Integer> tagIds) {
//        // 判断是不是好友关系
//        FriendVo friend = friendMapper.selectByUserIdAndFriendUserId(userId, friendUserId);
//        if (friend == null) {
//            return ResponseFactory.err("添加好友才能添加好友标签!");
//        }
//
//        // 判断标是否存在
//        List<UserTagDict> dict = userTagDictMapper.selectListByPrimaryKeys(tagIds);
//        if (dict.size() < tagIds.size()) {
//            return ResponseFactory.err("添加的标签不存在!");
//        }
//
//        // 取出用户的标签列表
//        UserPreference preference = userPreferenceMapper.selectByPrimaryKey(friendUserId);
//        int udpate = 1;
//        if (preference == null) {
//            udpate = 0;
//            preference = new UserPreference();
//        }
//        // 取出用户的标签列表
//        HashSet<UserTagVo> tagVos = null;
//
//        if (StringUtils.isNotBlank(preference.getTags())) {
//            tagVos = JSON.parseObject(preference.getTags(), new TypeReference<HashSet<UserTagVo>>() {
//            });
//        }
//
//        // 如果标签列表不存在创建
//        if (tagVos == null) {
//            tagVos = new HashSet<>();
//        }
//        // 判断用户是否有这个标签
//        UserTagVo vo = null;
//        for (UserTagDict tag : dict) {
//            boolean has = false;
//            for (UserTagVo tagVo : tagVos) {
//                // 如果有这个标签
//                if (tagVo.getTagId().compareTo(tag.getId()) == 0) {
//                    // 数量增加
//                    tagVo.setNum(tagVo.getNum() + 1);
//                    has = true;
//                    break;
//                }
//            }
//            // 如果没有这个标签
//            if (!has) {
//                // 添加新的标签
//                vo = new UserTagVo();
//                vo.setTagId(tag.getId());
//                vo.setType(tag.getType());
//                vo.setNum(1);
//                vo.setTag(tag.getTag());
//                tagVos.add(vo);
//            }
//        }
//        preference.setTags(JSON.toJSONString(tagVos));
//        // 更新
//        if (udpate == 1) {
//            preference.setGiftPreference(null);
//            userPreferenceMapper.updateByPrimaryKeySelective(preference);
//        } else {
//            // 新增数据
//            preference.setUserId(friendUserId);
//            userPreferenceMapper.insert(preference);
//        }
//        return ResponseFactory.sucMsg("添加成功!");
//    }


    /**
     * 获取礼物偏好列表
     *
     * @param userId       用户id
     * @param targetUserId
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response getGiftPreference(Integer userId, Integer targetUserId) {
        boolean isSelf = true;
        if (userId.compareTo(targetUserId) != 0) {
            isSelf = false;
            FriendVo friend = friendMapper.selectByUserIdAndFriendUserId(userId, targetUserId);
            if (friend == null) {
                return ResponseFactory.err("添加好友才能查看哦!");
            }
        }
        List<GiftPreferenceDict> dicts = giftPreferenceDictMapper.selectAll();
        if (CollectionUtils.isEmpty(dicts)) {
            return ResponseFactory.suc();
        }
        // 取出用户的偏好设置
        UserPreference preference = userPreferenceMapper.selectByPrimaryKey(targetUserId);
        if (preference == null) {
            preference = new UserPreference();
        }
        // 取出礼物偏好设置
        HashSet<Integer> ids = null;
        if (StringUtils.isNotBlank(preference.getGiftPreference())) {
            ids = JSON.parseObject(preference.getGiftPreference(), new TypeReference<HashSet<Integer>>() {
            });
        }
        // 如果是查看自己的
        List<GiftPreferenceDict> list = null;
        for (GiftPreferenceDict dict : dicts) {
            byte type = 2;
            if (CollectionUtils.isNotEmpty(ids) && ids.contains(dict.getId())) {
                type = 1;
                if (!isSelf) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(dict);
                }
            }
            dict.setType(type);
        }

        return ResponseFactory.sucData(isSelf ? dicts : list);
    }

    /**
     * 添加礼物偏好
     *
     * @return
     * @author yichenshanren
     * @date 2018/6/21
     */
    @Override
    public Response addGiftPreference(Integer userId, HashSet<Integer> idSet) {
        // 取出用户偏好设置
        UserPreference preference = userPreferenceMapper.selectByPrimaryKey(userId);
        int update = 1;
        if (preference == null) {
            update = 0;
            preference = new UserPreference();
        }
        if (CollectionUtils.isEmpty(idSet)) {
            preference.setGiftPreference("[]");
        } else {
            preference.setGiftPreference(JSON.toJSONString(idSet));
        }
        if (update == 0) {
            preference.setUserId(userId);
            userPreferenceMapper.insert(preference);
        } else {
            userPreferenceMapper.updateByPrimaryKeySelective(preference);
        }
        return ResponseFactory.sucMsg("修改成功!");
    }
}
