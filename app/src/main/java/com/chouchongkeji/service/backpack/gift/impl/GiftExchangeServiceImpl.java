package com.chouchongkeji.service.backpack.gift.impl;

import com.chouchongkeji.dial.dao.backpack.VbpMapper;
import com.chouchongkeji.dial.dao.backpack.gift.GiftExchangeMapper;
import com.chouchongkeji.dial.pojo.backpack.Vbp;
import com.chouchongkeji.dial.pojo.backpack.gift.GiftExchange;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.backpack.base.BpService;
import com.chouchongkeji.service.backpack.gift.GiftExchangeService;
import com.chouchongkeji.service.backpack.gift.vo.GiftExDetailVo;
import com.chouchongkeji.service.backpack.gift.vo.GiftExItemVo;
import com.chouchongkeji.service.backpack.gift.vo.GiftExListVo;
import com.chouchongkeji.util.Constants;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author linqin
 * @date 2018/7/13
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class GiftExchangeServiceImpl implements GiftExchangeService {

    @Autowired
    private VbpMapper vbpMapper;

    @Autowired
    private GiftExchangeMapper giftExchangeMapper;

    @Autowired
    private BpService bpService;
    /**
     * 用户添加想要交换的物品到交换记录
     *
     * @param userId
     * @param friendUserId 好友Id
     * @param exchangeBpId 想交换的礼物 背包Id用逗号隔开
     * @param wantBpId     想要的礼物  背包Id用逗号隔开
     * @return
     * @author linqin
     * @date 2018/7/13
     */
    @Override
    public Response addItem(Integer userId, Integer friendUserId, HashSet<Long> exchangeBpId, HashSet<Long> wantBpId) {
        //查询用户背包物品
        List<GiftExItemVo> list = new ArrayList<>();
        for (Long bpId : exchangeBpId) {
            Vbp vbp = vbpMapper.selectByUserIdBpId(userId, bpId);
            if (vbp == null) {
                return ResponseFactory.err("背包里不存在该物品");
            }
            //保存想交换的礼物
            list.add(assembleGiftExItemVo(vbp));
        }
        //查询好友背包物品
        List<GiftExItemVo> fList = new ArrayList<>();
        for (Long fBpId : wantBpId) {
            Vbp friendBp = vbpMapper.selectByUserIdBpId(friendUserId, fBpId);
            if (friendBp == null) {
                return ResponseFactory.err("好友背包里不存在该物品");
            }
            //保存想交换的礼物
            fList.add(assembleGiftExItemVo(friendBp));
        }
        //添加到礼物交换记录
        GiftExchange giftExchange = new GiftExchange();
        giftExchange.setUserId(userId);
        giftExchange.setFriendUserId(friendUserId);
        giftExchange.setExchangeGifts(list);
        giftExchange.setWantGifts(fList);
        giftExchange.setStatus(Constants.GIFT_EXCHANGE.USER_EX);//1.用户提交
        int insert = giftExchangeMapper.insert(giftExchange);
        if (insert < 1) {
            return ResponseFactory.err("");
        }
        return ResponseFactory.suc("用户添加交换礼物成功", "http://baidu.com");
    }

    private GiftExItemVo assembleGiftExItemVo(Vbp vbp) {
        GiftExItemVo giftExItemVo = new GiftExItemVo();
        giftExItemVo.setBpId(vbp.getId());
        giftExItemVo.setTargetId(vbp.getTargetId());
        giftExItemVo.setPrice(vbp.getPrice());
        giftExItemVo.setTitle(vbp.getTitle());
        giftExItemVo.setCover(vbp.getCover());
        giftExItemVo.setDescription(vbp.getDescription());
        giftExItemVo.setBrand(vbp.getBrand());
        giftExItemVo.setType(vbp.getType());
        giftExItemVo.setQuantity(vbp.getQuantity());
        return giftExItemVo;
    }


    /**
     * 好友提交要交换的物品
     *
     * @param userId
     * @param giftExchangeId 物品交换记录Id
     * @param submitId       提交的物品Id 背包Id用逗号隔开
     * @return
     * @author linqin
     * @date 2018/7/13
     */
    @Override
    public Response friendAddItem(Integer userId, Integer giftExchangeId, HashSet<Long> submitId) {
        //查询交换
        GiftExchange gift = giftExchangeMapper.selectByUserIdAndId(userId, giftExchangeId);
        if (gift == null) {
            return ResponseFactory.err("不存在该交换记录");
        }
        //查询背包物品
        List<GiftExItemVo> list = new ArrayList<>();
        for (Long fBpId : submitId) {
            Vbp friendBpItem = vbpMapper.selectByUserIdBpId(userId, fBpId);
            if (friendBpItem == null) {
                return ResponseFactory.err("背包里不存在该物品");
            }
            //添加想交换的背包物品
            list.add(assembleGiftExItemVo(friendBpItem));
        }
        //添加到礼物交换记录
        gift.setStatus(Constants.GIFT_EXCHANGE.FRIEND_EX); //好友提交
        gift.setSubmitGifts(list);
        int update = giftExchangeMapper.updateByPrimaryKeySelective(gift);
        if (update < 0) {
            return ResponseFactory.err("礼物交换添加失败");
        }
        return ResponseFactory.suc("礼物交换添加成功", "http://baidu.com");
    }

    /**
     * 确认交换礼物
     *
     * @param userId
     * @param giftExchangeId 礼物交换记录id
     * @param operation      操作 0-默认，1-确认交换，2-取消交换
     * @return
     * @author linqin
     * @date 2018/7/14
     */
    @Override
    public Response confirm(Integer userId, Integer giftExchangeId, Byte operation) {
        //查询交换
        GiftExchange gift = giftExchangeMapper.selectByUserIdGiftExId(userId, giftExchangeId);
        if (gift == null) {
            return ResponseFactory.err("不存在该交换记录");
        }
        if (operation == Constants.BP_ITEM.DEFAULT || operation == Constants.BP_ITEM.REFUSE){
            return ResponseFactory.sucMsg("不交换礼物");
        }
        //确认交换
        //更新礼物交换状态
        gift.setStatus(Constants.GIFT_EXCHANGE.ACCESS);
        int update = giftExchangeMapper.updateByPrimaryKeySelective(gift);
        if (update < 0) {
            return ResponseFactory.err("状态更新失败");
        }
        //跟新背包
        List<GiftExItemVo> submitGifts = gift.getSubmitGifts();//好友交换的物品
        for (GiftExItemVo submitGift : submitGifts) {
            //减少好友背包里的物品数量
           int count = vbpMapper.updateQuantityByBpIdUserId(submitGift.getBpId(),gift.getFriendUserId(),submitGift.getQuantity());
           if (count<1){
               throw new  ServiceException(ErrorCode.ERROR.getCode(),"更新数量失败");
           }
        }
        //把好友交换的物品添加到用户背包
        int count = bpService.addFromGiftExchange(giftExchangeId, userId, submitGifts);
        if (count<1){
            throw new  ServiceException(ErrorCode.ERROR.getCode(),"物品添加失败");
        }
        //更新背包
        List<GiftExItemVo> exchangeGifts = gift.getExchangeGifts(); //用户交换的物品
        for (GiftExItemVo exchangeGift : exchangeGifts) {
            // 减少用户背包里的物品数量
            int i = vbpMapper.updateQuantityByBpIdUserId(exchangeGift.getBpId(), userId, exchangeGift.getQuantity());
            if (i<1){
                throw new  ServiceException(ErrorCode.ERROR.getCode(),"更新数量失败");
            }
        }
        //把用户交换的物品添加到好友背包
        int add = bpService.addFromGiftExchange(giftExchangeId, gift.getFriendUserId(), exchangeGifts);
        if (add<1){
            throw new  ServiceException(ErrorCode.ERROR.getCode(),"物品添加失败");
        }
        return ResponseFactory.sucMsg("礼物交换成功");

    }

    /**
     * 交换记录列表
     *
     * @param userId
     * @param status      状态，0-全部，1-交易中，2-已完成，
     * @return
     * @author linqin
     * @date 2018/7/14
     */
    @Override
    public Response getList(Integer userId, Byte status, PageQuery pageQuery) {
        //分页
        PageHelper.startPage(pageQuery.getPageNum(),pageQuery.getPageSize());
        //查询列表
        List<GiftExListVo> listVos = giftExchangeMapper.selectListByUserIdStatus(userId,status);
        if (listVos == null){
            return ResponseFactory.sucMsg("交换记录为空");
        }
        return ResponseFactory.sucData(listVos);
    }


    /**
     * 礼物交换详情
     * @param userId
     * @param giftExchangeId 礼物交换记录id
     * @return
     * @author linqin
     * @date 2018/7/15
     */
    @Override
    public Response giftExDetail(Integer userId, Integer giftExchangeId) {
        GiftExDetailVo Vos = giftExchangeMapper.selectByUserIdgiftEXchangeId(userId,giftExchangeId);
        if (Vos == null){
            return ResponseFactory.sucMsg("交换记录为空");
        }
        return ResponseFactory.sucData(Vos);

    }
}
