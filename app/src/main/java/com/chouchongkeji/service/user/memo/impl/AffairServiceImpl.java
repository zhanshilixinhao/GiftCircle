package com.chouchongkeji.service.user.memo.impl;

import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.dial.dao.friend.FriendMapper;
import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.dao.user.memo.MemoAffairMapper;
import com.chouchongkeji.dial.dao.user.memo.MemoEventTypeMapper;
import com.chouchongkeji.dial.dao.user.memo.MemoFestivalItemMapper;
import com.chouchongkeji.dial.dao.user.memo.MemoFestivalMapper;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.dial.pojo.user.memo.MemoAffair;
import com.chouchongkeji.dial.pojo.user.memo.MemoEventType;
import com.chouchongkeji.dial.pojo.user.memo.MemoFestival;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.home.HomeService;
import com.chouchongkeji.service.home.vo.CalendarVo;
import com.chouchongkeji.service.mall.item.vo.ItemListVo;
import com.chouchongkeji.service.user.friend.vo.FriendVo;
import com.chouchongkeji.service.user.memo.AffairService;
import com.chouchongkeji.service.user.memo.vo.*;
import com.yichen.auth.redis.CacheCallback;
import com.yichen.auth.redis.MRedisTemplate;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    private AppUserMapper appUserMapper;

    @Autowired
    private MemoAffairMapper memoAffairMapper;

    @Autowired
    private MemoFestivalMapper memoFestivalMapper;

    @Autowired
    private MemoFestivalItemMapper memoFestivalItemMapper;

    @Autowired
    private MRedisTemplate mRedisTemplate;

    @Autowired
    private HomeService homeService;

    /**
     * 添加备忘录事件类型
     *
     * @param userId
     * @param name   类型名称
     * @return
     * @author linqin
     * @date 2019/3/11
     */
    @Override
    public Response addEventType(Integer userId, String name) {
        //判断事件类型是否相同
        MemoEventType type = memoEventTypeMapper.selectByUserIdAndName(userId, name);
        if (type != null) {
            return ResponseFactory.err("事件类型重复");
        }
        // 添加事件类型
        MemoEventType memoEventType = new MemoEventType();
        memoEventType.setUserId(userId);
        memoEventType.setName(name);
        int insert = memoEventTypeMapper.insert(memoEventType);
        if (insert < 1) {
            return ResponseFactory.err("添加失败");
        }
        return ResponseFactory.sucMsg("添加成功");
    }


    /**
     * 修改备忘录事件类型
     *
     * @param userId
     * @param name    类型名称
     * @param eventId 事件类型id
     * @return
     * @author linqin
     * @date 2019/3/11
     */
    @Override
    public Response updateEventType(Integer userId, String name, Integer eventId) {
        //判断事件类型是否存在
        MemoEventType type = memoEventTypeMapper.selectByPrimaryKey(eventId);
        if (type == null) {
            return ResponseFactory.err("事件类型不存在");
        }
        if (!type.getUserId().equals(userId)) {
            return ResponseFactory.err("无权修改");
        }
        //判断事件类型是否相同
        type = memoEventTypeMapper.selectByUserIdAndName(userId, name);
        if (type != null) {
            return ResponseFactory.err("事件类型重复");
        }
        type = new MemoEventType();
        type.setId(eventId);
        type.setName(name);
        int i = memoEventTypeMapper.updateByPrimaryKeySelective(type);
        if (i < 1) {
            return ResponseFactory.err("修改失败");
        }
        return ResponseFactory.sucMsg("修改成功!");
    }


    /**
     * 删除备忘录事件类型
     *
     * @param userId
     * @param eventId 事件类型id
     * @return
     * @author linqin
     * @date 2019/3/11
     */
    @Override
    public Response deleteEventType(Integer userId, Integer eventId) {
        //判断事件类型是否存在
        MemoEventType type = memoEventTypeMapper.selectByPrimaryKey(eventId);
        if (type == null) {
            return ResponseFactory.err("事件类型不存在");
        }
        if (!type.getUserId().equals(userId)) {
            return ResponseFactory.err("无权删除");
        }
        int i = memoEventTypeMapper.deleteByPrimaryKey(eventId);
        if (i < 1) {
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
        List<MemoEventType> list = memoEventTypeMapper.selectAllByUserId(userId);
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
        if (eventTypeId != null && eventTypeId != 1) {
            MemoEventType list = memoEventTypeMapper.selectByUserId(userId, eventTypeId);
            if (list == null) {
                return ResponseFactory.err("事件类型不存在");
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
        if (StringUtils.isBlank(affair.getUsers())) {
            memoAffairMapper.updateById(affair.getId());
        }
        // 循环
        if (affair.getIsCirculation() != null && (affair.getIsCirculation() < 0 || affair.getIsCirculation() > 3)) {
            return ResponseFactory.err("循环参数错误");
        }
        // 事件类型
        Integer eventTypeId = affair.getEventTypeId();
        if (eventTypeId != null && eventTypeId != 1) {
            MemoEventType list = memoEventTypeMapper.selectByUserId(userId, eventTypeId);
            if (list == null) {
                return ResponseFactory.err("事件类型不存在");
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
     * 获取备忘录列表
     *
     * @param userId
     * @param start
     * @param end
     * @return
     * @author linqin
     * @date 2019/1/7 16:38
     */
    @Override
    public Response getAffairList(Integer userId, Long start, Long end) throws ParseException {
        //不循环
        List<MemoItemVo> list = memoAffairMapper.selectByUserIdAndDate(userId, start, end);
        if (CollectionUtils.isNotEmpty(list)) {
            for (MemoItemVo vo : list) {
                // 被邀请
                if (vo.getType() == 2){
                    FriendVo friend = friendMapper.selectByUserIdAndFriendUserId(userId,vo.getUserId() );
                    if (friend != null && StringUtils.isNotBlank(friend.getRemark())){
                        vo.setNickname(friend.getRemark());
                    }
                }
            }
        }
            // 节日事件
        List<MemoItemVo> memos = memoFestivalMapper.selectAll();
        if (CollectionUtils.isNotEmpty(memos)) {
            list.addAll(memos);
        }
//        // 按周循环（200周）
//        List<MemoItemVo> weeks = memoAffairMapper.selectAllByUserIdWeek(userId);
//        if (CollectionUtils.isNotEmpty(weeks)) {
//            try {
//                for (MemoItemVo week : weeks) {
//                    // 目标日期
//                    Calendar tarcalendar = Calendar.getInstance();
//                    tarcalendar.setTime(week.getTargetTime());
//                    // 目标星期
//                    int i1 = tarcalendar.get(Calendar.DAY_OF_WEEK);
//                    int i2 = tarcalendar.get(Calendar.HOUR_OF_DAY);
//                    int i3 = tarcalendar.get(Calendar.MINUTE);
//                    int i4 = tarcalendar.get(Calendar.SECOND);
//                    //现在时间
//                    tarcalendar.setTime(new Date());
//                    tarcalendar.set(Calendar.DAY_OF_WEEK, i1);
//                    tarcalendar.set(Calendar.HOUR_OF_DAY, i2);
//                    tarcalendar.set(Calendar.MINUTE, i3);
//                    tarcalendar.set(Calendar.SECOND, i4);
//                    Date time = tarcalendar.getTime();
//                    for (int i = -104; i < 104; i++) {
//                        MemoItemVo itemVo = (MemoItemVo) week.clone();
//                        itemVo.setTargetTime(DateUtils.addWeeks(time, i));
//                        list.add(itemVo);
//                    }
//                }
//            } catch (CloneNotSupportedException e) {
//                e.printStackTrace();
//            }
//        }
//        // 按月循环(前后60个月)
//        List<MemoItemVo> months = memoAffairMapper.selectAllByUserIdMonth(userId);
//        if (CollectionUtils.isNotEmpty(months)) {
//            try {
//                for (MemoItemVo item : months) {
//                    // 当前日期
//                    Calendar tarcalendar = Calendar.getInstance();
//                    // 目标日期
//                    tarcalendar.setTime(item.getTargetTime());
//                    int day = tarcalendar.get(Calendar.DAY_OF_MONTH);//天
//                    int month = tarcalendar.get(Calendar.MONTH);//月
//                    int year = tarcalendar.get(Calendar.YEAR);// 年
//                    // 判断是是闰年
//                    if ((year % 4 == 0 && year % 100 != 0) || (year % 100 == 0 && year % 400 == 0)) {
//                        //2月29
//                        if (month == 1 && day == 29) {
//                            tarcalendar.set(Calendar.MONTH, 2);
//                        }
//                    }
//                    // 调整到当前年份
//                    tarcalendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
//                    Date time = tarcalendar.getTime();
//                    for (int i = -60; i < 60; i++) {
//                        MemoItemVo itemVo = (MemoItemVo) item.clone();
//                        //取出加了月份的日期
//                        tarcalendar.setTime(DateUtils.addMonths(time, i));
//                        int day2 = tarcalendar.get(Calendar.DAY_OF_MONTH);
//                        if (day == day2) {
//                            itemVo.setTargetTime(DateUtils.addMonths(time, i));
//                            list.add(itemVo);
//                        }
//                    }
//                }
//            } catch (CloneNotSupportedException e) {
//                e.printStackTrace();
//            }
//        }
        // 按年循环(100年)
        List<MemoItemVo> years = memoAffairMapper.selectAllCByUserId(userId);
        if (CollectionUtils.isNotEmpty(years)) {
            try {
                for (MemoItemVo item : years) {
                    Calendar tarcalendar = Calendar.getInstance();
                    //目标日期
                    tarcalendar.setTime(item.getTargetTime());
                    int day = tarcalendar.get(Calendar.DAY_OF_MONTH);
                    Date time = tarcalendar.getTime();
                    for (int i = -50; i < 50; i++) {
                        MemoItemVo itemVo = (MemoItemVo) item.clone();
                        // 取出调整后日期
                        tarcalendar.setTime(DateUtils.addYears(time, i));
                        int day2 = tarcalendar.get(Calendar.DAY_OF_MONTH);
                        if (day == day2) {
                            itemVo.setTargetTime(DateUtils.addYears(time, i));
                            // 好友备注问题
                            if (item.getType() == 2){
                                FriendVo friend = friendMapper.selectByUserIdAndFriendUserId(userId,item.getUserId() );
                                if (friend != null && StringUtils.isNotBlank(friend.getRemark())){
                                    itemVo.setNickname(friend.getRemark());
                                }
                            }
                            list.add(itemVo);
                        }
                    }
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
//        List<MemoItemVo> list1 = new ArrayList<>();
//        // 当前零点时间戳
//        Long s = TimeUtils.time(System.currentTimeMillis()) * 1000;
//        // 一年后时间
//        Date date = DateUtils.addYears(new Date(), 1);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//可以方便地修改日期格式
//        Long e = dateFormat.parse(dateFormat.format(date)).getTime();
//        // 一个月后
//        Date date1 = DateUtils.addMonths(new Date(), 1);
//        Long y = dateFormat.parse(dateFormat.format(date1)).getTime();
//        if (CollectionUtils.isNotEmpty(list)) {
//            for (MemoItemVo memoItemVo : list) {
//                Long targetTime = dateFormat.parse(dateFormat.format(memoItemVo.getTargetTime())).getTime();
//                if (memoItemVo.getType() == 3) {
//                    if (targetTime < y && targetTime >= s) {
//                        list1.add(memoItemVo);
//                    }
//                } else {
//                    if (targetTime < e && targetTime >= s) {
//                        list1.add(memoItemVo);
//                    }
//                }
//            }
//            //排序
//            list1.sort(new Comparator<MemoItemVo>() {
//                @Override
//                public int compare(MemoItemVo o1, MemoItemVo o2) {
//
//                    return o1.getTargetTime().compareTo(o2.getTargetTime());
//                }
//            });
//        }
        if (CollectionUtils.isNotEmpty(list)){
            //排序
            list.sort(new Comparator<MemoItemVo>() {
                @Override
                public int compare(MemoItemVo o1, MemoItemVo o2) {

                    return o1.getTargetTime().compareTo(o2.getTargetTime());
                }
            });
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
     * 节日事件详情
     *
     * @param id 节日事件id
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    @Override
    public Response memoFestivalDetail(Integer userId, Integer id) {
        MemoFestivalVo vo = new MemoFestivalVo();
        //节日详情
        MemoFestival festival = memoFestivalMapper.selectById(id);
        if (festival == null) {
            return ResponseFactory.err("节日事件不存在");
        }
        // 黄历宜忌
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String time = dateFormat.format(festival.getTargetTime()); //日期
        CalendarVo calendarVo = mRedisTemplate.get(time, 30, TimeUnit.DAYS, new TypeReference<CalendarVo>() {
        }, new CacheCallback<CalendarVo>() {
            @Override
            public CalendarVo load() {
                return homeService.getAlmanacDay(time);
            }
        });
        vo.setId(id);
        vo.setPicture(festival.getPicture());
        vo.setTitle(festival.getTitle());
        vo.setTargetTime(festival.getTargetTime());
        vo.setDetail(festival.getDetail());
        vo.setCreated(festival.getCreated());
        vo.setYi(calendarVo.getSuit());
        vo.setJi(calendarVo.getAvoid());
        return ResponseFactory.sucData(vo);
    }


    /**
     * 节日事件详情页商品列表
     *
     * @param id 节日事件id
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    @Override
    public Response memoFestivalDetailItems(Integer userId, Integer id) {
        List<ItemListVo> list = memoFestivalItemMapper.selectByFestival(id);
        return ResponseFactory.sucData(list);
    }


    /**
     * 节日详情页好友列表
     *
     * @param userId
     * @return
     * @author linqin
     * @date 2018/6/22
     */
    @Override
    public Response getFriendList(Integer userId) {
        List<FriendHumVo> list = friendMapper.selectByUserId(userId);
        if (CollectionUtils.isNotEmpty(list)){
            for (FriendHumVo vo : list) {
                if (StringUtils.isNotBlank(vo.getRemark())){
                    vo.setNickname(vo.getRemark());
                }
            }
        }
        return ResponseFactory.sucData(list);
    }



    /*---------------------------------------------------v2------------------------------------------------------*/

    /**
     * 普通事件详情
     *
     * @param userDetails
     * @param id          事件id
     * @return
     * @author linqin
     * @date 2019/6/21
     */
    @Override
    public Response memoAffairDetail(UserDetails userDetails, Integer id) {
        MemoItemVoV2 v2 = memoAffairMapper.selectByKey(id);
        if (v2 == null) {
            return ResponseFactory.err("详情获取失败");
        }
        List<Users> list = new ArrayList<>();
        // 被邀请的用户
        if (v2.getUsers() != null) {
            String[] split = v2.getUsers().split(",");
            for (String userId : split) {
                Users s = new Users();
                AppUser users = appUserMapper.selectByPrimaryKey(Integer.parseInt(userId));
                if (users != null) {
                    s.setAvatar(users.getAvatar());
                    s.setUserId(users.getId());
                    s.setNickname(users.getNickname());
                    FriendVo friend1 = friendMapper.selectByUserIdAndFriendUserId(userDetails.getUserId(),Integer.parseInt(userId));
                    if (friend1 != null && StringUtils.isNotBlank(friend1.getRemark())){
                        s.setNickname(friend1.getRemark());
                    }
                    list.add(s);
                }
            }

            v2.setUserList(list);
        }
        // 自己创建
        if (v2.getUserId().equals(userDetails.getUserId())) {
            v2.setType(1);
        } else {
            v2.setType(2); //被邀请
            // 互动值
            FriendVo friend = friendMapper.selectByUserIdAndFriendUserId(v2.getUserId(), userDetails.getUserId());
            if (friend != null) {
                v2.setHortNum(friend.getHeartNum());
            } else {
                v2.setHortNum(0f);
            }
            // 备注问题
            FriendVo friend1 = friendMapper.selectByUserIdAndFriendUserId(userDetails.getUserId(),v2.getUserId() );
            if (friend1 != null && StringUtils.isNotBlank(friend1.getRemark())){
                v2.setNickname(friend1.getRemark());
            }
        }
        return ResponseFactory.sucData(v2);
    }


}
