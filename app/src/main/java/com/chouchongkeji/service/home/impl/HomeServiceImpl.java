package com.chouchongkeji.service.home.impl;

import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.dial.dao.backpack.consignment.ConsignmentMapper;
import com.chouchongkeji.dial.dao.home.BannerMapper;
import com.chouchongkeji.dial.dao.home.FestivalMapper;
import com.chouchongkeji.dial.dao.home.WelfareMapper;
import com.chouchongkeji.dial.pojo.home.Banner;
import com.chouchongkeji.dial.pojo.home.Festival;
import com.chouchongkeji.dial.pojo.home.Welfare;
import com.chouchongkeji.dial.redis.MRedisTemplate;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.utils.DateUtil;
import com.chouchongkeji.service.backpack.consignment.vo.ConsignmentVo;
import com.chouchongkeji.service.home.HomeService;
import com.chouchongkeji.service.home.almanac.AlmanacApi;
import com.chouchongkeji.service.home.almanac.HLResult;
import com.chouchongkeji.service.home.calendar.CalendarApi;
import com.chouchongkeji.service.home.calendar.RLResult;
import com.chouchongkeji.service.home.vo.CalendarVo;
import com.chouchongkeji.service.home.vo.WelfareVo;
import com.chouchongkeji.service.user.info.vo.DistrictVo;
import com.show.api.ShowApiRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private FestivalMapper festivalMapper;

    @Autowired
    private WelfareMapper welfareMapper;

    @Autowired
    private MRedisTemplate mRedisTemplate;

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
    public Response getCalendar() {
        Date date = new Date();
        List<CalendarVo> list = new ArrayList<>();
        for (int i = 0; i > -5; i--) {
            Date date1 = DateUtils.addDays(date, i);
//            SimpleDateFormat dateFormat = new  SimpleDateFormat("yyyyMM");
//            String oneMonth = dateFormat.format(date1);// 现在月份
            SimpleDateFormat format = new  SimpleDateFormat("yyyyMMdd");
            String oneDay = format.format(date1);// 现在日期
            CalendarVo calendarVo = new CalendarVo();
            HLResult almanacInfo = AlmanacApi.getAlmanacInfo(oneDay);
            if ( almanacInfo.getShowapi_res_code()!=0){
                return ResponseFactory.err(almanacInfo.getShowapi_res_error());
            }
            String gongli = almanacInfo.getShowapi_res_body().getGongli(); //公历
            String s2 = gongli.replaceAll("公元","");
            calendarVo.setGongli(s2);
            // 农历
            String nongli = almanacInfo.getShowapi_res_body().getNongli();
            nongli = nongli.substring(6);
            calendarVo.setNongli(nongli);
            calendarVo.setSuit(almanacInfo.getShowapi_res_body().getYi());
            calendarVo.setAvoid(almanacInfo.getShowapi_res_body().getJi());
            String s = almanacInfo.getShowapi_res_body().getJieri();//节日
            String[] s1 = s.replaceAll("公历节日:  ", "")
                    .replaceAll("农历节日:  ", "").split(" ");
            calendarVo.setJieri(StringUtils.join(s1,","));
            calendarVo.setJieqi24(almanacInfo.getShowapi_res_body().getJieqi24());
            list.add(calendarVo);
        }
        return ResponseFactory.sucData(list);
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
