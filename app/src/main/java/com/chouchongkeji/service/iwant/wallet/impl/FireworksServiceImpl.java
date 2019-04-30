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
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.iwant.wallet.FireworksService;
import com.chouchongkeji.service.iwant.wallet.vo.InviteUserVo;
import com.chouchongkeji.service.user.friend.FriendService;
import com.chouchongkeji.util.Constants;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.Request;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
     *
     * @date 2019/4/3
     */
    public Integer addInviteUser(Integer userId, Integer parentUserId) {
        if (userId.equals(parentUserId)){
            return 0;
        }
        InviteUser inviteUser = new InviteUser();
        inviteUser.setUserId(userId);
        inviteUser.setParentUserId(parentUserId);
        int insert = inviteUserMapper.insert(inviteUser);
        if (insert < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "添加邀请好友记录失败");
        }
        // 添加为好友
//        friendService.addWXFriend(userId, parentUserId);
        return inviteUser.getId();//0错误，1正确
    }


    /**
     * 添加礼花/添加记录
     * @param userId
     * @param count
     * @param type
     * @param des
     * @param targetId
     * @return
     */
    public int updateFireworks(Integer userId, Integer count, Constants.FIREWORKS_RECORD type,String des, Integer targetId) {
        //根据用户id取出礼花信息
        Fireworks detail = getFireworks(userId);
        //礼花余额
        Integer fir = detail.getCount();
        //根据记录类型增加/减少余额
        switch (type.type) {
            case 1:
            case 2:
                break;
            case 3:
                count = -count;
                break;
        }

        fir = fir+count;
        if (fir < 0) {
            throw new ServiceException(ErrorCode.YUE_NOT_EN);
        }
        //更新余额
        detail.setCount(fir);
        fireworksMapper.updateByPrimaryKeySelective(detail);
        //插入一条使用记录
        FireworksRecord record = new FireworksRecord();
        record.setUserId(userId);
        record.setDescribe(des);
        record.setCount(count); //绝对值
        record.setTargetId(targetId);
        record.setType((byte) type.type);
        fireworksRecordMapper.insert(record);
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
        if (fire != null) {
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
     *
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

    /**
     * 礼花收益记录
     *
     * @param userId
     * @param starting 开始时间
     * @param ending   截至时间
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    @Override
    public Response earnRecordList(Integer userId, PageQuery pageQuery, Long starting, Long ending) {
        //分页
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        // 查询礼花收益记录
        List<FireworksRecord> records = fireworksRecordMapper.selectByUserIdAndTime(userId, starting, ending);
        return ResponseFactory.sucData(records);
    }


    /**
     * 礼花剩余数量
     *
     * @param userId
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    @Override
    public Response numberRemaining(Integer userId) {
        Fireworks fire = getFireworks(userId);
        Map map = new HashMap();
        int count = 0;
        int proportion = 10;
        if (fire != null) {
            count = fire.getCount();
        }
        map.put("userId", userId);
        map.put("count", count);
        map.put("proportion", proportion);
        return ResponseFactory.sucData(map);
    }


}
