package com.chouchongkeji.service.backpack.gift.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.dial.dao.backpack.VbpMapper;
import com.chouchongkeji.dial.dao.backpack.gift.GiftRecordDetailMapper;
import com.chouchongkeji.dial.dao.backpack.gift.GiftRecordMapper;
import com.chouchongkeji.dial.dao.friend.FriendMapper;
import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.pojo.backpack.Vbp;
import com.chouchongkeji.dial.pojo.backpack.gift.GiftRecordDetail;
import com.chouchongkeji.dial.pojo.gift.virtualItem.AppMessage;
import com.chouchongkeji.dial.pojo.gift.virtualItem.GiftRecord;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.yichen.auth.redis.MRedisTemplate;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.utils.DateUtil;
import com.chouchongkeji.goexplore.utils.K;
import com.chouchongkeji.service.backpack.base.BpService;
import com.chouchongkeji.service.backpack.gift.GiftService;
import com.chouchongkeji.service.backpack.gift.vo.*;
import com.chouchongkeji.service.backpack.gift.vo2.GiftSendVo2;
import com.chouchongkeji.service.message.MessageService;
import com.chouchongkeji.service.message.vo.GiftMessageVo;
import com.chouchongkeji.service.user.friend.FriendService;
import com.chouchongkeji.service.user.friend.vo.FriendVo;
import com.chouchongkeji.service.user.info.UserService;
import com.chouchongkeji.util.Constants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author yichenshanren
 * @date 2018/7/2
 */

@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class GiftServiceImpl implements GiftService {

    private static Logger log = LoggerFactory.getLogger(GiftServiceImpl.class);

    @Autowired
    private GiftRecordMapper giftRecordMapper;

    @Autowired
    private GiftRecordDetailMapper giftRecordDetailMapper;

    @Autowired
    private FriendService friendService;

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private VbpMapper vbpMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private BpService bpService;

    @Autowired
    private UserService userService;

    @Autowired
    private MRedisTemplate mRedisTemplate;

    @Autowired
    private AppUserMapper appUserMapper;

    /*----------------------------------------自动减少互动值------------------------------------------------------*/
    @Scheduled(fixedRate = 86400000)
    public void timeHeartNum() {
        // 查询用户最新赠送记录
        List<RecordDetailVo> giftRecordDetail = giftRecordDetailMapper.selectOne();
        if (!CollectionUtils.isEmpty(giftRecordDetail)) {
            for (RecordDetailVo recordDetailVo : giftRecordDetail) {
                Date date = new Date();
                // 赠送时间大于30天自动减少互动值
                if (date.getTime() - recordDetailVo.getMaxUpdated().getTime() > 2592000000L) {
                    // 自动减少互动值
                    friendMapper.updateHeartNumByUserId(recordDetailVo.getUserId(), recordDetailVo.getFriendUserId());
                }
            }
        }
    }


    /*----------------------------------------礼物赠送------------------------------------------------------*/

    /**
     * 获取started之后收到的礼物记录
     *
     * @param userId  用户id
     * @param started 开始日期
     * @author yichenshanren
     * @date 2018/7/2
     */
    public List<GiftBaseVo> getWithDays(Integer userId, Date started) {
        return mRedisTemplate.get(K.genKey(K.RECENT_GIFT, userId), (int) (DateUtil.tomZeroDiff() / 1000),
                TimeUnit.MILLISECONDS, new TypeReference<List<GiftBaseVo>>() {
                },
                () -> getGiftRecordWithDays(userId, started));
    }

    /**
     * 从数据库中取出
     *
     * @param userId  用户id
     * @param started 开始查询的时间
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    private List<GiftBaseVo> getGiftRecordWithDays(Integer userId, Date started) {
        List<GiftRecordDetail> details = giftRecordDetailMapper.selectByDate(userId, started);
        if (CollectionUtils.isEmpty(details)) {
            return null;
        }
        List<GiftBaseVo> baseVos = new ArrayList<>();
        for (GiftRecordDetail detail : details) {
            List<GiftItemVo> vos = JSON.parseObject(detail.getContent(), new TypeReference<List<GiftItemVo>>() {
            });
            if (CollectionUtils.isNotEmpty(vos)) {
                for (GiftItemVo vo : vos) {
                    GiftBaseVo baseVo = new GiftBaseVo();
                    baseVo.setCover(vo.getCover());
                    baseVo.setTitle(vo.getTitle());
                    baseVo.setTargetId(vo.getTargetId());
                    baseVo.setCreated(detail.getCreated());
                    baseVo.setTargetType(vo.getTargetType());
                    baseVos.add(baseVo);
                }
            }
        }
        return baseVos;
    }

    /*----------------------------------------礼物赠送------------------------------------------------------*/


    /*----------------------------------------礼物赠送------------------------------------------------------*/

    /**
     * 按时赠送任务
     */
    @Scheduled(fixedRate = 30000)
    public void sendTask() {
        log.info("开始执行礼物赠送");
        // 取出所有需要按时赠送的礼物记录
        List<GiftTaskVo> giftTaskVos = giftRecordMapper.selectAllByTargetTime();
        if (CollectionUtils.isEmpty(giftTaskVos)) {
            log.info("没有需要赠送的礼物");
            return;
        }
        for (GiftTaskVo vo : giftTaskVos) {
            // 添加礼物通知消息
            log.info("添加礼物通知消息");
            // 判断是不是好友关系,如果不是好友关系添加好友关系
            friendService.addWXFriend(vo.getUserId(), vo.getSendUserId());
            addGiftNotifyMessage(vo.getSendUserId(), vo.getUserId(), vo.getRecordDetailId(),
                    vo.getGiftItems());
            // 更新礼物记录状态为已赠送
            giftRecordMapper.updateStatusById(vo.getRecordId(), Constants.GIFT_STATUS.SEND);
            // 更新礼物记录详情记录
            int count = giftRecordDetailMapper.updateStatusById(vo.getRecordDetailId(), Constants.GIFT_STATUS.SEND);
            if (count < 1) {
                log.info("礼物似乎已赠送");
                continue;
            }

            // 添加物品到背包
            log.info("添加物品到背包");
            addItemToBp(vo.getRecordDetailId(), vo.getUserId(), vo.getGiftItems());
            log.info("礼物{}赠送成功", vo.getRecordId());
        }
    }

    /**
     * 小程序领取好友分享的礼物
     *
     * @param userId       赠送者信息
     * @param giftRecordId 礼物赠送记录id
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @Override
    public Response wxGetGift(Integer userId, Integer giftRecordId) {
        // 取出礼物记录
        GiftRecord giftRecord = giftRecordMapper.selectByPrimaryKey(giftRecordId);
        if (giftRecord == null) {
            return ResponseFactory.errMsg(20003, "礼物不存在或已过期!");
        }
        Response response = wxGetGiftStatus(userId, giftRecordId);
        if (((WXGetGiftResVo)response.getData()).getStatus() != 0){
            return response;
        }
        // 取出礼物记录详情
        List<GiftRecordDetail> details = giftRecordDetailMapper.selectByRecordId(giftRecord.getId());
        if (CollectionUtils.isEmpty(details)) {
            return ResponseFactory.errMsg(20005, "礼物不存在!" + giftRecordId);
        }
        // 判断礼物是否还可以领取
        List<GiftItemVo> itemVos = new ArrayList<>();
        if (giftRecord.getStatus() > Constants.GIFT_STATUS.PART_SEND) {
            for (GiftRecordDetail detail : details) {
                itemVos.addAll(JSON.parseArray(detail.getContent(), GiftItemVo.class));
            }
            WXGetGiftResVo<GiftMessageVo> vo = messageGift(userId, giftRecord, 0, itemVos);
            return ResponseFactory.errData(20004, "礼物已被领取或已过期!", vo);
        }

        // 如果开可以领取，
        if (giftRecord.getType() == Constants.GIFT_SEND_TYPE.WX_FRIEND) {
            // 如果是 直接赠送的，直接更新状态为已赠送
            return updateWXSent(userId, giftRecord, details, 0);
        } else {
            return updateWXSentRandom(userId, giftRecord, details);
        }
    }

    @Override
    public Response wxGetGiftStatus(Integer userId, Integer giftRecordId) {
        WXGetGiftResVo<GiftMessageVo> vo = new WXGetGiftResVo<>();
        Integer sta = 0;// 0 可以领取
        GiftRecord giftRecord = giftRecordMapper.selectByPrimaryKey(giftRecordId);
        if (giftRecord.getUserId().equals(userId)) {
            sta = 3; // 3 礼物是我送的 我自己不能领取
        } else if (giftRecord.getStatus() == 3) {
            GiftRecordDetail detail = giftRecordDetailMapper.selectByUserIdAndRecordId(userId, giftRecordId);
            if (detail != null) {
                sta = 1; // 1 我已领取
            } else {
                sta = 2; // 2 我没有领取 但是礼物已经被别人领取了
            }
        }
        // 送礼详情
        // 找出还未被领取的礼物
        List<GiftRecordDetail> list = new ArrayList<>();
        List<GiftItemVo> itemVos = new ArrayList<>();
        List<GiftRecordDetail> details = giftRecordDetailMapper.selectByRecordId(giftRecordId);
        if (CollectionUtils.isNotEmpty(details)) {
            for (GiftRecordDetail detail : details) {
                itemVos.addAll(JSON.parseArray(detail.getContent(), GiftItemVo.class));
                if (detail.getStatus() == Constants.GIFT_STATUS.WAIT) {
                    list.add(detail);
                }
            }
        }
        // 找出还未被领取的礼物
        vo = messageGift(userId, giftRecord, list.size(), itemVos);
        vo.setStatus(sta);
        return ResponseFactory.sucData(vo);
    }

    /**
     * 微信好友领取礼物，随机礼物
     * 根据中奖概率计算该用户能否领取
     * 领取之后
     * * 更新礼物记录状态为已赠送
     * * 把物品放到好友背包
     * * 添加一条消息记录
     *
     * @param userId     领取礼物的用户id
     * @param giftRecord 礼物赠送记录
     * @return
     */
    private Response updateWXSentRandom(Integer userId, GiftRecord giftRecord, List<GiftRecordDetail> details) {
        // 取出概率
        Float p = giftRecord.getP();
        if (p == null) {
            return ResponseFactory.err("中奖概率错误!");
        }
        // 找出还未被领取的礼物
        List<GiftRecordDetail> list = new ArrayList<>();
        List<GiftItemVo> itemVos = new ArrayList<>();
        for (GiftRecordDetail detail : details) {
//            if (detail.getUserId() != null && detail.getUserId().compareTo(userId) == 0) {
//                return ResponseFactory.errMsg(20005, "你已领取过了!");
//            }
            if (detail.getStatus() == Constants.GIFT_STATUS.WAIT) {
                list.add(detail);
                itemVos.addAll(JSON.parseArray(detail.getContent(), GiftItemVo.class));
            }
            if (detail.getUserId() != null && detail.getUserId().compareTo(userId) == 0) {
                WXGetGiftResVo<GiftMessageVo> vo = messageGift(userId, giftRecord, list.size(), itemVos);
                return ResponseFactory.errData(20005, "你已领取过了!", vo);
            }
        }
        if (CollectionUtils.isEmpty(list)) {
            WXGetGiftResVo<GiftMessageVo> vo = messageGift(userId, giftRecord, list.size(), itemVos);
            return ResponseFactory.errData(20002, "手速太慢啦，礼品都没有了!", vo);
        }
        // 计算是否能中奖
        if (!cal(p)) {
            return unluckNoGift(userId, giftRecord, list, itemVos);
        }

        return luckGotGift(userId, giftRecord, list, itemVos);
    }

    /**
     * 运气很好抢到了一个礼物
     *
     * @param userId     用户id
     * @param giftRecord 礼物记录
     * @param list       礼物详情记录
     * @param itemVos    礼物内容列表
     * @return
     */
    private Response luckGotGift(Integer userId, GiftRecord giftRecord, List<GiftRecordDetail> list, List<GiftItemVo> itemVos) {
        // 随机取一个礼物保存到用户背包
        int index = (int) (Math.random() * list.size());
        List<GiftRecordDetail> details = new ArrayList<>();
        details.add(list.get(index));
        return updateWXSent(userId, giftRecord, details, list.size() - 1);
    }


    /**
     * 运气不好，没有抢到礼物，返回剩余的物品
     *
     * @param userId     用户id
     * @param giftRecord 礼物记录
     * @param details    礼物记录详情
     * @param itemVos
     * @return
     */
    private Response unluckNoGift(Integer userId, GiftRecord giftRecord, List<GiftRecordDetail> details, List<GiftItemVo> itemVos) {
//        // 返回未被领取的礼物
//        WXGetGiftResVo<GiftMessageVo> vo = new WXGetGiftResVo<>();
//        vo.setType(giftRecord.getType());
//        // 详细信息
//        GiftMessageVo messageVo = new GiftMessageVo();
//        // 赠送者信息
//        AppUser appUser = appUserMapper.selectByUserId(userId);
//        if (appUser != null) {
//            messageVo.setAvatar(appUser.getAvatar());
//            messageVo.setNickname(appUser.getNickname());
//        }
//        AppUser sendUser = appUserMapper.selectByUserId(giftRecord.getUserId());
//        if (sendUser != null) {
//            messageVo.setSendAvatar(sendUser.getAvatar());
//            messageVo.setSendNickname(sendUser.getNickname());
//        }
//        messageVo.setUserId(userId);
//        messageVo.setGreetting(giftRecord.getGreetting());
//        messageVo.setSendUserId(giftRecord.getUserId());
//        messageVo.setGiftItems(itemVos);
//        messageVo.setReNum(details.size());
//        vo.setGiftInfo(messageVo);
        WXGetGiftResVo<GiftMessageVo> vo = messageGift(userId, giftRecord, details.size(), itemVos);
        return ResponseFactory.errData(20001, "运气太差啦，没有抢到礼品!", vo);
    }


    /**
     * @param userId
     * @param giftRecord
     * @param details
     * @param itemVos
     * @return
     */
    private WXGetGiftResVo<GiftMessageVo> messageGift(Integer userId, GiftRecord giftRecord, Integer size, List<GiftItemVo> itemVos) {
        // 返回未被领取的礼物
        WXGetGiftResVo<GiftMessageVo> vo = new WXGetGiftResVo<>();
        vo.setType(giftRecord.getType());
        // 详细信息
        GiftMessageVo messageVo = new GiftMessageVo();
        // 赠送者信息
        AppUser appUser = appUserMapper.selectByUserId(userId);
        if (appUser != null) {
            messageVo.setAvatar(appUser.getAvatar());
            messageVo.setNickname(appUser.getNickname());
        }
        AppUser sendUser = appUserMapper.selectByUserId(giftRecord.getUserId());
        if (sendUser != null) {
            messageVo.setSendAvatar(sendUser.getAvatar());
            messageVo.setSendNickname(sendUser.getNickname());
        }
        messageVo.setUserId(userId);
        messageVo.setGreetting(giftRecord.getGreetting());
        messageVo.setSendUserId(giftRecord.getUserId());
        messageVo.setGiftItems(itemVos);
        messageVo.setReNum(size);
        vo.setGiftInfo(messageVo);
        return vo;
    }

    /**
     * 根据中奖概率计算能否 获得礼物
     *
     * @param p 中奖概率
     * @return
     */
    public static boolean cal(float p) {
        int num = (int) (Math.random() * 100);
        if (num <= (int) (p * 100)) {
            return true;
        }
        return false;
    }

    /**
     * 微信赠送，好友领取
     * 更新礼物记录状态为已赠送
     * 把物品放到好友背包
     * 添加一条消息记录
     *
     * @param userId     领取用户id
     * @param giftRecord 礼物记录
     * @return
     */
    private Response updateWXSent(Integer userId, GiftRecord giftRecord, List<GiftRecordDetail> details, Integer reNum) {
        // 判断是不是本人领取
        if (userId.equals(giftRecord.getUserId())) {
            return ResponseFactory.err("本人不能领取礼物");
        }
        // 判断是不是好友关系
        friendService.addWXFriend(giftRecord.getUserId(), userId);
        //如果礼物剩余数量为0则更新状态
        if (reNum == 0) {
            giftRecord.setStatus(Constants.GIFT_STATUS.SEND);
            giftRecordMapper.updateByPrimaryKeySelective(giftRecord);
        }
        // 更新记录状态为已赠送
        GiftRecordDetail detail = details.get(0);
        // 更新记录详情状态为已赠送
        detail.setStatus(Constants.GIFT_STATUS.SEND);
        detail.setUserId(userId);
        AppUser user = appUserMapper.selectByPrimaryKey(userId);
        if (user != null) {
            if (user.getIsHide() == null || user.getIsHide() == 1) {
                detail.setIsHide((byte) 1);
            } else {
                detail.setIsHide((byte) 2);
            }
        }
        giftRecordDetailMapper.updateByPrimaryKeySelective(detail);

        // 添加礼物通知消息
        List<GiftItemVo> list = JSON.parseArray(detail.getContent(), GiftItemVo.class);
        addGiftNotifyMessage(giftRecord.getUserId(), userId, detail.getId(),
                list);

        // 更新赠送者背包中的物品数量
        for (GiftItemVo itemVo : list) {
            int count = vbpMapper.updateQuantityById(itemVo.getBpId(), 1, itemVo.getGiftType());
            if (count < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "用户背包中没有该物品!");
            }
        }
        // 添加物品到背包
        addItemToBp(detail.getId(), userId, list);
        WXGetGiftResVo<GiftMessageVo> vo = new WXGetGiftResVo<>();
        vo.setType(giftRecord.getType());
        // 返回礼物赠送信息
        GiftMessageVo messageVo = new GiftMessageVo();
        messageVo.setRecordDetailId(detail.getId());
        // 赠送者信息
        AppUser appUser = appUserMapper.selectByUserId(userId);
        messageVo.setUserId(userId);
        if (appUser != null) {
            messageVo.setAvatar(appUser.getAvatar());
            messageVo.setNickname(appUser.getNickname());
        }
        AppUser sendUser = appUserMapper.selectByUserId(giftRecord.getUserId());
        if (sendUser != null) {
            messageVo.setSendAvatar(sendUser.getAvatar());
            messageVo.setSendNickname(sendUser.getNickname());
        }
        messageVo.setGreetting(giftRecord.getGreetting());
        messageVo.setGiftItems(list);
        messageVo.setSendUserId(giftRecord.getUserId());
        messageVo.setIsReply((byte) 2);
        messageVo.setReNum(reNum);
        vo.setGiftInfo(messageVo);

        return ResponseFactory.sucData(vo);
    }

    /*----------------------------------------礼物赠送v2开始---------------------------------------------------*/


    /**
     * app赠送礼物实现V2
     *
     * @param userId 赠送者信息
     * @param sendVo 赠送礼物信息 type 1 未领取 2 已领取部分 3 已领取全部 4 超时领取失败
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @Override
    public Response sendForAppV2(Integer userId, GiftSendVo2 sendVo, Integer client) {
        // 判断赠送的物品是否在背包里面
        // 保存礼物信息
        Vbp vbp = vbpMapper.selectByUserIdBpId(userId, sendVo.getBpId());
        if (vbp == null || vbp.getQuantity() < sendVo.getFriendUserIds().size()) {
            return ResponseFactory.err("赠送的礼物不存在背包中或赠送的数量大于背包中的数量!");
        }
        Date buyTime = vbp.getBuyTime();
        if (vbp.getType() != null && vbp.getType() == 1) {
            if (buyTime == null || DateUtils.addDays(buyTime, Constants.BP_EXPIRE_TIME).getTime() - System.currentTimeMillis() <= 0) {
                return ResponseFactory.err("超过赠送时限!");
            }
        }
        // 判断和被赠送的用户是不是好友关系
        for (Integer friendUserId : sendVo.getFriendUserIds()) {
            FriendVo friend = friendService.isFriend(userId, friendUserId);
            if (friend == null) {
                return ResponseFactory.err("添加好友才能赠送!");
            }
        }
        // 增加送礼记录
        GiftRecord record = new GiftRecord();
        record.setUserId(userId);
        record.setGreetting(sendVo.getGreeting());
        record.setEvent(sendVo.getEvent());
        record.setType(sendVo.getType());

        byte status;
        // 如果需要按时间赠送
        if (sendVo.getType() == Constants.GIFT_SEND_TYPE.TARGET_TIME) {
            record.setTargetTime(sendVo.getTargetTime());
            status = Constants.GIFT_STATUS.WAIT;
        } else { // 如果是立即赠送
            record.setTargetTime(new Date());
            status = Constants.GIFT_STATUS.SEND;
        }
        record.setStatus(status);
        // 查看用户是否设置隐藏
        AppUser appUser = appUserMapper.selectByPrimaryKey(userId);
        if (appUser != null) {
            if (appUser.getIsHide() == null || appUser.getIsHide() == 1) {
                record.setIsHide((byte) 1);
            } else {
                record.setIsHide((byte) 2);
            }
        }
        // 保存礼物记录
        int count = giftRecordMapper.insert(record);
        if (count == 0) {
            return ResponseFactory.err("赠送失败!");
        }
        for (Integer friendUserId1 : sendVo.getFriendUserIds()) {
            // 先保存详情
            List<GiftItemVo> list = new ArrayList<>();
            list.add(assembleDetail(sendVo.getBpId(), vbp));
            // 更新背包物品的数量
            count = vbpMapper.updateQuantityById(vbp.getId(), 1, vbp.getType());
            if (count < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新数量失败!");
            }
            // 保存礼物详情记录
            GiftRecordDetail detail = new GiftRecordDetail();
            detail.setUserId(friendUserId1);
            detail.setGiftRecordId(record.getId());
            detail.setStatus(status);
            detail.setContent(JSON.toJSONString(list));
            AppUser user = appUserMapper.selectByPrimaryKey(friendUserId1);
            if (user != null) {
                if (user.getIsHide() == null || user.getIsHide() == 1) {
                    detail.setIsHide((byte) 1);
                } else {
                    detail.setIsHide((byte) 2);
                }
            }
            count = giftRecordDetailMapper.insert(detail);
            if (count == 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "赠送失败!");
            }
            // 如果是立即赠送添加礼物赠送消息
            if (sendVo.getType() == Constants.GIFT_SEND_TYPE.NOW) {
                addGiftNotifyMessage(userId, friendUserId1, detail.getId(), list);
                addItemToBp(detail.getId(), detail.getUserId(), list);
            }
        }
        if (sendVo.getRecordDetailId() != null) {
            updateRecordDetail(sendVo);
        }
        return ResponseFactory.sucMsg("赠送成功");
    }

    /**
     * 更新回礼状态
     *
     * @param sendVo
     */
    private void updateRecordDetail(GiftSendVo2 sendVo) {
        GiftRecordDetail giftRecordDetail = giftRecordDetailMapper.selectByPrimaryKey(sendVo.getRecordDetailId());
        giftRecordDetail.setIsReply((byte) (giftRecordDetail.getIsReply() | 4));
        giftRecordDetailMapper.updateByPrimaryKeySelective(giftRecordDetail);
    }



    /*----------------------------------------礼物赠送v2结束---------------------------------------------------*/
    /*----------------------------------------礼物赠送原来开始---------------------------------------------------*/


    /**
     * app赠送礼物实现
     *
     * @param userId 赠送者信息
     * @param sendVo 赠送礼物信息
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @Override
    public Response sendForApp(Integer userId, GiftSendVo sendVo, String de, String s2, Integer client) {
        // 校验支付密码
//        Response response = userService.changePwdVerify(userId, de, s2, client);
//        if (!response.isSuccessful()) {
//            return response;
//        }
        // 判断和被赠送的用户是不是好友关系
        FriendVo friend = friendService.isFriend(userId, sendVo.getFriendUserId());
        if (friend == null) {
            return ResponseFactory.err("添加好友才能赠送!");
        }
        // 判断赠送的物品是否在背包里面
        HashMap<Long, Integer> map = new HashMap<>();
        // 保存礼物信息
        List<Vbp> vbps = new ArrayList<>();
        Vbp vbp = vbpMapper.selectByUserIdBpId(userId, sendVo.getBpId());
        if (vbp == null || vbp.getQuantity() < 1) {
            return ResponseFactory.err("赠送的礼物不存在背包中或赠送的数量大于背包中的数量!");
        }
        vbps.add(vbp);
        map.put(sendVo.getBpId(), 1);
        // 判断附属物品是否存在
        Integer quantity;
        if (CollectionUtils.isNotEmpty(sendVo.getSubBpIds())) {
            for (Long id : sendVo.getSubBpIds()) {
                // 取出
                vbp = vbpMapper.selectByUserIdBpId(userId, id);
                quantity = map.get(id);
                quantity = quantity == null ? 1 : quantity + 1;
                if (vbp == null || vbp.getQuantity() < quantity) {
                    return ResponseFactory.err("赠送的礼物不存在背包中或赠送的数量大于背包中的数量!");
                }
                if (vbp.getBuyTime() == null || DateUtils.addDays(vbp.getBuyTime(), Constants.BP_EXPIRE_TIME).getTime() < System.currentTimeMillis()) {
                    return ResponseFactory.err("赠送的礼物超过赠送有效期!");
                }
                vbps.add(vbp);
            }
        }
        // 增加送礼记录
        GiftRecord record = new GiftRecord();
        record.setUserId(userId);
        record.setGreetting(sendVo.getGreeting());
        record.setEvent(sendVo.getEvent());
        record.setType(sendVo.getType());

        byte status;
        // 如果需要按时间赠送
        if (sendVo.getType() == Constants.GIFT_SEND_TYPE.TARGET_TIME) {
            record.setTargetTime(sendVo.getTargetTime());
            status = Constants.GIFT_STATUS.WAIT;
        } else { // 如果是立即赠送
            record.setTargetTime(new Date());
            status = Constants.GIFT_STATUS.SEND;
        }
        record.setStatus(status);
        // 保存礼物记录
        int count = giftRecordMapper.insert(record);
        if (count == 0) {
            return ResponseFactory.err("赠送失败!");
        }
        // 先保存详情
        List<GiftItemVo> list = new ArrayList<>();
        for (Vbp item : vbps) {
            list.add(assembleDetail(sendVo.getBpId(), item));
            // 更新背包物品的数量
            count = vbpMapper.updateQuantityById(item.getId(), 1, item.getType());
            if (count < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新数量失败!");
            }
        }
        // 保存礼物详情记录
        GiftRecordDetail detail = new GiftRecordDetail();
        detail.setUserId(sendVo.getFriendUserId());
        detail.setGiftRecordId(record.getId());
        detail.setStatus(status);
        detail.setContent(JSON.toJSONString(list));
        count = giftRecordDetailMapper.insert(detail);
        if (count == 0) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "赠送失败!");
        }
        // 如果是立即赠送添加礼物赠送消息
        if (sendVo.getType() == Constants.GIFT_SEND_TYPE.NOW) {
            addGiftNotifyMessage(userId, sendVo.getFriendUserId(), detail.getId(), list);
            addItemToBp(detail.getId(), detail.getUserId(), list);
            return ResponseFactory.sucMsg("赠送成功");
        } else {
            return ResponseFactory.sucMsg("礼物将在预定时间内送达!");
        }
    }

    /*----------------------------------------礼物赠送原来结束---------------------------------------------------*/
    /*----------------------------------------微信礼物赠送---------------------------------------------------*/

    /**
     * 微信赠送礼物实现小程序
     *
     * @param userId 赠送者信息
     * @param sendVo 赠送礼物信息 type 1 未领取 2 已领取部分 3 已领取全部 4 超时领取失败
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @Override
    public Response sendForWx(Integer userId, GiftSendVo sendVo, Integer client, HashSet<SendWXVo> sendWXVos) {
        if (StringUtils.isBlank(sendVo.getEvent())) {
            sendVo.setEvent("小程序赠送");
        }
        List<Vbp> vbps = new ArrayList<>();
        Long bpId = null;
        Integer quantity = null;
        for (SendWXVo sendWXVo : sendWXVos) {
            bpId = sendWXVo.getBpId();
            quantity = sendWXVo.getQuantity();
            // 判断赠送的物品是否在背包里面
            // 保存礼物信息
            Vbp vbp = vbpMapper.selectByUserIdBpId(userId, bpId);
            if (vbp == null || vbp.getQuantity() < quantity) {
                return ResponseFactory.err("赠送的礼物不存在背包中或赠送的数量大于背包中的数量!");
            }
            vbp.setQuantity(quantity);
            vbps.add(vbp);
            Date buyTime = vbp.getBuyTime();
            if (vbp.getType() != null && vbp.getType() == 1) {
                if (buyTime == null || DateUtils.addDays(buyTime, Constants.BP_EXPIRE_TIME).getTime() - System.currentTimeMillis() <= 0) {
                    return ResponseFactory.err("超过赠送时限!");
                }
            }
        }
        // 增加送礼记录
        GiftRecord record = new GiftRecord();
        record.setUserId(userId);
        record.setGreetting(sendVo.getGreeting());
        record.setEvent(sendVo.getEvent());
        record.setType(sendVo.getType());
        record.setP(sendVo.getP());
        // 小程序的状态都是待领取
        byte status = Constants.GIFT_STATUS.WAIT;
        record.setStatus(status);
        // 查看用户是否设置隐藏
        AppUser appUser = appUserMapper.selectByPrimaryKey(userId);
        if (appUser != null) {
            if (appUser.getIsHide() == null || appUser.getIsHide() == 1) {
                record.setIsHide((byte) 1);
            } else {
                record.setIsHide((byte) 2);
            }
        }
        // 保存礼物记录
        int count = giftRecordMapper.insert(record);
        if (count == 0) {
            return ResponseFactory.err("赠送失败!");
        }
        // 先保存详情
        List<GiftItemVo> list = new ArrayList<>();
        for (Vbp item : vbps) {
//            // 更新背包物品的数量 移到领取礼物的时候
//            count = vbpMapper.updateQuantityById(item.getId(), 1, item.getType());
//            if (count < 1) {
//                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新数量失败!");
//            }
            // 如果是随机赠送，需要分别添加每个物品到礼物详情记录
            if (sendVo.getType() == Constants.GIFT_SEND_TYPE.WX_FRIEND_RANDOM) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    list = new ArrayList<>();
                    list.add(assembleDetail(bpId, item));
                    saveGiftSendDetail(sendVo, record, list, status);
                }

            } else {
                for (int i = 0; i < item.getQuantity(); i++) {
                    list.add(assembleDetail(bpId, item));
                }

            }
        }
        // 如果是直接赠送，礼物信息一起保存，忘记为什么要这么做了
        if (sendVo.getType() == Constants.GIFT_SEND_TYPE.WX_FRIEND) {
            saveGiftSendDetail(sendVo, record, list, status);
        }

        // 返回礼物赠送记录id，用于分享给微信好友
        Map<String, Object> result = new HashMap<>();
        result.put("giftRecordId", record.getId());
        return ResponseFactory.sucData(result);
    }

    /**
     * 微信赠送礼物实现App
     *
     * @param userId 赠送者信息
     * @param sendVo 赠送礼物信息 type 1 未领取 2 已领取部分 3 已领取全部 4 超时领取失败
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @Override
    public Response sendForWxApp(Integer userId, GiftSendVo sendVo, Integer client) {
        if (StringUtils.isBlank(sendVo.getEvent())) {
            sendVo.setEvent("小程序赠送");
        }

        // 判断赠送的物品是否在背包里面
        HashMap<Long, Integer> map = new HashMap<>();
        // 保存礼物信息
        List<Vbp> vbps = new ArrayList<>();
        Vbp vbp = vbpMapper.selectByUserIdBpId(userId, sendVo.getBpId());
        if (vbp == null || vbp.getQuantity() < 1) {
            return ResponseFactory.err("赠送的礼物不存在背包中或赠送的数量大于背包中的数量!");
        }
        vbps.add(vbp);
        map.put(sendVo.getBpId(), 1);
        // 判断附属物品是否存在
        Integer quantity;
        if (CollectionUtils.isNotEmpty(sendVo.getSubBpIds())) {
            for (Long id : sendVo.getSubBpIds()) {
                // 取出
                vbp = vbpMapper.selectByUserIdBpId(userId, id);
                quantity = map.get(id);
                quantity = quantity == null ? 1 : quantity + 1;
                if (vbp == null || vbp.getQuantity() < quantity) {
                    return ResponseFactory.err("赠送的礼物不存在背包中或赠送的数量大于背包中的数量!");
                }
                if (vbp.getBuyTime() == null || DateUtils.addDays(vbp.getBuyTime(), Constants.BP_EXPIRE_TIME).getTime() < System.currentTimeMillis()) {
                    return ResponseFactory.err("赠送的礼物超过赠送有效期!");
                }
                vbps.add(vbp);
            }
        }
        // 增加送礼记录
        GiftRecord record = new GiftRecord();
        record.setUserId(userId);
        record.setGreetting(sendVo.getGreeting());
        record.setEvent(sendVo.getEvent());
        record.setType(sendVo.getType());
        record.setP(sendVo.getP());
        // 小程序的状态都是待领取
        byte status = Constants.GIFT_STATUS.WAIT;
        record.setStatus(status);
        // 查看用户是否设置隐藏
        AppUser appUser = appUserMapper.selectByPrimaryKey(userId);
        if (appUser != null) {
            if (appUser.getIsHide() == null || appUser.getIsHide() == 1) {
                record.setIsHide((byte) 1);
            } else {
                record.setIsHide((byte) 2);
            }
        }
        // 保存礼物记录
        int count = giftRecordMapper.insert(record);
        if (count == 0) {
            return ResponseFactory.err("赠送失败!");
        }
        // 先保存详情
        List<GiftItemVo> list = new ArrayList<>();
        for (Vbp item : vbps) {
//            // 更新背包物品的数量 移到领取礼物的时候
//            count = vbpMapper.updateQuantityById(item.getId(), 1, item.getType());
//            if (count < 1) {
//                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新数量失败!");
//            }
            // 如果是随机赠送，需要分别添加每个物品到礼物详情记录
            if (sendVo.getType() == Constants.GIFT_SEND_TYPE.WX_FRIEND_RANDOM) {
                list = new ArrayList<>();
                list.add(assembleDetail(sendVo.getBpId(), item));
                saveGiftSendDetail(sendVo, record, list, status);
            } else {
                list.add(assembleDetail(sendVo.getBpId(), item));
            }
        }

        // 如果是直接赠送，礼物信息一起保存，忘记为什么要这么做了
        if (sendVo.getType() == Constants.GIFT_SEND_TYPE.WX_FRIEND) {
            saveGiftSendDetail(sendVo, record, list, status);
        }

        // 返回礼物赠送记录id，用于分享给微信好友
        Map<String, Object> result = new HashMap<>();
        result.put("giftRecordId", record.getId());
        return ResponseFactory.sucData(result);
    }






    /*----------------------------------------微信礼物赠送结束---------------------------------------------------*/


    /**
     * 保存礼物记录的详细信息
     *
     * @param sendVo 赠送信息
     * @param record 礼物赠送记录信息
     * @param list   礼物赠送列表
     * @param status 状态
     */
    private void saveGiftSendDetail(GiftSendVo sendVo, GiftRecord record, List<GiftItemVo> list, byte status) {
        // 保存礼物详情记录
        GiftRecordDetail detail = new GiftRecordDetail();
        detail.setUserId(sendVo.getFriendUserId());
        detail.setGiftRecordId(record.getId());
        detail.setStatus(status);
        detail.setContent(JSON.toJSONString(list));
        int count = giftRecordDetailMapper.insert(detail);
        if (count == 0) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "赠送失败!");
        }
    }

    /**
     * 将赠送的礼物放入对方的背包*
     *
     * @param recordDetailId 物品信息
     * @param list
     * @author yichenshanren
     * @date 2018/7/2
     */
    private int addItemToBp(Integer recordDetailId, Integer userId, List<GiftItemVo> list) {

        mRedisTemplate.del(K.genKey(K.RECENT_GIFT, userId));
        return bpService.addFromGiftSent(recordDetailId, userId, list);
    }

    /**
     * 赠送成功之后
     * 添加一条消息
     *
     * @param userId       赠送者用户id
     * @param friendUserId 接收者用户id
     * @param recordId     礼物记录详情id
     * @param list         礼物列表
     * @return 1
     * @author yichenshanren
     * @date 2018/7/2
     */
    private int addGiftNotifyMessage(Integer userId, Integer friendUserId, Integer recordId, List<GiftItemVo> list) {
        FriendVo friend = friendService.isFriend(friendUserId, userId);
        if (friend == null) {
            throw new ServiceException(ErrorCode.ERROR, "扎心了，对方和你不是好友关系!");
        }
        // 根据用户id和好友id查询赠送次数
        int count = giftRecordDetailMapper.selectCount(userId, friendUserId);
        // 增加互动值
        if (count >= 1 && count < 3) {
            int num = 1;
            friendMapper.updateHeartNum(userId, friendUserId, num);
        } else if (count >= 3 && count < 7) {
            int num = 2;
            friendMapper.updateHeartNum(userId, friendUserId, num);
        } else if (count >= 7 && count < 15) {
            int num = 3;
            friendMapper.updateHeartNum(userId, friendUserId, num);
        } else if (count >= 15) {
            int num = 4;
            friendMapper.updateHeartNum(userId, friendUserId, num);
        }
        // 添加消息记录
        String summary = String.format("%s送给您的礼物", Constants.genName(friend));
        messageService.addMessage(Constants.APP_MESSAGE_TYPE.GIFT,
                summary, null, recordId, friendUserId);
        return 1;
    }

    /**
     * 组装详情
     *
     * @param sendVo 赠送新词
     * @param vbp    背包物品信息
     * @return
     */
    private GiftItemVo assembleDetail(Long bpId, Vbp vbp) {
        GiftItemVo detail = new GiftItemVo();
        detail.setBpId(vbp.getId());
        detail.setPrice(vbp.getPrice());
        detail.setTitle(vbp.getTitle());
        detail.setCover(vbp.getCover());
        detail.setBuyTime(vbp.getBuyTime());
        detail.setDescription(vbp.getDescription());
        detail.setTargetType(vbp.getType());
        detail.setTargetId(vbp.getTargetId());
        detail.setBrand(vbp.getBrand());
        detail.setGiftType(bpId.equals(vbp.getId()) ? Constants.GIFT_M_TYPE.MAIN : Constants.GIFT_M_TYPE.SUB);
        return detail;
    }

    /*----------------------------------------礼物赠送结束------------------------------------------------------*/

    /*----------------------------------------礼物赠送答谢开始------------------------------------------------------*/

    /**
     * 答谢礼物赠送
     *
     * @param userId         礼物接收者id
     * @param recordDetailId 记录详情id
     * @param reply          回复内容
     * @return
     * @author yichenshanren
     * @date 2018/7/2
     */
    @Override
    public Response acknowledge(Integer userId, Integer recordDetailId, String reply) {
        // 取出
        GiftRecordDetail detail = giftRecordDetailMapper.selectByPrimaryKey(recordDetailId);
        if (detail == null) {
            return ResponseFactory.err("答谢的记录不存在!");
        }
        if (!detail.getUserId().equals(userId)) {
            return ResponseFactory.err("只能答谢自己收到的礼物!");
        }
        GiftRecordDetail newDetail = new GiftRecordDetail();
        newDetail.setId(detail.getId());
        newDetail.setIsReply((byte) (detail.getIsReply() | 2));
        newDetail.setReply(reply);
        giftRecordDetailMapper.updateByPrimaryKeySelective(newDetail);
        // 根据记录详情id查询送礼者id
        Integer sendGiftId = giftRecordDetailMapper.selectByRecordDetailId(recordDetailId);
        if (sendGiftId == null) {
            return ResponseFactory.err("送礼物者用户不存在");
        }
        // 获取用户昵称
        AppUser appUser = appUserMapper.selectByUserId(userId);
        if (appUser == null) {
            return ResponseFactory.err("该用户不存在");
        }
        //添加答谢系统消息
        AppMessage appMessage = new AppMessage();
        appMessage.setTitle("系统通知");
        appMessage.setSummary("答谢通知");
        appMessage.setContent(appUser.getNickname() + " 发来的感谢语:" + reply);
        appMessage.setTargetId(recordDetailId.longValue());
        appMessage.setTargetType((byte) 24);
        appMessage.setMessageType((byte) 2);
        int in = messageService.addMessage(appMessage, new ArrayList<Integer>() {
            {
                add(sendGiftId);
            }
        });
        if (in < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "添加系统消息失败");
        }
        return ResponseFactory.suc();
    }

    /*----------------------------------------礼物赠送答谢结束------------------------------------------------------*/
}
