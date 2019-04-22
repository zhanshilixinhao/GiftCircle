package com.chouchongkeji;

import com.alibaba.fastjson.JSON;
import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * @author linqin
 * @date 2018/12/6 11:49
 */

public class Test2 {

    @Test
    public void test() {
        List<Integer> hong = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            int n = random.nextInt();
            if (n <= 33 && n > 0 && !hong.contains(n)) {
                hong.add(n);
            } else i --;
        }
        int lan = 0;
        do {
            lan = random.nextInt();
        } while (lan < 1 || lan > 16);

        Collections.sort(hong);
        System.out.println(JSON.toJSONString(hong));
        System.out.println(lan);
    }

    @Test
    public void aaa() {
        String json = "[\n" +
                "  {\n" +
                "    path: \"/permission\", redirect: \"/permission/roleList\", name: \"permission\", menu: \"permission\", meta: { title: \"权限管理\", icon: \"example\" },\n" +
                "    children:\n" +
                "      [\n" +
                "        { path: \"roleList\", name: \"角色管理\",aa:\"@/views/permission/roleList\", menu: \"role_list\", meta: { title: \"角色管理\", icon: \"example\" }},\n" +
                "        { path: \"roleAdd\", name: \"添加角色\", hidden: true,aa:\"@/views/permission/roleAdd\", menu: \"role_add\", meta: { title: \"添加角色\", icon: \"example\" }},\n" +
                "        { path: \"roleEdit/:id\", name: \"修改角色\", hidden: true,aa:\"@/views/permission/roleEdit\", menu: \"role_edit\", meta: { title: \"修改角色\", icon: \"example\" }},\n" +
                "        { path: \"list\", name: \"后台用户管理\",aa:\"@/views/webUser/list\", menu: \"web_user_list\", meta: { title: \"后台用户管理\", icon: \"example\" }},\n" +
                "        { path: \"add\", name: \"后台用户添加\", hidden: true,aa:\"@/views/webUser/add\", menu: \"web_user_add\", meta: { title: \"后台用户添加\", icon: \"example\" }},\n" +
                "        { path: \"edit/:id\", name: \"后台用户修改\", hidden: true,aa:\"@/views/webUser/edit\", menu: \"web_user_edit\", meta: { title: \"后台用户修改\", icon: \"example\" }}\n" +
                "      ]\n" +
                "  },\n" +
                "  {\n" +
                "    path: \"/questionnaire\", redirect: \"/questionnaire\", name: \"questionnaire\", menu: \"questionnaire\", meta: { title: \"问卷调查\", icon: \"example\" },\n" +
                "    children:\n" +
                "      [\n" +
                "        { path: \"topicList\", name: \"问卷调查管理\",aa:\"@/views/questionnaire/list\", menu: \"questionnaire_list\", meta: { title: \"问卷调查管理\", icon: \"example\" }},\n" +
                "        { path: \"topicAdd\", name: \"添加题目\", hidden: true,aa:\"@/views/questionnaire/add\", menu: \"questionnaire_add\", meta: { title: \"添加问卷调查题目\", icon: \"example\" }}\n" +
                "      ]\n" +
                "  },\n" +
                "  {\n" +
                "    path: \"/order\", redirect: \"/order\", name: \"order\", menu: \"order\", meta: { title: \"订单管理\", icon: \"example\" },\n" +
                "    children:\n" +
                "      [\n" +
                "        { path: \"orderList\", name: \"订单管理\",aa:\"@/views/order/list\", menu: \"order_list\", meta: { title: \"订单管理\", icon: \"example\" }},\n" +
                "        { path: \"otherOrderList\", name: \"其它订单管理\",aa:\"@/views/order/otherList\", menu: \"other_order_list\", meta: { title: \"其它订单管理\", icon: \"example\" }}\n" +
                "      ]\n" +
                "  },\n" +
                "  {\n" +
                "    path: \"/demand\", redirect: \"/demand\", name: \"demand\", menu: \"demand\", meta: { title: \"需求管理\", icon: \"example\" },\n" +
                "    children:\n" +
                "      [\n" +
                "        { path: \"demandList\", name: \"需求管理\",aa:\"@/views/demand/list\", menu: \"demand_list\", meta: { title: \"需求管理\", icon: \"example\" }},\n" +
                "        { path: \"demandAdd\", name: \"添加需求\", hidden: true,aa:\"@/views/demand/add\", menu: \"demand_add\", meta: { title: \"添加需求\", icon: \"example\" }}\n" +
                "      ]\n" +
                "  },\n" +
                "  {\n" +
                "    path: \"/article\", redirect: \"/article\", name: \"article\", menu: \"article\", meta: { title: \"文章管理\", icon: \"example\" },\n" +
                "    children:\n" +
                "      [\n" +
                "        { path: \"articleList\", name: \"文章管理\",aa:\"@/views/article/list\", menu: \"article_list\", meta: { title: \"文章管理\", icon: \"example\" }}\n" +
                "      ]\n" +
                "  },\n" +
                "  {\n" +
                "    path: \"/user\", redirect: \"/user\", name: \"user\", menu: \"user\", meta: { title: \"用户管理\", icon: \"example\" },\n" +
                "    children:\n" +
                "      [\n" +
                "        { path: \"userList\", name: \"用户管理\",aa:\"@/views/user/list\", menu: \"user_list\", meta: { title: \"用户管理\", icon: \"example\" }},\n" +
                "        { path: \"codeList/:id\", name: \"推荐码列表\", hidden: true,aa:\"@/views/user/codeList\", menu: \"code_list\", meta: { title: \"推荐码列表\", icon: \"example\" }}\n" +
                "      ]\n" +
                "  },\n" +
                "  {\n" +
                "    path: \"/withdraw\", redirect: \"/withdraw\", name: \"withdraw\", menu: \"withdraw\", meta: { title: \"提现管理\", icon: \"example\" },\n" +
                "    children:\n" +
                "      [\n" +
                "        { path: \"withdraw\", name: \"提现管理\",aa:\"@/views/withdraw/list\", menu: \"withdraw_list\", meta: { title: \"提现管理\", icon: \"example\" }}\n" +
                "      ]\n" +
                "  },\n" +
                "  {\n" +
                "    path: \"/banner\", redirect: \"/banner\", name: \"banner\", menu: \"banner\", meta: { title: \"首页横幅管理\", icon: \"example\" },\n" +
                "    children:\n" +
                "      [\n" +
                "        { path: \"bannerList\", name: \"首页横幅列表\",aa:\"@/views/banner/list\", menu: \"banner_list\", meta: { title: \"首页横幅管理\", icon: \"example\" }},\n" +
                "        { path: \"bannerAdd\", name: \"添加首页横幅\", hidden: true,aa:\"@/views/banner/add\", menu: \"banner_add\", meta: { title: \"添加首页横幅\", icon: \"example\" }},\n" +
                "        { path: \"bannerEdit/:id\", name: \"修改首页横幅\", hidden: true,aa:\"@/views/banner/edit\", menu: \"banner_edit\", meta: { title: \"修改首页横幅\", icon: \"example\" }}\n" +
                "      ]\n" +
                "  },\n" +
                "  {\n" +
                "    path: \"/statistics\", redirect: \"/statistics\", name: \"statistics\", menu: \"statistics\", meta: { title: \"数据统计\", icon: \"example\" },\n" +
                "    children:\n" +
                "      [\n" +
                "        { path: \"orderStatistics\", name: \"订单统计\",aa:\"@/views/statistics/orderStatistics\", menu: \"order_statistics\", meta: { title: \"订单统计\", icon: \"example\" }},\n" +
                "        { path: \"userStatistics\", name: \"用户统计\",aa:\"@/views/statistics/userStatistics\", menu: \"user_statistics\", meta: { title: \"用户统计\", icon: \"example\" }}\n" +
                "      ]\n" +
                "  }\n" +
                "]";

        Object parse = JSON.parse(json);
        System.out.println(parse);
    }

    //同意拒绝好友索要
    @Test
    public void operation() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","6e4fa086-2bbe-4127-b4da-1ab86c9e07a0");
        params.put("forRecordId",19);
        params.put("operation",1);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/friend/bp/operation", params);
        System.out.println(post.body().string());
    }

    // 向好友索要物品
  @Test
    public void forRecord() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","f3e7f648-845e-4614-a49e-bbd8f43add65");
        params.put("friendUserId",4);
        params.put("bpId","4818120614107");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/friend/bp/add_for_record", params);
        System.out.println(post.body().string());
    }
    // 用户信息
    @Test
    public void profile() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/user/profile", params);
        System.out.println(post.body().string());
    }

    //银行卡列表
    @Test
    public void bankList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/bankCard/list", params);
        System.out.println(post.body().string());
    }

    //banner
    @Test
    public void banner() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","ca0aa669-f006-4ba8-bdf9-91a078b3ccf1");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/v1/home/Banner", params);
        System.out.println(post.body().string());
    }
    //搜索好友
    @Test
    public void search() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        params.put("key","年");
//        params.put("type",1);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/friend/search", params);
        System.out.println(post.body().string());
    }


}
