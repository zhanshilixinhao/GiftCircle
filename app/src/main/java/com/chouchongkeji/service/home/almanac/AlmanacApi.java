package com.chouchongkeji.service.home.almanac;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import com.show.api.ShowApiRequest;
import okhttp3.Response;

import javax.sound.midi.Soundbank;
import java.io.IOException;

/**
 * @author linqin
 * @date 2019/3/11 11:10
 */

public class AlmanacApi {

    private static final String URL = "http://route.showapi.com/856-1";
    private static final String appId = "89410";
    private static final String appSecret = "4e0a66c488474fd9b779b610b4830082";

    public static HLResult getAlmanacInfo(String data) {
//        RequestParams params = new RequestParams();
//        params.put("showapi_appid", "87830");
//        params.put("showapi_sign", "f3be077c6f134cf0a7bfa05dceef067f");
//        params.put("showapi_timestamp", data);
        String res = new ShowApiRequest(URL,appId, appSecret)
                .addTextPara("date", data)
                .post();
        HLResult hlResult = JSON.parseObject(res, HLResult.class);

        return hlResult;
    }

    public static void main(String[] args) {
//        String res = new ShowApiRequest("http://route.showapi.com/856-1", "87830", "f3be077c6f134cf0a7bfa05dceef067f")
//                .addTextPara("date", "20190311")
//                .post();
        HLResult almanacInfo = getAlmanacInfo("20190214");
        System.out.println(almanacInfo.toString());
    }

}
