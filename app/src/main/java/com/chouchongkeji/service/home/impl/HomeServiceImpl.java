package com.chouchongkeji.service.home.impl;

import com.chouchongkeji.dial.dao.backpack.consignment.ConsignmentMapper;
import com.chouchongkeji.dial.dao.home.BannerMapper;
import com.chouchongkeji.dial.pojo.home.Banner;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.backpack.consignment.vo.ConsignmentVo;
import com.chouchongkeji.service.home.HomeService;
import com.chouchongkeji.service.mall.article.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linqin
 * @date 2018/7/6
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private ConsignmentMapper consignmentMapper;

    @Autowired

    /**
     * 首页Banner
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    @Override
    public Response getItemList() {
        List<Banner> banner = bannerMapper.selectAll();
       if (banner == null){
           return ResponseFactory.err("");
       }
        return ResponseFactory.sucData(banner);
    }


    /**
     * 首页寄售台新上架商品
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    @Override
    public Response getConItem() {
        List<ConsignmentVo> conList = consignmentMapper.selectAll();
        if (conList == null){
            return ResponseFactory.err("");
        }
        return ResponseFactory.sucData(conList);
    }



}
