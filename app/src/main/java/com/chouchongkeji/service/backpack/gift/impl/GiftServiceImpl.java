package com.chouchongkeji.service.backpack.gift.impl;

import com.chouchongkeji.dial.dao.backpack.VbpMapper;
import com.chouchongkeji.dial.dao.backpack.consignment.GiftRecordDetailMapper;
import com.chouchongkeji.dial.dao.backpack.consignment.GiftRecordMapper;
import com.chouchongkeji.dial.dao.backpack.item.BpItemMapper;
import com.chouchongkeji.dial.pojo.backpack.Vbp;
import com.chouchongkeji.dial.pojo.friend.Friend;
import com.chouchongkeji.dial.pojo.gift.virtualItem.GiftRecord;

import com.chouchongkeji.dial.pojo.gift.virtualItem.GiftRecordDetail;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.backpack.gift.GiftSendVo;
import com.chouchongkeji.service.backpack.gift.GiftService;
import com.chouchongkeji.service.user.friend.FriendService;
import com.chouchongkeji.util.Constants;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/7/2
 */

@Service
public class GiftServiceImpl implements GiftService {

    @Autowired
    private GiftRecordMapper giftRecordMapper;

    @Autowired
    private GiftRecordDetailMapper giftRecordDetailMapper;

    @Autowired
    private FriendService friendService;

    @Autowired
    private VbpMapper vbpMapper;

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
    public Response sendForApp(Integer userId, GiftSendVo sendVo) {
        // 判断和被赠送的用户是不是好友关系
        Friend friend = friendService.isFriend(userId, sendVo.getFriendUserId());
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
        List<GiftRecordDetail> list = new ArrayList<>();
        for (Vbp item : vbps) {
            list.add(assembleDetail(record, sendVo, item));
            // 更新背包物品的数量
            count = vbpMapper.updateQuantityById(item.getId(), 1, item.getType());
            if (count < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新数量失败!");
            }
        }
        // 批量插入
        count = giftRecordDetailMapper.insertBatch(list);
        if (count == 0) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "赠送失败!");
        }
        // 如果是立即赠送添加礼物赠送消息
        return ResponseFactory.sucMsg("赠送成功");
    }

    /**
     * 组装详情
     *
     * @param record 礼物记录
     * @param sendVo 赠送新词
     * @param vbp
     * @return
     */
    private GiftRecordDetail assembleDetail(GiftRecord record, GiftSendVo sendVo, Vbp vbp) {
        GiftRecordDetail detail = new GiftRecordDetail();
        detail.setGiftRecordId(record.getId());
        detail.setBpId(vbp.getId());
        detail.setPrice(vbp.getPrice());
        detail.setQuantity(1);
        detail.setUserId(sendVo.getFriendUserId());
        detail.setStatus(record.getStatus());
        detail.setType(sendVo.getBpId().equals(vbp.getId()) ? Constants.GIFT_M_TYPE.MAIN : Constants.GIFT_M_TYPE.SUB);
        return detail;
    }
}
