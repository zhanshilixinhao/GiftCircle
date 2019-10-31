package com.chouchongkeji.service.iwant.merchant.impl;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.dial.dao.iwant.merchant.MerchantMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.dial.pojo.iwant.merchant.Merchant;
import com.chouchongkeji.service.iwant.merchant.MerchantService;
import com.chouchongkeji.service.iwant.merchant.vo.MerchantVo;
import com.chouchongkeji.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yy
 * @date 2018/6/20
 **/

@Service
public class MerchantServiceImpl implements MerchantService{
    @Autowired
    private MerchantMapper merchantMapper;

    /**
     * 商家认证申请
     *
     * @param: [userId 用户id,merchantVo 商家信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/20
     */
    @Override
    public Response applyMerchant(Integer userId, MerchantVo merchantVo) {
        Merchant merchant = merchantMapper.selectByUserId(userId);
        if (merchant != null) {
            if (merchant.getStatus() == Constants.MERCHANT_STATUS.PASS){
                return ResponseFactory.err("商家认证已提交,并且已通过!");
            }else if (merchant.getStatus() == Constants.MERCHANT_STATUS.NO_PASS){
                // 被驳回，重新提交
                merchant.setUserId(userId);
                merchant.setUpdated(new Date());
                merchant.setStatus((byte)2);
                merchant.setRegistrationNo(merchantVo.getRegistrationNo());
                merchant.setPhone(merchantVo.getPhone());
                if (merchantVo.getOtherPics() != null && merchantVo.getOtherPics().size() > 0) {
                    merchant.setOtherPics(JSON.toJSONString(merchantVo.getOtherPics()));
                }
                merchant.setName(merchantVo.getName());
                merchant.setLicensePic(merchantVo.getLicensePic());
                merchant.setLegalPerson(merchantVo.getLegalPerson());
                merchant.setCreated(new Date());
                merchant.setAddress(merchantVo.getAddress());
                int i = merchantMapper.updateByPrimaryKeySelective(merchant);
                if (i < 1) {
                    return ResponseFactory.err("申请失败");
                }
                return ResponseFactory.sucMsg("重新申请成功");
            }else {
                return ResponseFactory.err("商家认证审核中!");
            }
        }
        merchant = new Merchant();
        merchant.setUserId(userId);
        merchant.setUpdated(new Date());
        merchant.setStatus((byte)2);
        merchant.setRegistrationNo(merchantVo.getRegistrationNo());
        merchant.setPhone(merchantVo.getPhone());
        if (merchantVo.getOtherPics() != null && merchantVo.getOtherPics().size() > 0) {
            merchant.setOtherPics(JSON.toJSONString(merchantVo.getOtherPics()));
        }
        merchant.setName(merchantVo.getName());
        merchant.setLicensePic(merchantVo.getLicensePic());
        merchant.setLegalPerson(merchantVo.getLegalPerson());
        merchant.setCreated(new Date());
        merchant.setAddress(merchantVo.getAddress());
        int count = merchantMapper.insert(merchant);
        if (count == 1) {
            return ResponseFactory.sucMsg("申请成功");
        }
        return ResponseFactory.err("申请失败");
    }

    /**
     * 获得该用户的商家认证状态
     *
     * @param: [userId 用户id]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/20
     */
    @Override
    public Response getMerchantStatus(Integer userId) {
        Map map = new HashMap();
        Merchant merchant = merchantMapper.selectByUserId(userId);
        if (merchant != null) {
            map.put("status", merchant.getStatus());
            return ResponseFactory.sucData(map);
        }
        map.put("status", 0);
        return ResponseFactory.sucData(map);
    }
}
