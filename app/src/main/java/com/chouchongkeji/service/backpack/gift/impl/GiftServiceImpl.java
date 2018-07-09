package com.chouchongkeji.service.backpack.gift.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.dial.dao.backpack.VbpMapper;
import com.chouchongkeji.dial.dao.backpack.gift.GiftRecordDetailMapper;
import com.chouchongkeji.dial.dao.backpack.gift.GiftRecordMapper;
import com.chouchongkeji.dial.pojo.backpack.Vbp;
import com.chouchongkeji.dial.pojo.backpack.gift.GiftRecordDetail;
import com.chouchongkeji.dial.pojo.gift.virtualItem.GiftRecord;
import com.chouchongkeji.dial.redis.MRedisTemplate;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.utils.DateUtil;
import com.chouchongkeji.goexplore.utils.K;
import com.chouchongkeji.service.backpack.base.BpService;
import com.chouchongkeji.service.backpack.gift.GiftService;
import com.chouchongkeji.service.backpack.gift.vo.GiftBaseVo;
import com.chouchongkeji.service.backpack.gift.vo.GiftItemVo;
import com.chouchongkeji.service.backpack.gift.vo.GiftSendVo;
import com.chouchongkeji.service.backpack.gift.vo.GiftTaskVo;
import com.chouchongkeji.service.message.MessageService;
import com.chouchongkeji.service.user.friend.FriendService;
import com.chouchongkeji.service.user.friend.vo.FriendVo;
import com.chouchongkeji.service.user.info.UserService;
import com.chouchongkeji.util.Constants;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    private VbpMapper vbpMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private BpService bpService;

    @Autowired
    private UserService userService;

    @Autowired
    private MRedisTemplate mRedisTemplate;


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
            // 更新礼物记录状态为已赠送
            giftRecordMapper.updateStatusById(vo.getRecordId(), Constants.GIFT_STATUS.SEND);
            // 更新礼物记录详情记录
            int count = giftRecordDetailMapper.updateStatusById(vo.getRecordDetailId(), Constants.GIFT_STATUS.SEND);
            if (count < 1) {
                log.info("礼物似乎已赠送");
                continue;
            }
            // 添加礼物通知消息
            log.info("添加礼物通知消息");
            addGiftNotifyMessage(vo.getSendUserId(), vo.getUserId(), vo.getRecordId(),
                    vo.getGiftItems());
            // 添加物品到背包
            log.info("添加物品到背包");
            addItemToBp(vo.getRecordDetailId(), vo.getUserId(), vo.getGiftItems());
            log.info("礼物{}赠送成功", vo.getRecordId());
        }
    }

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
        Response response = userService.changePwdVerify(userId, de, s2, client);
        if (!response.isSuccessful()) {
            return response;
        }
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
                vbp = vbpMapper.selectByUserIdBpId(userId, sendVo.getBpId());
                quantity = map.get(id);
                quantity = quantity == null ? 1 : quantity + 1;
                if (vbp == null || vbp.getQuantity() < quantity) {
                    return ResponseFactory.err("赠送的礼物不存在背包中或赠送的数量大于背包中的数量!");
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
            list.add(assembleDetail(sendVo, item));
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
            addGiftNotifyMessage(userId, sendVo.getFriendUserId(), record.getId(), list);
            addItemToBp(detail.getId(), detail.getUserId(), list);
            return ResponseFactory.sucMsg("赠送成功");
        } else {
            return ResponseFactory.sucMsg("礼物将在预定时间内送达!");
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
     * @param recordId     礼物记录id
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
        // 添加消息记录
        String summary = String.format("%s送您了%s", Constants.genName(friend), list.get(0).getTitle());
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
    private GiftItemVo assembleDetail(GiftSendVo sendVo, Vbp vbp) {
        GiftItemVo detail = new GiftItemVo();
        detail.setBpId(vbp.getId());
        detail.setPrice(vbp.getPrice());
        detail.setTitle(vbp.getTitle());
        detail.setCover(vbp.getCover());
        detail.setDescription(vbp.getDescription());
        detail.setTargetType(vbp.getType());
        detail.setTargetId(vbp.getTargetId());
        detail.setBrand(vbp.getBrand());
        detail.setGiftType(sendVo.getBpId().equals(vbp.getId()) ? Constants.GIFT_M_TYPE.MAIN : Constants.GIFT_M_TYPE.SUB);
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
        newDetail.setIsReply((byte) 1);
        newDetail.setReply(reply);
        giftRecordDetailMapper.updateByPrimaryKeySelective(newDetail);
        return ResponseFactory.suc();
    }

    /*----------------------------------------礼物赠送答谢结束------------------------------------------------------*/
}
