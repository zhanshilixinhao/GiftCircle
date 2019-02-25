package com.chouchongkeji.service.home.calendar;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author linqin
 * @date 2019/2/25 14:57
 */

public class CalendarApi {

    private static final String URL = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php";

    public static RLResult getCalendarInfo(String date){
        RequestParams params = new RequestParams();
        params.put("query",date);
        params.put("resource_id","6018");
        params.put("format","json");
        params.put("qq-pf-to","pcqq.c2c");
        RLResult rl = new RLResult();
        try {
            Response response = OkHttpUtil.post(URL, params);
            if (response.isSuccessful()){
                String body = response.body().string();
                rl = JSON.parseObject(body,RLResult.class);
            }else {
                rl.setStatus(String.valueOf( response.code()));
               rl.setT(response.message());
            }
        }catch (IOException e) {
            e.printStackTrace();
            rl.setStatus("-1");
            rl.setT(e.getMessage());
        }
        return rl;
    }


    public static void main(String[] args) {
//        String res=new ShowApiRequest("http://route.showapi.com/856-1","87830","f3be077c6f134cf0a7bfa05dceef067f")
//                .addTextPara("date","20150423")
//                .post();
//        System.out.println(res);
        RLResult g = getCalendarInfo("201902");
        System.out.println(g);
    }


}
