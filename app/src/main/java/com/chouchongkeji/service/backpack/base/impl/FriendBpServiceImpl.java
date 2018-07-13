package com.chouchongkeji.service.backpack.base.impl;

import com.chouchongkeji.dial.dao.backpack.BpItemMapper;
import com.chouchongkeji.dial.dao.backpack.ForRecordMapper;
import com.chouchongkeji.dial.pojo.backpack.BpItem;
import com.chouchongkeji.dial.pojo.backpack.ForRecord;
import com.chouchongkeji.dial.pojo.backpack.Vbp;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.base.BpService;
import com.chouchongkeji.service.backpack.base.FriendBpService;
import com.chouchongkeji.service.backpack.base.vo.ForRecordVo;
import com.chouchongkeji.util.Constants;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author linqin
 * @date 2018/7/12
 */
@Service
@Transactional(rollbackFor =Exception.class ,isolation = Isolation.REPEATABLE_READ)
public class FriendBpServiceImpl implements FriendBpService {

    @Autowired
    private BpItemMapper bpItemMapper;

    @Autowired
    private BpService bpService;

    @Autowired
    private ForRecordMapper forRecordMapper;

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
        return ResponseFactory.sucMsg("向好友发起索要背包物品消息成功");
    }


    /**
     * 索要记录列表
     *
     * @param userId
     * @return
     * @author linqin
     * @date 2018/7/12
     */
    @Override
    public Response getRecordList(Integer userId, PageQuery pageQuery) {
        //分页
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        //查询索要记录列表
        List<ForRecordVo> list = forRecordMapper.selectAllByUserId(userId);
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
        //同意或拒绝好友索要物品
        if (forRecord.getOperation() == Constants.BP_ITEM.DEFAULT &&
                forRecord.getStatus() == Constants.FOR_RECORD_STATUS.ASK_FOR_THE){
            //同意好友索要物品
            if (operation == Constants.BP_ITEM.AGREE){
                //更新索要记录状态，操作
                forRecord.setStatus(Constants.FOR_RECORD_STATUS.ACCESS);//索要成功
                forRecord.setOperation(operation);//同意索要
                forRecordMapper.updateByPrimaryKeySelective(forRecord);
                //更新用户背包（减少该物品）
                BpItem bpItem = bpItemMapper.selectByUserIdAndBpItemId(userId,forRecord.getBpId());
                if (bpItem == null) {
                    throw new ServiceException(ErrorCode.ERROR.getCode(),"背包物品为空");
                }
                bpItem.setQuantity(bpItem.getQuantity()-1);
                int update = bpItemMapper.updateByPrimaryKeySelective(bpItem);
                if (update <1) {
                    throw new ServiceException(ErrorCode.ERROR.getCode(),"用户背包更新失败");
                }
                //更新好友背包（添加物品到好友背包里）
                int add = bpService.addFromFriendBp(forRecord, bpItem);
                if (add<0){
                    return ResponseFactory.err("物品添加到好友背包失败");
                }
//                BpItem friendBp = new BpItem();
//                friendBp.setUserId(forRecord.getUserId());
//                friendBp.setTargetId(bpItem.getTargetId());
//                friendBp.setQuantity(1);
//                friendBp.setPrice(bpItem.getPrice());
//                friendBp.setFrom(bpItem.getFrom());
//                friendBp.setType(bpItem.getType());
//                int insert = bpItemMapper.insert(friendBp);
//                if (insert<0){
//                    return ResponseFactory.err("物品添加到好友背包失败");
//                }
                return ResponseFactory.sucMsg("已同意好友索要物品");
            }
            //拒绝好友索要物品
            if (operation == Constants.BP_ITEM.REFUSE) {
                //更新索要记录状态，操作
                forRecord.setStatus(Constants.FOR_RECORD_STATUS.FAIL);//索要失败
                forRecord.setOperation(operation);//拒绝索要
                forRecordMapper.updateByPrimaryKeySelective(forRecord);
                return ResponseFactory.err("已拒绝好友索要物品");
            }
        }
        return ResponseFactory.sucMsg("该物品已做操作或拒绝操作");
    }



}
