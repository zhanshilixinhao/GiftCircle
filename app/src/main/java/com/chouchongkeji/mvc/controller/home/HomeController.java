package com.chouchongkeji.mvc.controller.home;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.home.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author linqin
 * @date 2018/7/6
 */
@RestController
@RequestMapping("noauth/v1/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    /**
     * 首页Banner
     *
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    @PostMapping("Banner")
    public Response homeItem() {
        return homeService.getItemList();
    }

    /**
     * 首页寄售台新上架商品
     *
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    @PostMapping("con_item")
    public Response honeConItem() {
        return homeService.getConItem();
    }

    /**
     * 首页节日列表
     *
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    @PostMapping("festival")
    public Response getFestivalList() {
        return homeService.getFestivalList();
    }

    /**
     * 首页黄历
     *
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    @PostMapping("calendar")
    public Response getCalendar() throws ParseException {
        return homeService.getCalendar();
    }



    /**
     * 获取整点福利
     *
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    @PostMapping("welfare")
    public Response getWelfare() {
        return homeService.getWelfare();
    }

}
