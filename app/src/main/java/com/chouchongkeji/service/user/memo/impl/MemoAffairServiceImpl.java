package com.chouchongkeji.service.user.memo.impl;

import com.chouchongkeji.dial.dao.friend.FriendMapper;
import com.chouchongkeji.dial.dao.user.memo.MemoAffairMapper;
import com.chouchongkeji.dial.pojo.user.memo.MemoAffair;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.user.friend.vo.FriendVo;
import com.chouchongkeji.service.user.memo.MemoAffairService;
import com.chouchongkeji.service.user.memo.vo.HomeMemoItemVo;
import com.chouchongkeji.service.user.memo.vo.MemoItemVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author linqin
 * @date 2019/1/7 20:41
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class MemoAffairServiceImpl implements MemoAffairService {

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private MemoAffairMapper memoAffairMapper;

//    @Autowired
//    private OrderHelper orderHelper;

    /**
     * 添加备忘录事件
     *
     * @param userId
     * @param affair
     * @return
     * @author linqin
     * @date 2019/1/7 16:38
     */
    @Override
    public Response addAffair(Integer userId, MemoAffair affair, HashSet<Integer> idSet, Byte isCirculation) {
        if (CollectionUtils.isNotEmpty(idSet)) {
            //判断被邀请的用户是不是好友关系
            for (Integer id : idSet) {
                FriendVo friend = friendMapper.selectByUserIdAndFriendUserId(userId, id);
                if (friend == null) {
                    return ResponseFactory.err(String.format("你和用户%s的用户不是好友关系，不能邀请Ta", id));
                }
            }
            affair.setCount(idSet.size());
        }
        affair.setUserId(userId);
        affair.setIsCirculation(isCirculation);
//        if (isCirculation == Constants.MEMO.YES){
//            List<Date> list = new ArrayList<>();
//            Date now = new Date();
//            for (int i = 0; i < 30; i++) {
//                list.add(DateUtils.addYears(now, i));
//            }
//            affair.setParentId(String.valueOf(orderHelper.genOrderNo(8,8)));
//            memoAffairMapper.insertBatch(affair, list);
//        } else
        memoAffairMapper.insert(affair);
        return ResponseFactory.sucMsg("添加成功!");
    }


    /**
     * 修改备忘录事件
     *
     * @param userId
     * @param affair
     * @return
     * @author linqin
     * @date 2019/1/7 16:38
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
        }
        // 修改活动信息
        affair.setUserId(null);
        memoAffairMapper.updateByPrimaryKeySelective(affair);
        return ResponseFactory.sucMsg("修改成功!");
    }


    /**
     * 获取活动列表
     *
     * @param userId
     * @param start
     * @param end
     * @return
     * @author linqin
     * @date 2019/1/7 16:38
     */
    @Override
    public Response getAffairList(Integer userId, Long start, Long end) {
        List<MemoItemVo> list = memoAffairMapper.selectByUserIdAndDate(userId, start, end);
        List<MemoItemVo> list1 = memoAffairMapper.selectAllCByUserId(userId);
        if (CollectionUtils.isNotEmpty(list1)) {
            try {
                for (MemoItemVo item : list1) {
                    for (int i = 0; i < 30; i++) {
                        MemoItemVo itemVo = (MemoItemVo) item.clone();
                        itemVo.setTargetTime(DateUtils.addYears(item.getTargetTime(), i));
                        list.add(itemVo);
                    }
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

        }
        return ResponseFactory.sucData(list);
    }

    /**
     * 获得好友的备忘录
     *
     * @param userId 用户信息
     * @param start  开始时间
     * @param end    结束时间
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    @Override
    public Response getListForFriend(Integer userId, Long start, Long end, Integer friendUserId) {
        // 判断是不是好友关系
        FriendVo friend = friendMapper.selectByUserIdAndFriendUserId(userId, friendUserId);
        if (friend == null) {
            return ResponseFactory.err("需要添加好友才能查看!");
        }
        List<MemoItemVo> list = memoAffairMapper
                .selectFriendByUserIdAndDate(friendUserId, start, end);
        return ResponseFactory.sucData(list);
    }


    /**
     * 删除一个备忘录信息
     *
     * @param userId 用户信息
     * @param id     备忘录 id
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

    /**
     * 首页的三个备忘录
     *
     * @param userId 用户信息
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    @Override
    public Response getHomeList(Integer userId) throws ParseException {
        Long start = time(System.currentTimeMillis());
        Long end = timeEnd(System.currentTimeMillis());
        List<HomeMemoItemVo> list = memoAffairMapper.selectLastByUserId(userId, start, end);
//         所有循环事件
        List<MemoItemVo> list1 = memoAffairMapper.selectAllCByUserId(userId);
        if (CollectionUtils.isNotEmpty(list1)) {
            Calendar tarcalendar = Calendar.getInstance();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DateUtils.addDays(new Date(start * 1000), 7));
            for (MemoItemVo itemVo : list1) {
                if (itemVo.getTargetTime().getTime() > calendar.getTimeInMillis()) continue;
                tarcalendar.setTime(itemVo.getTargetTime());
                Date targetDate = DateUtils.addYears(itemVo.getTargetTime(),
                        calendar.get(Calendar.YEAR) - tarcalendar.get(Calendar.YEAR));
                if (targetDate.getTime() > calendar.getTimeInMillis()) {
                    targetDate = DateUtils.addYears(itemVo.getTargetTime(), -1);
                }
                float d = (calendar.getTimeInMillis() - targetDate.getTime()) / 86400000f;
                if (d <= 7) {
                    HomeMemoItemVo homeMemoItemVo = new HomeMemoItemVo();
                    homeMemoItemVo.setId(itemVo.getId());
                    homeMemoItemVo.setAvatar(itemVo.getAvatar());
                    homeMemoItemVo.setNickname(itemVo.getNickname());
                    homeMemoItemVo.setDays(7 - d);
                    homeMemoItemVo.setTargetTime(targetDate);
                    homeMemoItemVo.setCreated(itemVo.getCreated());
                    homeMemoItemVo.setUserId(itemVo.getUserId());
                    homeMemoItemVo.setDetail(itemVo.getDetail());
                    homeMemoItemVo.setIsCirculation(itemVo.getIsCirculation());
                    list.add(homeMemoItemVo);
                }
            }
        }
        return ResponseFactory.sucData(list);
    }


    /**
     * 时间戳(当天0点)
     */
    public Long time(Long day) throws ParseException {
        Date now = new Date(day);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = dateFormat.format(now);//日期
        Date parse = dateFormat.parse(format);  //时间戳
        day = parse.getTime() / 1000;
        return day;
    }

    /**
     * 时间戳（当天12点）
     */
    public Long timeEnd(Long end) throws ParseException {
        Date now = new Date(end);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = dateFormat.format(now);
        Date parse = dateFormat.parse(format);
        end = DateUtils.addDays(parse, 1).getTime() / 1000;
        return end;
    }


}
