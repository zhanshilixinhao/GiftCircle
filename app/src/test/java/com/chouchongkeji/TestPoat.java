package com.chouchongkeji;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.dao.user.ThirdAccountMapper;
import com.chouchongkeji.dial.dao.v3.*;
import com.chouchongkeji.dial.dao.v4.*;
import com.chouchongkeji.dial.pojo.v3.ElectronicCoupons;
import com.chouchongkeji.dial.pojo.v3.MembershipCard;
import com.chouchongkeji.dial.pojo.v3.Store;
import com.chouchongkeji.dial.pojo.v3.UserMemberCard;
import com.chouchongkeji.dial.pojo.v4.RebateCoupon;
import com.chouchongkeji.goexplore.common.ResponseImpl;
import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.chouchongkeji.goexplore.utils.OkHttpManager;
import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import com.chouchongkeji.service.v3.vo.CardVo;
import com.chouchongkeji.service.v3.vo.ElCouponVo;
import com.chouchongkeji.service.v4.vo.JsonVo;
import com.chouchongkeji.service.v4.vo.StoreVoV4;
import com.chouchongkeji.service.v4.vo.SysAdminIdVo;
import com.chouchongkeji.util.CommonUtil;
import com.chouchongkeji.util.CountDownUtils;
import com.chouchongkeji.util.HttpUtil;
import com.chouchongkeji.wxpush.entity.Template;
import com.chouchongkeji.wxpush.entity.TemplateParam;
import com.yichen.auth.authentication.MyToken;
import com.yichen.auth.properties.SecurityProperties;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.chouchongkeji.dial.pojo.v4.RebateCoupon.*;
import static com.chouchongkeji.service.wxapi.WXCodeApi.WX_APP_SECRET;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPoat {

    @Resource
    private ShareCouponUserMapper shareCouponUserMapper;

    @Resource
    private ElUserCouponMapper elUserCouponMapper;

    @Resource
    private AppUserMapper appUserMapper;

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private ElUserCouponV4Mapper elUserCouponV4Mapper;

    @Resource
    private ThirdAccountMapper thirdAccountMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Resource
    private ElectronicCouponsMapper electronicCouponsMapper;

    @Resource
    private SysAdminVoMapper sysAdminVoMapper;

    @Resource
    private StoreSysAdminIdVoMapper storeSysAdminIdVoMapper;

    @Resource
    private UserMemberCardMapper userMemberCardMapper;

    @Resource
    private MembershipCardMapper membershipCardMapper;
    @Resource
    private RebateCouponMapper rebateCouponMapper;

    @Resource
    private HttpServletRequest request;


    @Test
    public void a2(){
        for (int i = 1; i < 10; i++) {
            System.out.println(i);
        }

    }


    @Test
    public void cc() throws ParseException {
        Example example = new Example(RebateCoupon.class);
        example.createCriteria().andEqualTo(USER_ID, 1533).andEqualTo(STATUS, (byte)1).
                andEqualTo(STORE_ID, 4);
        RebateCoupon rebateCoupon = rebateCouponMapper.selectOneByExample(example);
        System.out.println(rebateCoupon.getFirstTime().getTime());
        String countDown = CountDownUtils.getCountDown(rebateCoupon.getFirstTime(), new Date());
        System.out.println(countDown);
    }
    @Test
    public void bb(){
        Template template = new Template();
        TemplateParam templateParam = new TemplateParam();
        TemplateParam templateParam1 = new TemplateParam();
        templateParam.setKey("thing1").setValue("你狗");
        templateParam1.setKey("thing2").setValue("大憨包");
        ArrayList<TemplateParam> templateParams = new ArrayList<>();
        templateParams.add(templateParam);
        templateParams.add(templateParam1);
        template.setTemplateParamList(templateParams);
        template.setTouser("oCKK94sfPIHk7mEURCr-eqTUtjoE").setTemplate_id("Hf9p_7_TI2WxePFrEJ7zJai-nUcU3Rx6YEAI_5yVCTY")
        .setPage("pages/index/index");
        String s = template.toJSON();
        System.out.println(s);
        String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + "wx3e38146df77ddbd9" + "&secret=" + WX_APP_SECRET;
        String result = HttpUtil.sendGet(url);
        JSONObject object=JSON.parseObject(result);
        String Access_Token = object.getString("access_token");
        System.out.println(Access_Token);
        String requestUrl="https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";
        requestUrl=requestUrl.replace("ACCESS_TOKEN", Access_Token);
        com.gexin.fastjson.JSONObject post = CommonUtil.httpsRequest(requestUrl, "POST", template.toJSON());
        String s1 = post.toJSONString();
        System.out.println(s1);
    }
    @Test
    public void aa() throws IOException {

        RequestParams params = new RequestParams();
        params.put("openId", "oCKK94sfPIHk7mEURCr-eqTUtjoE");
        params.put("accType", 2);
        params.put("app_id", "giftcircler-dl");
        params.put("app_secret", "qMEjFl8w63EtAX17cRX83L0iMkK2U4mg");
        params.put("time", System.currentTimeMillis());
        String sing = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.WXMINI).get(ApiSignUtil.WXMINI);
        params.put("sign", sing);
        Response response = OkHttpUtil.post(OkHttpManager.create(null, null),
                "http://localhost:9001/login/third", params);
        String re = response.body().string();
        System.out.println(re);
        com.chouchongkeji.goexplore.common.Response res = JSON.parseObject(
                re,
                new TypeReference<ResponseImpl<MyToken>>() {
                });
        MyToken data = (MyToken) res.getData();
        System.out.println("输出"+data.getAccessToken());
        JsonVo jsonVo = JSON.parseObject(re, JsonVo.class);
        System.out.println(jsonVo);
        MyToken myToken = JSONObject.parseObject(jsonVo.getData(), MyToken.class);
        myToken.setRebate(new BigDecimal(33));
        myToken.setUserRebate(new BigDecimal(55));
        System.out.println(myToken.getAccessToken()+"//"+myToken.getRefreshToken()+"//"+myToken.getExpire()
        +"//"+myToken.getRebate()+"//"+myToken.getUserRebate());


    }
    @Test
    public void test() throws IOException {
        RequestParams params = new RequestParams();
        params.put("type", "shunfeng");
        params.put("postid", "078174215686");
        params.put("temp", System.currentTimeMillis());
        Response response = OkHttpUtil.post("http://www.kuaidi100.com/query", params);
        System.out.println(response.body().string());

    }

    @Test
    public void test1() throws IOException {
        Map<String, Object> map = JSON.parseObject("{\"app_id\":\"2018082361125281\",\"body\":\"礼遇圈\",\"buyer_id\":\"2088702252957362\",\"buyer_logon_id\":\"111***@qq.com\",\"buyer_pay_amount\":\"0.01\",\"charset\":\"utf-8\",\"fund_bill_list\":\"[{\\\"amount\\\":\\\"0.01\\\",\\\"fundChannel\\\":\\\"PCREDIT\\\"}]\",\"gmt_create\":\"2018-11-23 20:44:59\",\"gmt_payment\":\"2018-11-23 20:44:59\",\"invoice_amount\":\"0.01\",\"notify_id\":\"2018112300222204459057361025915904\",\"notify_time\":\"2018-11-23 20:44:59\",\"notify_type\":\"trade_status_sync\",\"out_trade_no\":\"1218112320142\",\"point_amount\":\"0.00\",\"receipt_amount\":\"0.01\",\"seller_email\":\"3436609882@qq.com\",\"seller_id\":\"2088231386393754\",\"sign\":\"e+HELc3UriB6XBs7jaLCpdlh3O5ao4K6xp2KwB+3bAKUUeSnHU9BtY1lVyV2lb//D9NLORzdQxIo62w8W5Up9Htgj2DLbI5Z2EsDgZobyT8Po8/j/mAZqoOsPY+KuiqaD8eDRkboo95OCTrU+w5PAma0sSP2PqOTxn7siAZbQIPPsyULH1/tbbI+GOXyfkL6Db+PtLnvKIlfvMcI7VrnP3g+K6wqzX0RcAQUEvNQ2SApYwBzgprXgzbYwBnmOy9CQuU7IYohx8lQF8hyiLNoUVhA7EsAHx16qU/5OP9iyPOVUosH6I7b9YAcbQEyd36nOnM8c/HCmA5+iASlSnSEpQ==\",\"sign_type\":\"RSA2\",\"subject\":\"-商品购买\",\"total_amount\":\"0.01\",\"trade_no\":\"2018112322001457361008067775\",\"trade_status\":\"TRADE_SUCCESS\",\"version\":\"1.0\"}",
                new TypeReference<HashMap<String, Object>>() {
                });
        System.out.println(map);
        RequestParams params = new RequestParams();
        map.forEach(params::put);
        Response response = OkHttpUtil.post("http://localhost:8089/noauth/pay/item_order/ali", params);
        System.out.println(response.body().string());
    }

//加密密码
    @Test
    public void pwd() {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }


    // 收藏商品列表
    @Test
    public void itemList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/favorite/itemList", params);
        System.out.println(post.body().string());
    }

    // 礼物偏好列表
    @Test
    public void preference() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/user/gift/preference/list", params);
        System.out.println(post.body().string());
    }
    // 修改礼物偏好
    @Test
    public void preferenceModify() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/user/gift/preference/modify", params);
        System.out.println(post.body().string());
    }
    // 修改礼物偏好
    @Test
    public void cartList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","824d7b11-4bdf-461e-b1d5-138859f14459");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/cart/list", params);
        System.out.println(post.body().string());
    }
}
