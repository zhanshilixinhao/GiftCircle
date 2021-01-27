package com.chouchongkeji.wxpush;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chouchongkeji.util.CommonUtil;
import com.chouchongkeji.util.HttpUtil;
import com.chouchongkeji.wxpush.entity.Template;
import com.chouchongkeji.wxpush.entity.TemplateParam;

import java.util.ArrayList;

import static com.chouchongkeji.service.wxapi.WXCodeApi.WX_APPID;
import static com.chouchongkeji.service.wxapi.WXCodeApi.WX_APP_SECRET;

/**
 * @Description: 微信推送
 * @Author Lxh
 * @Date 2020/10/29 14:04
 */
public class WxPush {
    public void Push(String openId, String templateId, ArrayList<TemplateParam> templateParams,String page){
        Template template = new Template();
        if (page == null) {
            template.setPage("pages/index/index");
        }
        template.setTemplate_id(templateId).setTouser(openId).setTemplateParamList(templateParams);
        String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + WX_APPID + "&secret=" + WX_APP_SECRET;
        String result = HttpUtil.sendGet(url);
        JSONObject object= JSON.parseObject(result);
        String Access_Token = object.getString("access_token");
        String requestUrl="https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";
        requestUrl=requestUrl.replace("ACCESS_TOKEN", Access_Token);
        CommonUtil.httpsRequest(requestUrl, "POST", template.toJSON());
    }
}
