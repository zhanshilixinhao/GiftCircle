package com.chouchongkeji.push;

import com.gexin.fastjson.JSON;
import com.gexin.rp.sdk.base.IAliasResult;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.gexin.rp.sdk.base.uitls.StringUtils;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linqin
 * @date 2018/12/27 14:09
 */

public class AppPush {

    //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "ePAZsicK6m98ugoygozFwA";
    private static String appKey = "EvHd5Dou1AAeIDRFFz5BU";
    private static String masterSecret = "DcGlsvIQgeAAG5I6IUQNpA";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";

//    public static void main(String[] args) {
//        // 链式
//        push(
//                PushMsg.msg()
//                        .title("测试")
//                        .text("textsdad")
//                        .user(1)
//                        .messageType(1)
//                        .messageId(1)
//        );
//    }

    public static void push(PushMsg msg) {
        // 配置返回每个用户返回用户状态，可选
        System.setProperty("gexin_pushList_needDetails", "true");
        // 配置返回每个别名及其对应cid的用户状态，可选
        // System.setProperty("gexin_pushList_needAliasDetails", "true");
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        // 通知透传模板
        NotificationTemplate template = notificationTemplateDemo(
                msg.title, msg.text, JSON.toJSONString(msg.transmissionContent));
        if (msg.users == null) {
            pushAll(push, msg, template);
        } else {
            pushToUsers(push, msg, template);
        }
    }

    static void pushToUsers(IGtPush push, PushMsg msg, NotificationTemplate template) {
        ListMessage message = new ListMessage();
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        // 配置推送目标
        List targets = new ArrayList();
        //
        for (Integer user : msg.users) {
            IAliasResult test = push.queryClientId(appId, String.valueOf(user));
            List<String> clientIdList = test.getClientIdList();
            if (CollectionUtils.isEmpty(clientIdList)){
                continue;
            }
            for (String clientId : clientIdList) {
                Target target = new Target();
                target.setAppId(appId);
                target.setClientId(clientId);
                targets.add(target);
            }
        }

        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
    }

    static void pushAll(IGtPush push, PushMsg msg, NotificationTemplate template) {
        AppMessage message = new AppMessage();
        message.setData(template);

        message.setOffline(true);
        //离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        //推送给App的目标用户需要满足的条件
        AppConditions cdt = new AppConditions();
        List<String> appIdList = new ArrayList<String>();
        appIdList.add(appId);
        message.setAppIdList(appIdList);
        //手机类型
        List<String> phoneTypeList = new ArrayList<String>();
        //省份
        List<String> provinceList = new ArrayList<String>();
        //自定义tag
        List<String> tagList = new ArrayList<String>();

        cdt.addCondition(AppConditions.PHONE_TYPE, phoneTypeList);
        cdt.addCondition(AppConditions.REGION, provinceList);
        cdt.addCondition(AppConditions.TAG, tagList);
        message.setConditions(cdt);
        IPushResult ret = push.pushMessageToApp(message);
    }


    public static NotificationTemplate notificationTemplateDemo(String title, String text, String content) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(text);
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent(content);
        return template;
    }


    public static void band(Integer userId,String clientid) throws Exception {
        IGtPush push = new IGtPush(url, appKey, masterSecret);

        IAliasResult bindSCid = push.bindAlias(appId, String.valueOf(userId),clientid );
//        System.out.println("绑定结果：" + bindSCid.getResult() +  "错误码:" + bindSCid.getErrorMsg());

    }
//"cb4133150e74ff5043358da0ef39dd28"

}
