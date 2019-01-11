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
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    @PostMapping("Banner")
    public Response homeItem(){
        return homeService.getItemList();
    }

    /**
     * 首页寄售台新上架商品
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    @PostMapping("con_item")
    public Response honeConItem(){
        return homeService.getConItem();
    }

    /**
     * 按天获取文章列表
     * @param day
     * @return
     */
    @PostMapping("article_list")
    public Response getArticleByDay(Long day) throws ParseException {
        // 如果时间为空，默认为当天
        if (day == null){
            time(System.currentTimeMillis());
        }else {
            time(day);
        }
        return null;
    }

    /**
     * 时间戳
     */
    public void time(Long day) throws ParseException {
        Date now = new Date(day);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = dateFormat.format(now);//日期
        Date parse = dateFormat.parse(format);  //时间戳
        day = parse.getTime() / 1000;
    }

}
