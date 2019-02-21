package com.chouchongkeji.service.home.impl;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.alibaba.fastjson.JSONObject;
import com.chouchongkeji.dial.dao.home.WelfareMapper;
import com.chouchongkeji.dial.dao.home.WelfareRecordMapper;
import com.chouchongkeji.dial.pojo.backpack.BpItem;
import com.chouchongkeji.dial.pojo.home.Welfare;
import com.chouchongkeji.dial.pojo.home.WelfareRecord;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.backpack.base.BpService;
import com.chouchongkeji.service.home.WelfareService;
import com.chouchongkeji.util.Constants;
import com.chouchongkeji.util.OrderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author linqin
 * @date 2019/2/20 16:25
 */
@Service
@Transactional(rollbackFor = Exception.class,isolation = Isolation.REPEATABLE_READ)
public class WelfareServiceImpl implements WelfareService {

    @Autowired
    private WelfareMapper welfareMapper;

    @Autowired
    private BpService bpService;

    @Autowired
    private WelfareRecordMapper welfareRecordMapper;

    @Autowired
    private OrderHelper orderHelper;

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
        if (welfare == null){
            throw new ServiceException(ErrorCode.ERROR.getCode(),"现在没有福利");
        }
        // 领取过就不能再领取
        WelfareRecord record = welfareRecordMapper.selectByUserIdWelfareId(userId,welfare.getId());
        if (record != null){
            throw new ServiceException(ErrorCode.ERROR.getCode(),"您已经领取过礼物");
        }
        // 剩余数量小于等于0则无法领取
        if (welfare.getCount() <=0){
            throw new ServiceException(ErrorCode.ERROR.getCode(),"礼物已被抢完，请关注下次的福利");
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
        welfare.setCount(welfare.getCount()-1);
        int i = welfareMapper.updateByPrimaryKeySelective(welfare);
        if (i < 1) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "");
        }
        return ResponseFactory.sucMsg("领取成功");
    }
}
