package com.chouchongkeji.service.home.impl;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chouchongkeji.dial.dao.backpack.gift.GiftRecordDetailMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemSkuMapper;
import com.chouchongkeji.dial.dao.gift.virtualItem.VirtualItemMapper;
import com.chouchongkeji.dial.dao.home.WelfareMapper;
import com.chouchongkeji.dial.dao.home.WelfareRecordMapper;
import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.pojo.backpack.BpItem;
import com.chouchongkeji.dial.pojo.backpack.gift.GiftRecordDetail;
import com.chouchongkeji.dial.pojo.gift.item.ItemSku;
import com.chouchongkeji.dial.pojo.gift.virtualItem.GiftRecord;
import com.chouchongkeji.dial.pojo.gift.virtualItem.VirtualItem;
import com.chouchongkeji.dial.pojo.home.Welfare;
import com.chouchongkeji.dial.pojo.home.WelfareRecord;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.backpack.base.BpService;
import com.chouchongkeji.service.backpack.gift.GiftService;
import com.chouchongkeji.service.backpack.gift.impl.GiftServiceImpl;
import com.chouchongkeji.service.backpack.gift.vo.GiftItemVo;
import com.chouchongkeji.service.backpack.gift.vo.WXGetGiftResVo;
import com.chouchongkeji.service.home.WelfareService;
import com.chouchongkeji.service.home.vo.WelfareVo;
import com.chouchongkeji.service.message.vo.GiftMessageVo;
import com.chouchongkeji.util.Constants;
import com.chouchongkeji.util.OrderHelper;
import com.yichen.auth.service.UserDetails;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author linqin
 * @date 2019/2/20 16:25
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class WelfareServiceImpl implements WelfareService {

    @Autowired
    private WelfareMapper welfareMapper;

    @Autowired
    private BpService bpService;

    @Autowired
    private WelfareRecordMapper welfareRecordMapper;

    @Autowired
    private OrderHelper orderHelper;

    @Autowired
    private ItemSkuMapper itemSkuMapper;

    @Autowired
    private VirtualItemMapper virtualItemMapper;
    @Autowired
    private AppUserMapper appUserMapper;


    /**
     * 确认领取福利
     *
     * @param userId
     * @return
     * @author linqin
     * @date 2019/2/20
     */
    @Override
    public Response confirmWelfare(Integer userId) {
        // 获取福利商品详情
        Welfare welfare = welfareMapper.selectAllByTime();
        if (welfare == null) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "现在没有福利");
        }
        // 领取过就不能再领取
        WelfareRecord record = welfareRecordMapper.selectByUserIdWelfareId(userId, welfare.getId());
        if (record != null) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "您已经领取过礼物");
        }
        // 剩余数量小于等于0则无法领取
        if (welfare.getCount() <= 0) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "礼物已被抢完，请关注下次的福利");
        }
        // 到福利生效时间才能领取
        if (welfare.getTargetDate().getTime() > new Date().getTime()) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "还没到福利领取时间");
        }
        // 领取礼物加入背包
        BpItem bpItem = new BpItem();
        bpItem.setId(orderHelper.genOrderNo(7, 7));
        bpItem.setUserId(userId);
        bpItem.setTargetId(welfare.getTargetId());
        bpItem.setQuantity(1);
        bpItem.setPrice(welfare.getPrice());
        bpItem.setType(welfare.getType());
        bpItem.setBuyTime(new Date());
        JSONObject object = new JSONObject();
        object.put("type", Constants.BP_ITEM_FROM.WELFARE);
        object.put("orderNo", welfare.getId());
        bpItem.setFrom(object.toJSONString());
        int add = bpService.add(bpItem);
        if (add < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "");
        }
        // 添加福利领取记录
        WelfareRecord welfareRecord = new WelfareRecord();
        welfareRecord.setWelfareId(welfare.getId());
        welfareRecord.setUserId(userId);
        int insert = welfareRecordMapper.insert(welfareRecord);
        if (insert < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "");
        }
        // 减少数量
        welfare.setCount(welfare.getCount() - 1);
        int i = welfareMapper.updateByPrimaryKeySelective(welfare);
        if (i < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "");
        }
        // 如果福利是商品,减少sku
        if (welfare.getType() == 1) {
            int count = itemSkuMapper.updateStockBySkuId(welfare.getTargetId(), -1);
            if (count < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "库存更新失败");
            }
        }
        return ResponseFactory.sucMsg("领取成功");
    }


    /**
     * app获取整点福利
     *
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    @Override
    public Response getWelfare(Integer userId) {
        WelfareVo welfare = welfareMapper.selectByTime();
        // 查看是否领取过
        if (welfare != null) {
            WelfareRecord record = welfareRecordMapper.selectByUserIdWelfareId(userId, welfare.getId());
            if (record == null) {
                welfare.setIsReceive(1);//未领取
            } else {
                welfare.setIsReceive(2); //已领取
            }
            welfare.setRemainTime(welfare.getTargetDate().getTime() - System.currentTimeMillis());
            if (welfare.getRemainTime() < 0) {
                welfare.setRemainTime(0L);
            }
        }

        return ResponseFactory.sucData(welfare);

    }


    /**
     * 微信领取福利
     *
     * @param userDetails
     * @param id          福利id
     * @return
     * @author linqin
     * @date 2019/2/20
     */
    @Override
    public Response wxConfirmWelfare(UserDetails userDetails, Integer id) {
        // 返回参数
        WXGetGiftResVo<GiftMessageVo> vo = new WXGetGiftResVo<>();
        Welfare welfare = welfareMapper.selectById(id);
        if (welfare == null) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "福利不存在");
        }
        // 领取过就不能再领取
        WelfareRecord record = welfareRecordMapper.selectByUserIdWelfareId(userDetails.getUserId(), welfare.getId());
        if (record != null) {
            List<GiftItemVo> itemVos = itemDetail(welfare);
            vo = messageGift(userDetails.getUserId(), welfare.getCount(), itemVos);
            vo.setStatus(2); //已经领取过礼物
            return ResponseFactory.sucData(vo);
        }
        // 剩余数量小于等于0则无法领取
        if (welfare.getCount() <= 0) {
            List<GiftItemVo> itemVos = itemDetail(welfare);
            vo = messageGift(userDetails.getUserId(), welfare.getCount(), itemVos);
            vo.setStatus(3); //礼物已经被领完
            return ResponseFactory.sucData(vo);
        }
        // 领取礼物加入背包
        BpItem bpItem = new BpItem();
        bpItem.setId(orderHelper.genOrderNo(7, 7));
        bpItem.setUserId(userDetails.getUserId());
        bpItem.setTargetId(welfare.getTargetId());
        bpItem.setQuantity(1);
        bpItem.setPrice(welfare.getPrice());
        bpItem.setType(welfare.getType());
        bpItem.setBuyTime(new Date());
        JSONObject object = new JSONObject();
        object.put("type", Constants.BP_ITEM_FROM.WELFARE);
        object.put("orderNo", welfare.getId());
        bpItem.setFrom(object.toJSONString());
        int add = bpService.add(bpItem);
        if (add < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "");
        }
        // 添加福利领取记录
        WelfareRecord welfareRecord = new WelfareRecord();
        welfareRecord.setWelfareId(welfare.getId());
        welfareRecord.setUserId(userDetails.getUserId());
        int insert = welfareRecordMapper.insert(welfareRecord);
        if (insert < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "");
        }
        // 减少数量
        welfare.setCount(welfare.getCount() - 1);
        int i = welfareMapper.updateByPrimaryKeySelective(welfare);
        if (i < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "");
        }
        // 如果福利是商品,减少sku
        if (welfare.getType() == 1) {
            int count = itemSkuMapper.updateStockBySkuId(welfare.getTargetId(), -1);
            if (count < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "库存更新失败");
            }
        }
        List<GiftItemVo> itemVos = itemDetail(welfare);
        vo = messageGift(userDetails.getUserId(), welfare.getCount(), itemVos);
        vo.setStatus(1); //领取礼物成功
        return ResponseFactory.sucData(vo);
    }


    private List<GiftItemVo> itemDetail(Welfare welfare) {
        List<GiftItemVo> itemVos = new ArrayList<>();
        // 如果福利是商品,减少sku
        if (welfare.getType() == 1) {
            // 返回商品信息
            GiftItemVo item = itemSkuMapper.selectBySkuIdAll(welfare.getTargetId());
            item.setTargetType(welfare.getType());
            itemVos.add(item);
        } else if (welfare.getType() == 2) {
            GiftItemVo item = virtualItemMapper.selectById(welfare.getTargetId());
            item.setTargetType(welfare.getType());
            itemVos.add(item);
        }
        return itemVos;
    }


    /**
     * @param userId
     * @param itemVos
     * @return
     */
    private WXGetGiftResVo<GiftMessageVo> messageGift(Integer userId, Integer size, List<GiftItemVo> itemVos) {
        // 返回未被领取的礼物
        WXGetGiftResVo<GiftMessageVo> vo = new WXGetGiftResVo<>();
        vo.setType((byte) 1);//1微信扫码领取领取
        // 详细信息
        GiftMessageVo messageVo = new GiftMessageVo();
        AppUser appUser = appUserMapper.selectByUserId(userId);
        if (appUser != null) {
            messageVo.setAvatar(appUser.getAvatar());
            messageVo.setNickname(appUser.getNickname());
        }
        messageVo.setCreated(new Date());
        messageVo.setGreetting("福利领取");
        messageVo.setGiftItems(itemVos);
        messageVo.setReNum(size); //剩余礼物
        vo.setGiftInfo(messageVo);
        return vo;
    }

}
