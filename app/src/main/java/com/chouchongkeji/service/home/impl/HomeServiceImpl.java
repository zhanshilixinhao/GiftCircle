package com.chouchongkeji.service.home.impl;

import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.dial.dao.backpack.consignment.ConsignmentMapper;
import com.chouchongkeji.dial.dao.home.AlmanacMapper;
import com.chouchongkeji.dial.dao.home.BannerMapper;
import com.chouchongkeji.dial.dao.home.FestivalMapper;
import com.chouchongkeji.dial.dao.home.WelfareMapper;
import com.chouchongkeji.dial.pojo.home.Almanac;
import com.chouchongkeji.dial.pojo.home.Banner;
import com.chouchongkeji.dial.pojo.home.Festival;
import com.chouchongkeji.dial.redis.CacheCallback;
import com.chouchongkeji.dial.redis.MRedisTemplate;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.backpack.consignment.vo.ConsignmentVo;
import com.chouchongkeji.service.home.HomeService;
import com.chouchongkeji.service.home.almanac.AlmanacApi;
import com.chouchongkeji.service.home.almanac.HLResult;
import com.chouchongkeji.service.home.vo.CalendarVo;
import com.chouchongkeji.service.home.vo.WelfareVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author linqin
 * @date 2018/7/6
 */
@Service
@Transactional(rollbackFor = Exception.class,isolation = Isolation.REPEATABLE_READ)
public class HomeServiceImpl implements HomeService {

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private ConsignmentMapper consignmentMapper;

    @Autowired
    private FestivalMapper festivalMapper;

    @Autowired
    private WelfareMapper welfareMapper;

    @Autowired
    private MRedisTemplate mRedisTemplate;

    @Autowired
    private AlmanacMapper almanacMapper;

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

    /**
     * 首页节日列表
     *
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    @Override
    public Response getFestivalList() {
        // 查询所有节日
        List<Festival> festivals = festivalMapper.selectByAll();
        return ResponseFactory.sucData(festivals);
    }




    /**
     * 首页黄历
     *
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    @Override
    public Response getCalendar(){
        Date date = new Date();
        List<CalendarVo> list = new ArrayList<>();
        for (int i = 0; i > -5; i--) {
            Date date1 = DateUtils.addDays(date, i);
            SimpleDateFormat format = new  SimpleDateFormat("yyyyMMdd");
            String oneDay = format.format(date1);// 现在日期
            CalendarVo calendarVo1 = mRedisTemplate.get(oneDay, 30, TimeUnit.DAYS, new TypeReference<CalendarVo>() {
            }, new CacheCallback<CalendarVo>() {
                @Override
                public CalendarVo load() {
                    return getAlmanacDay(oneDay);
                }
            });
            calendarVo1.setDate(date1.getTime());
            list.add(calendarVo1);
        }
        return ResponseFactory.sucData(list);
    }




    /**
     * 老黄历
     * @param oneDay
     * @return
     */
    public CalendarVo getAlmanacDay(String oneDay){
        CalendarVo vo = new CalendarVo();
        Almanac almanac = almanacMapper.selectByPrimaryKey(oneDay);
        if (almanac == null){
            return getAlmanacApi(oneDay);
        }
        vo.setGongli(almanac.getGongli());
        vo.setNongli(almanac.getNongli());
        vo.setAvoid(almanac.getAvoid());
        vo.setSuit(almanac.getSuit());
        vo.setJieri(almanac.getJieri());
        vo.setJieqi24(almanac.getJieqi24());
        return vo;
    }

//    public static void main(String[] args) {
//        Locale localeCN = Locale.SIMPLIFIED_CHINESE;
//        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  E",localeCN);
//        SimpleDateFormat mat = new SimpleDateFormat("yyyy年MM月dd日 E");
//        Date parse = null;//反格式化
//        try {
//            parse = mat.parse("2019年3月21日 星期四");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println(parse);
//    }


    /**
     * 第三方老黄历接口
     * @param oneDay
     * @return
     */
    private CalendarVo getAlmanacApi(String oneDay) {
        CalendarVo vo = new CalendarVo();
        HLResult almanacInfo = AlmanacApi.getAlmanacInfo(oneDay);
        if ( almanacInfo.getShowapi_res_code()!=0){
            throw new ServiceException(ErrorCode.ERROR.getCode(),almanacInfo.getShowapi_res_error());
        }
        //公历
        String gongli = almanacInfo.getShowapi_res_body().getGongli();
        String s2 = gongli.replaceAll("公元","").trim();
        Locale localeCN = Locale.SIMPLIFIED_CHINESE;
        SimpleDateFormat mat = new SimpleDateFormat("yyyy年MM月dd日 E",localeCN);
        Date parse = null;//反格式化
        try {
            parse = mat.parse(s2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mat = new SimpleDateFormat("yyyy.MM E",localeCN);
        s2 = mat.format(parse);
        vo.setGongli(s2);
        // 农历
        String nongli = almanacInfo.getShowapi_res_body().getNongli();
        nongli = nongli.substring(6);
        vo.setNongli(nongli);
        // 宜忌
        vo.setSuit(almanacInfo.getShowapi_res_body().getYi());
        vo.setAvoid(almanacInfo.getShowapi_res_body().getJi());
        //节日
        String s = almanacInfo.getShowapi_res_body().getJieri();
        String[] s1 = s.replaceAll("公历节日:  ", "")
                .replaceAll("农历节日:  ", "").split(" ");
        vo.setJieri(StringUtils.join(s1,","));
        // 24节气
        vo.setJieqi24(almanacInfo.getShowapi_res_body().getJieqi24());
        // 加入数据库
        Almanac al = new Almanac();
        al.setDay(oneDay);
        al.setGongli(vo.getGongli());
        al.setNongli(vo.getNongli());
        al.setJieqi24(vo.getJieqi24());
        al.setAvoid(vo.getAvoid());
        al.setSuit(vo.getSuit());
        al.setJieri(vo.getJieri());
        almanacMapper.insert(al);
        return vo;
    }


    /**
     * 获取整点福利
     *
     * @return
     * @author linqin
     * @date 2018/7/6
     */
    @Override
    public Response getWelfare(){
        WelfareVo welfare = welfareMapper.selectByTime();
        return ResponseFactory.sucData(welfare);
    }

}
