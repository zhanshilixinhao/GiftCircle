package com.chouchongkeji.service.backpack.base.impl;

import com.alibaba.fastjson.JSONObject;
import com.chouchongkeji.dial.dao.backpack.BpItemMapper;
import com.chouchongkeji.dial.dao.backpack.ForRecordMapper;
import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.pojo.backpack.BpItem;
import com.chouchongkeji.dial.pojo.backpack.ForRecord;
import com.chouchongkeji.dial.pojo.backpack.Vbp;
import com.chouchongkeji.dial.pojo.gift.virtualItem.AppMessage;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.base.BpService;
import com.chouchongkeji.service.backpack.base.FriendBpService;
import com.chouchongkeji.service.backpack.base.vo.ForRecordVo;
import com.chouchongkeji.service.message.MessageService;
import com.chouchongkeji.util.Constants;
import com.chouchongkeji.util.OrderHelper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linqin
 * @date 2018/7/12
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class FriendBpServiceImpl implements FriendBpService {

    @Autowired
    private BpItemMapper bpItemMapper;

    @Autowired
    private BpService bpService;

    @Autowired
    private ForRecordMapper forRecordMapper;

    @Autowired
    private OrderHelper orderHelper;

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private MessageService messageService;

    /**
     * 好友背包列表
     *
     * @param userId       用户id
     * @param friendUserId 好友用户Id
     * @param type         1 物品 2 虚拟物品 3 优惠券
     * @return
     * @author linqin
     * @date 2018/7/12
     */
    @Override
    public Response getList(Integer userId, Integer friendUserId, Integer type, PageQuery pageQuery) {
        //分页
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        List<Vbp> list = bpItemMapper.selectAllByUserId(friendUserId, type);
        return ResponseFactory.sucData(list);
    }


    /**
     * 好友背包物品详情
     *
     * @param userId       用户Id
     * @param friendUserId 好友用户Id
     * @param bpId         背包id
     * @return
     * @author linqin
     * @date 2018/7/12
     */
    @Override
    public Response itemDetail(Integer userId, Integer friendUserId, Long bpId) {
        //查询好友背包物品详细信息
        Vbp vbpItem = bpItemMapper.selectByUserIdBpId(friendUserId, bpId);
        return ResponseFactory.sucData(vbpItem);
    }


    /**
     * 好友背包物品添加到索要记录(向好友索要物品)
     *
     * @param userId       用户id
     * @param friendUserId 好友用户Id
     * @param bpId         背包id
     * @return
     * @author linqin
     * @date 2018/7/12
     */
    @Override
    public Response addForRecord(Integer userId, Integer friendUserId, Long bpId) {
        //查询好友背包物品
        Vbp vbpItem = bpItemMapper.selectByUserIdBpId(friendUserId, bpId);
        if (vbpItem == null) {
            return ResponseFactory.err("好友背包中没有该物品");
        }
        ForRecord addForRecord = new ForRecord();
        addForRecord.setUserId(userId);
        addForRecord.setFriendUserId(friendUserId);
        addForRecord.setBpId(bpId);
        addForRecord.setStatus(Constants.FOR_RECORD_STATUS.ASK_FOR_THE);
        addForRecord.setOperation(Constants.BP_ITEM.DEFAULT);
        int insert = forRecordMapper.insert(addForRecord);
        if (insert < 0) {
            return ResponseFactory.err("");
        }
        // 查询用户昵称
        AppUser appUser = appUserMapper.selectByUserId(userId);
        if (appUser == null) {
            return ResponseFactory.err("用户不存在");
        }
        //添加系统消息
        AppMessage appMessage = new AppMessage();
        appMessage.setTitle("系统通知");
        appMessage.setSummary("您的好友向您索要了礼品!");
        appMessage.setContent(appUser.getNickname() + "  向您索要礼品 ");
        appMessage.setTargetId(bpId);
        appMessage.setTargetType((byte) 25);
        appMessage.setMessageType((byte) 2);
        int in = messageService.addMessage(appMessage, new ArrayList<Integer>() {
            {
                add(friendUserId);
            }
        });
        if (in < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "添加系统消息失败");
        }
        return ResponseFactory.sucMsg("向好友发起索要背包物品消息成功");
    }


    /**
     * 索要记录列表
     *
     * @param userId
     * @param type   1-用户向好友索要商品 ，2-好友向用户索要商品
     * @return
     * @author linqin
     * @date 2018/7/12
     */
    @Override
    public Response getRecordList(Integer userId, PageQuery pageQuery, Integer type) {
        //分页
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        //查询索要记录列表
        List<ForRecordVo> list = forRecordMapper.selectAllByUserId(userId, type);
        return ResponseFactory.sucData(list);
    }

    /**
     * 同意或者拒绝好友索要背包物品
     *
     * @param userId
     * @param operation   0-默认无操作，1-同意好友索要，2-拒绝好友索要
     * @param forRecordId 索要记录Id
     * @return
     * @author linqin
     * @date 2018/7/13
     */
    @Override
    public Response operation(Integer userId, Byte operation, Integer forRecordId) {
        //查询是否有操作权限
        ForRecord forRecord = forRecordMapper.selectByUserIdAndForRecordId(userId, forRecordId);
        if (forRecord == null) {
            return ResponseFactory.err("没有操作权限");
        }
        // 查询用户昵称
        AppUser appUser = appUserMapper.selectByUserId(userId);
        if (appUser == null) {
            return ResponseFactory.err("用户不存在");
        }
        //同意或拒绝好友索要物品
        if (forRecord.getOperation() == Constants.BP_ITEM.DEFAULT &&
                forRecord.getStatus() == Constants.FOR_RECORD_STATUS.ASK_FOR_THE) {
            //同意好友索要物品
            if (operation == Constants.BP_ITEM.AGREE) {
                //更新索要记录状态，操作
                forRecord.setStatus(Constants.FOR_RECORD_STATUS.ACCESS);//索要成功
                forRecord.setOperation(operation);//同意索要
                forRecordMapper.updateByPrimaryKeySelective(forRecord);
                //更新用户背包（减少该物品）
                BpItem bpItem = bpItemMapper.selectByUserIdAndBpItemId(userId, forRecord.getBpId());
                if (bpItem == null) {
                    throw new ServiceException(ErrorCode.ERROR.getCode(), "背包物品为空");
                }
                bpItem.setQuantity(bpItem.getQuantity() - 1);
                int update = bpItemMapper.updateByPrimaryKeySelective(bpItem);
                if (update < 1) {
                    throw new ServiceException(ErrorCode.ERROR.getCode(), "用户背包更新失败");
                }
                //更新好友背包（添加物品到好友背包里）
//                int add = bpService.addFromFriendBp(forRecord, bpItem);
//                if (add<0){
//                    return ResponseFactory.err("物品添加到好友背包失败");
//                }
                JSONObject object = new JSONObject();
                object.put("type", Constants.BP_ITEM_FROM.ASK_FOR);
                object.put("forRecordId", forRecord.getId());
                BpItem friendBp = new BpItem();
                friendBp.setId(orderHelper.genOrderNo(7, 7));
                friendBp.setUserId(forRecord.getUserId());
                friendBp.setTargetId(bpItem.getTargetId());
                friendBp.setQuantity(1);
                friendBp.setPrice(bpItem.getPrice());
                friendBp.setFrom(object.toJSONString());
                friendBp.setType(bpItem.getType());
                int insert = bpItemMapper.insert(friendBp);
                if (insert < 0) {
                    return ResponseFactory.err("物品添加到好友背包失败");
                }
                //添加系统消息
                AppMessage appMessage = new AppMessage();
                appMessage.setTitle("系统通知");
                appMessage.setSummary("您的好友已同意了您的索要!");
                appMessage.setContent("您的好友"+appUser.getNickname()+ "同意了您的礼品索要");
                appMessage.setTargetId(forRecord.getBpId());
                appMessage.setTargetType((byte) 25);
                appMessage.setMessageType((byte) 2);
                int in = messageService.addMessage(appMessage, new ArrayList<Integer>() {
                    {
                        add(forRecord.getUserId());
                    }
                });
                if (in < 1) {
                    throw new ServiceException(ErrorCode.ERROR.getCode(), "添加系统消息失败");
                }
                return ResponseFactory.sucMsg("已同意好友索要物品");
            }
            //拒绝好友索要物品
            if (operation == Constants.BP_ITEM.REFUSE) {
                //更新索要记录状态，操作
                forRecord.setStatus(Constants.FOR_RECORD_STATUS.FAIL);//索要失败
                forRecord.setOperation(operation);//拒绝索要
                forRecordMapper.updateByPrimaryKeySelective(forRecord);
                //添加系统消息
                AppMessage appMessage = new AppMessage();
                appMessage.setTitle("系统通知");
                appMessage.setSummary("您的好友已拒绝了您的索要!");
                appMessage.setContent("您的好友"+appUser.getNickname()+ "拒绝了您的礼品索要");
                appMessage.setTargetId(forRecord.getBpId());
                appMessage.setTargetType((byte) 25);
                appMessage.setMessageType((byte) 2);
                int in = messageService.addMessage(appMessage, new ArrayList<Integer>() {
                    {
                        add(forRecord.getUserId());
                    }
                });
                if (in < 1) {
                    throw new ServiceException(ErrorCode.ERROR.getCode(), "添加系统消息失败");
                }
                return ResponseFactory.err("已拒绝好友索要物品");
            }
        }
        return ResponseFactory.sucMsg("该物品已做操作或拒绝操作");
    }


}
