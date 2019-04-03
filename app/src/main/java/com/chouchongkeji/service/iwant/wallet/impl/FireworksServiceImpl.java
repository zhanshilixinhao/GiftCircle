package com.chouchongkeji.service.iwant.wallet.impl;

import com.chouchongkeji.dial.dao.iwant.wallet.FireworksMapper;
import com.chouchongkeji.dial.dao.iwant.wallet.FireworksRecordMapper;
import com.chouchongkeji.dial.dao.user.InviteUserMapper;
import com.chouchongkeji.dial.pojo.friend.Friend;
import com.chouchongkeji.dial.pojo.iwant.wallet.Fireworks;
import com.chouchongkeji.dial.pojo.iwant.wallet.FireworksRecord;
import com.chouchongkeji.dial.pojo.user.InviteUser;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.iwant.wallet.FireworksService;
import com.chouchongkeji.service.iwant.wallet.vo.InviteUserVo;
import com.chouchongkeji.service.user.friend.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author linqin
 * @date 2019/4/3
 */

@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class FireworksServiceImpl implements FireworksService {

    @Autowired
    private InviteUserMapper inviteUserMapper;

    @Autowired
    private FireworksMapper fireworksMapper;

    @Autowired
    private FireworksRecordMapper fireworksRecordMapper;

    @Autowired
    private FriendService friendService;


    /**
     * 添加邀请好友记录
     *
     * @param userId       被邀请者id
     * @param parentUserId 邀请者id
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    public Integer addInviteUser(Integer userId, Integer parentUserId) {
        InviteUser inviteUser = new InviteUser();
        inviteUser.setUserId(userId);
        inviteUser.setParentUserId(parentUserId);
        int insert = inviteUserMapper.insert(inviteUser);
        if (insert < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "添加邀请好友记录失败");
        }
        // 添加为好友
        friendService.addWXFriend(userId,parentUserId);
        return 1;//0错误，1正确
    }


    /**
     * 添加用户礼花
     *
     * @param userId
     * @param count
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    public Integer addFirework(Integer userId, Integer count) {
        Fireworks fire = fireworksMapper.selectByUserId(userId);
        if (fire == null) {
            fire = new Fireworks();
            fire.setUserId(userId);
            fire.setCount(count);
            fire.setAllCount(count);
            int insert = fireworksMapper.insert(fire);
            if (insert < 1) {
                return 0; //0错误，1正确
            }
        }
        fire.setCount(fire.getCount() + count);
        fire.setAllCount(fire.getAllCount() + count);
        int update = fireworksMapper.updateByPrimaryKeySelective(fire);
        if (update < 1) {
            return 0; //0错误，1正确
        }
        return 1;
    }


    /**
     * 添加礼花收益/使用记录
     *
     * @param userId   用户id
     * @param count    礼花数
     * @param des      描述
     * @param targetId 目标id
     * @param type     1 邀请好友，2好友购买商品 3购买商品
     * @return
     */
    public Integer addFireworkRecord(Integer userId, Integer count, String des, Integer targetId, Byte type) {
        FireworksRecord record = new FireworksRecord();
        record.setUserId(userId);
        record.setDescribe(des);
        record.setCount(count);
        record.setTargetId(targetId);
        record.setType(type);
        int insert = fireworksRecordMapper.insert(record);
        if (insert < 1) {
            return 0; //0错误，1正确
        }
        return 1;
    }


    /**
     * 用户礼花详情
     *
     * @param userId
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    @Override
    public Response fireworksDetail(Integer userId) {
        Fireworks fire = getFireworks(userId);
        return ResponseFactory.sucData(fire);
    }



    private Fireworks getFireworks(Integer userId) {
        Fireworks fire = fireworksMapper.selectByUserId(userId);
        if (fire != null){
            return fire;
        }
        // 插入一条空数据
        Fireworks works = new Fireworks();
        works.setUserId(userId);
        works.setCount(0);
        works.setAllCount(0);
        fireworksMapper.insert(works);
        return works;
    }


    /**
     * 我的团队
     * @param userId
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    @Override
    public Response getInviteUserList(Integer userId) {
        List<InviteUserVo> vos = inviteUserMapper.selectAllList(userId);
        return ResponseFactory.sucData(vos);
    }



}
