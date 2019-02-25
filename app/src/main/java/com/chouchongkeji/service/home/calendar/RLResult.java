package com.chouchongkeji.service.home.calendar;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 黄历宜忌
 * @author linqin
 * @date 2019/2/25 14:49
 */

//    https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=2019%E5%B9%B42%E6%9C%88&resource_id=6018&format=json&qq-pf-to=pcqq.c2c网址

public class RLResult {
    /**
     * status : 0
     * t :
     * set_cache_time :
     * data : [{"StdStg":6018,"StdStl":8,"_update_time":"1544762107","cambrian_appid":"0","almanac":[{"avoid":"出行.治病.安葬.开市.","date":"2019-2-1","suit":"会亲友.纳采.进人口.修造.动土.竖柱.上梁.祭祀.开光.塑绘.祈福.斋醮.嫁娶.安床.移徙.入宅.安香.纳畜."},{"avoid":"造屋.开市.作灶.入宅.","date":"2019-2-2","suit":"祭祀.会亲友.出行.订盟.纳采.沐浴.修造.动土.祈福.斋醮.嫁娶.拆卸.安床.入殓.移柩.安葬.谢土.赴任.裁衣.竖柱.上梁.伐木.捕捉.栽种.破土.安门."},{"avoid":"诸事不宜.","date":"2019-2-3","suit":"解除.破屋.坏垣.余事勿取."},{"avoid":"嫁娶.安葬.","date":"2019-2-4","suit":"祭祀.沐浴.解除.理发.扫舍.破屋.坏垣.余事勿取."},{"avoid":"安床.作灶.造船.会亲友.","date":"2019-2-5","suit":"纳采.订盟.祭祀.祈福.安香.出火.修造.出行.开市.移徙.入宅.动土.安葬.破土."},{"avoid":"嫁娶.安门.移徙.入宅.安葬.","date":"2019-2-6","suit":"塞穴.结网.取渔.畋猎."},{"avoid":"嫁娶.开市.安葬.破土.","date":"2019-2-7","suit":"纳采.祭祀.祈福.出行.会亲友.修造.动土.移徙.入宅."},{"avoid":"移徙.入宅.出火.安门.安葬.","date":"2019-2-8","suit":"纳采.嫁娶.祭祀.祈福.出行.开市.会亲友.动土.破土.启攒."},{"avoid":"嫁娶.动土.开光.造屋.破土.","date":"2019-2-9","suit":"祭祀.祈福.求嗣.斋醮.入殓.除服.成服.移柩.安葬.启攒."},{"avoid":"祭祀.移徙.入宅.动土.破土.","date":"2019-2-10","suit":"纳采.会亲友.竖柱.上梁.立券.入殓.移柩.安葬.启攒."},{"avoid":"开光.嫁娶.作灶.掘井.纳畜.","date":"2019-2-11","suit":"祭祀.祈福.斋醮.出行.开市.立券.动土.移徙.入宅.破土.安葬."},{"avoid":"开市.动土.安葬.破土.","date":"2019-2-12","suit":"会亲友.求嗣.理发.冠笄.结网.捕捉.开光.理发."},{"avoid":"嫁娶.祈福.掘井.安葬.","date":"2019-2-13","suit":"祭祀.平治道涂.余事勿取."},{"avoid":"置产.造屋.合脊.开光.探病.安门.作灶.","date":"2019-2-14","suit":"祈福.求嗣.斋醮.纳采.嫁娶.伐木.修造.动土.移徙.入宅.造庙.安机械.开市.入殓.除服.成服.移柩.安葬.破土.谢土."},{"avoid":"开光.开市.入宅.动土.造屋.","date":"2019-2-15","suit":"入学.习艺.出行.纳采.订盟.嫁娶.会亲友.进人口.牧养.捕捉.入殓.移柩.安葬.启攒."},{"avoid":"入宅.开市.安葬.","date":"2019-2-16","suit":"祭祀.沐浴.求医.治病.扫舍.破屋.坏垣.解除.余事勿取."},{"avoid":"安床.栽种.治病.作灶.","date":"2019-2-17","suit":"祭祀.冠笄.嫁娶.拆卸.修造.动土.起基.上梁.造屋.入宅.开市.开池.塞穴.入殓.除服.成服.移柩.安葬.破土."},{"avoid":"余事勿取.","date":"2019-2-18","suit":"祭祀.结网.入殓.除服.成服.移柩.安葬.破土."},{"avoid":"伐木.作灶.安葬.取渔.入宅.","date":"2019-2-19","suit":"塑绘.开光.祈福.求嗣.订盟.纳采.裁衣.冠笄.拆卸.修造.动土.起基.安门.安床.移徙.造仓.结网.纳畜."},{"avoid":"安葬.上梁.入宅.作灶.","date":"2019-2-20","suit":"祭祀.沐浴.开光.塑绘.祈福.求嗣.订盟.纳采.冠笄.裁衣.嫁娶.动土.除服.成服.移柩.破土.启攒.出行.安碓硙.放水.开市.立券.交易."},{"avoid":"出行.嫁娶.入宅.动土.","date":"2019-2-21","suit":"祭祀.祈福.求嗣.酬神.裁衣.安床.立券.交易.入殓.除服.成服.移柩.谢土.启攒."},{"avoid":"祭祀.祈福.移徙.嫁娶.入宅.","date":"2019-2-22","suit":"裁衣.合帐.入殓.除服.成服.会亲友.纳财."},{"avoid":"掘井.","date":"2019-2-23","suit":"祭祀.斋醮.裁衣.合帐.冠笄.订盟.纳采.嫁娶.入宅.安香.谢土.入殓.移柩.破土.立碑.安香.会亲友.出行.祈福.求嗣.立碑.上梁.放水."},{"avoid":"斋醮.作灶.安床.安葬.","date":"2019-2-24","suit":"安床.合帐.入宅.问名.纳采.求嗣.祭祀.开仓."},{"avoid":"祭祀.祈福.安葬.安门.余事勿取.","date":"2019-2-25","suit":"作灶.平治道涂."},{"avoid":"造屋.栽种.安葬.作灶.","date":"2019-2-26","suit":"塑绘.开光.酬神.斋醮.订盟.纳采.裁衣.合帐.拆卸.动土.上梁.安床.安香.造庙.挂匾.会亲友.进人口.出行.修造.纳财.伐木.放水.出火.纳畜.沐浴.安门."},{"avoid":"栽种.动土.开市.作灶.","date":"2019-2-27","suit":"祭祀.祈福.酬神.订盟.纳采.冠笄.裁衣.合帐.嫁娶.安床.移徙.入宅.安香.入殓.移柩.启攒.安葬.解除.取渔.捕捉.伐木.安门.出火."},{"avoid":"诸事不宜.","date":"2019-2-28","suit":"求医.破屋."}],"holiday":[{"desc":"2018年12月30日至2019年1月1日放假调休，共3天。2018年12月29日（星期六）上班。","festival":"2019-1-1","list":[{"date":"2018-12-29","status":"2"},{"date":"2018-12-31","status":"1"},{"date":"2019-1-1","status":"1"},{"date":"2018-12-30","status":"1"}],"list#num#baidu":4,"name":"元旦","rest":"拼假建议：2019年1月2日（周三）~2019年1月4日（周五）请假3天，可拼8天元旦小长假"},{"desc":"除夕","festival":"2019-2-4","list":[{"date":"2019-2-4","status":"1"}],"list#num#baidu":1,"name":"除夕","rest":"农历腊月最后一天为除夕，即大年初一前夜，又称为年三十。"},{"desc":"2月4日至10日放假调休，共7天。2月2日（星期六）、2月3日（星期日）上班","festival":"2019-2-5","list":[{"date":"2019-2-4","status":"1"},{"date":"2019-2-2","status":"2"},{"date":"2019-2-3","status":"2"},{"date":"2019-2-5","status":"1"},{"date":"2019-2-6","status":"1"},{"date":"2019-2-7","status":"1"},{"date":"2019-2-8","status":"1"},{"date":"2019-2-9","status":"1"},{"date":"2019-2-10","status":"1"}],"list#num#baidu":9,"name":"春节","rest":"拼假建议：2019年2月11日（周一）-2019年2月15日（周五）请假5天，可拼14天春节小长假。"}],"holidaylist":[{"name":"元旦","startday":"2019-1-1"},{"name":"除夕","startday":"2019-2-4"},{"name":"春节","startday":"2019-2-4"},{"name":"清明节","startday":"2019-4-5"},{"name":"劳动节","startday":"2019-5-1"},{"name":"端午节","startday":"2019-6-7"},{"name":"中秋节","startday":"2019-9-13"},{"name":"国庆节","startday":"2019-10-1"}],"key":"2019年2月","selectday":"2019-2-1","url":"http://nourl.baidu.com/6018","loc":"https://ss1.baidu.com/8aQDcnSm2Q5IlBGlnYG/q?r=2002753&k=2019%E5%B9%B42%E6%9C%88","SiteId":2002753,"_version":2387,"_select_time":1544761606,"clicklimit":"1-3","ExtendedLocation":"","OriginQuery":"2019年2月","tplt":"calendar_new","resourceid":"6018","fetchkey":"6018_2019年2月","appinfo":"","role_id":10,"disp_type":0}]
     */

    private String status;
    private String t;
    private String set_cache_time;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getSet_cache_time() {
        return set_cache_time;
    }

    public void setSet_cache_time(String set_cache_time) {
        this.set_cache_time = set_cache_time;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * StdStg : 6018
         * StdStl : 8
         * _update_time : 1544762107
         * cambrian_appid : 0
         * almanac : [{"avoid":"出行.治病.安葬.开市.","date":"2019-2-1","suit":"会亲友.纳采.进人口.修造.动土.竖柱.上梁.祭祀.开光.塑绘.祈福.斋醮.嫁娶.安床.移徙.入宅.安香.纳畜."},{"avoid":"造屋.开市.作灶.入宅.","date":"2019-2-2","suit":"祭祀.会亲友.出行.订盟.纳采.沐浴.修造.动土.祈福.斋醮.嫁娶.拆卸.安床.入殓.移柩.安葬.谢土.赴任.裁衣.竖柱.上梁.伐木.捕捉.栽种.破土.安门."},{"avoid":"诸事不宜.","date":"2019-2-3","suit":"解除.破屋.坏垣.余事勿取."},{"avoid":"嫁娶.安葬.","date":"2019-2-4","suit":"祭祀.沐浴.解除.理发.扫舍.破屋.坏垣.余事勿取."},{"avoid":"安床.作灶.造船.会亲友.","date":"2019-2-5","suit":"纳采.订盟.祭祀.祈福.安香.出火.修造.出行.开市.移徙.入宅.动土.安葬.破土."},{"avoid":"嫁娶.安门.移徙.入宅.安葬.","date":"2019-2-6","suit":"塞穴.结网.取渔.畋猎."},{"avoid":"嫁娶.开市.安葬.破土.","date":"2019-2-7","suit":"纳采.祭祀.祈福.出行.会亲友.修造.动土.移徙.入宅."},{"avoid":"移徙.入宅.出火.安门.安葬.","date":"2019-2-8","suit":"纳采.嫁娶.祭祀.祈福.出行.开市.会亲友.动土.破土.启攒."},{"avoid":"嫁娶.动土.开光.造屋.破土.","date":"2019-2-9","suit":"祭祀.祈福.求嗣.斋醮.入殓.除服.成服.移柩.安葬.启攒."},{"avoid":"祭祀.移徙.入宅.动土.破土.","date":"2019-2-10","suit":"纳采.会亲友.竖柱.上梁.立券.入殓.移柩.安葬.启攒."},{"avoid":"开光.嫁娶.作灶.掘井.纳畜.","date":"2019-2-11","suit":"祭祀.祈福.斋醮.出行.开市.立券.动土.移徙.入宅.破土.安葬."},{"avoid":"开市.动土.安葬.破土.","date":"2019-2-12","suit":"会亲友.求嗣.理发.冠笄.结网.捕捉.开光.理发."},{"avoid":"嫁娶.祈福.掘井.安葬.","date":"2019-2-13","suit":"祭祀.平治道涂.余事勿取."},{"avoid":"置产.造屋.合脊.开光.探病.安门.作灶.","date":"2019-2-14","suit":"祈福.求嗣.斋醮.纳采.嫁娶.伐木.修造.动土.移徙.入宅.造庙.安机械.开市.入殓.除服.成服.移柩.安葬.破土.谢土."},{"avoid":"开光.开市.入宅.动土.造屋.","date":"2019-2-15","suit":"入学.习艺.出行.纳采.订盟.嫁娶.会亲友.进人口.牧养.捕捉.入殓.移柩.安葬.启攒."},{"avoid":"入宅.开市.安葬.","date":"2019-2-16","suit":"祭祀.沐浴.求医.治病.扫舍.破屋.坏垣.解除.余事勿取."},{"avoid":"安床.栽种.治病.作灶.","date":"2019-2-17","suit":"祭祀.冠笄.嫁娶.拆卸.修造.动土.起基.上梁.造屋.入宅.开市.开池.塞穴.入殓.除服.成服.移柩.安葬.破土."},{"avoid":"余事勿取.","date":"2019-2-18","suit":"祭祀.结网.入殓.除服.成服.移柩.安葬.破土."},{"avoid":"伐木.作灶.安葬.取渔.入宅.","date":"2019-2-19","suit":"塑绘.开光.祈福.求嗣.订盟.纳采.裁衣.冠笄.拆卸.修造.动土.起基.安门.安床.移徙.造仓.结网.纳畜."},{"avoid":"安葬.上梁.入宅.作灶.","date":"2019-2-20","suit":"祭祀.沐浴.开光.塑绘.祈福.求嗣.订盟.纳采.冠笄.裁衣.嫁娶.动土.除服.成服.移柩.破土.启攒.出行.安碓硙.放水.开市.立券.交易."},{"avoid":"出行.嫁娶.入宅.动土.","date":"2019-2-21","suit":"祭祀.祈福.求嗣.酬神.裁衣.安床.立券.交易.入殓.除服.成服.移柩.谢土.启攒."},{"avoid":"祭祀.祈福.移徙.嫁娶.入宅.","date":"2019-2-22","suit":"裁衣.合帐.入殓.除服.成服.会亲友.纳财."},{"avoid":"掘井.","date":"2019-2-23","suit":"祭祀.斋醮.裁衣.合帐.冠笄.订盟.纳采.嫁娶.入宅.安香.谢土.入殓.移柩.破土.立碑.安香.会亲友.出行.祈福.求嗣.立碑.上梁.放水."},{"avoid":"斋醮.作灶.安床.安葬.","date":"2019-2-24","suit":"安床.合帐.入宅.问名.纳采.求嗣.祭祀.开仓."},{"avoid":"祭祀.祈福.安葬.安门.余事勿取.","date":"2019-2-25","suit":"作灶.平治道涂."},{"avoid":"造屋.栽种.安葬.作灶.","date":"2019-2-26","suit":"塑绘.开光.酬神.斋醮.订盟.纳采.裁衣.合帐.拆卸.动土.上梁.安床.安香.造庙.挂匾.会亲友.进人口.出行.修造.纳财.伐木.放水.出火.纳畜.沐浴.安门."},{"avoid":"栽种.动土.开市.作灶.","date":"2019-2-27","suit":"祭祀.祈福.酬神.订盟.纳采.冠笄.裁衣.合帐.嫁娶.安床.移徙.入宅.安香.入殓.移柩.启攒.安葬.解除.取渔.捕捉.伐木.安门.出火."},{"avoid":"诸事不宜.","date":"2019-2-28","suit":"求医.破屋."}]
         * holiday : [{"desc":"2018年12月30日至2019年1月1日放假调休，共3天。2018年12月29日（星期六）上班。","festival":"2019-1-1","list":[{"date":"2018-12-29","status":"2"},{"date":"2018-12-31","status":"1"},{"date":"2019-1-1","status":"1"},{"date":"2018-12-30","status":"1"}],"list#num#baidu":4,"name":"元旦","rest":"拼假建议：2019年1月2日（周三）~2019年1月4日（周五）请假3天，可拼8天元旦小长假"},{"desc":"除夕","festival":"2019-2-4","list":[{"date":"2019-2-4","status":"1"}],"list#num#baidu":1,"name":"除夕","rest":"农历腊月最后一天为除夕，即大年初一前夜，又称为年三十。"},{"desc":"2月4日至10日放假调休，共7天。2月2日（星期六）、2月3日（星期日）上班","festival":"2019-2-5","list":[{"date":"2019-2-4","status":"1"},{"date":"2019-2-2","status":"2"},{"date":"2019-2-3","status":"2"},{"date":"2019-2-5","status":"1"},{"date":"2019-2-6","status":"1"},{"date":"2019-2-7","status":"1"},{"date":"2019-2-8","status":"1"},{"date":"2019-2-9","status":"1"},{"date":"2019-2-10","status":"1"}],"list#num#baidu":9,"name":"春节","rest":"拼假建议：2019年2月11日（周一）-2019年2月15日（周五）请假5天，可拼14天春节小长假。"}]
         * holidaylist : [{"name":"元旦","startday":"2019-1-1"},{"name":"除夕","startday":"2019-2-4"},{"name":"春节","startday":"2019-2-4"},{"name":"清明节","startday":"2019-4-5"},{"name":"劳动节","startday":"2019-5-1"},{"name":"端午节","startday":"2019-6-7"},{"name":"中秋节","startday":"2019-9-13"},{"name":"国庆节","startday":"2019-10-1"}]
         * key : 2019年2月
         * selectday : 2019-2-1
         * url : http://nourl.baidu.com/6018
         * loc : https://ss1.baidu.com/8aQDcnSm2Q5IlBGlnYG/q?r=2002753&k=2019%E5%B9%B42%E6%9C%88
         * SiteId : 2002753
         * _version : 2387
         * _select_time : 1544761606
         * clicklimit : 1-3
         * ExtendedLocation :
         * OriginQuery : 2019年2月
         * tplt : calendar_new
         * resourceid : 6018
         * fetchkey : 6018_2019年2月
         * appinfo :
         * role_id : 10
         * disp_type : 0
         */

        private int StdStg;
        private int StdStl;
        private String _update_time;
        private String cambrian_appid;
        private String key;
        private String selectday;
        private String url;
        private String loc;
        private int SiteId;
        private int _version;
        private int _select_time;
        private String clicklimit;
        private String ExtendedLocation;
        private String OriginQuery;
        private String tplt;
        private String resourceid;
        private String fetchkey;
        private String appinfo;
        private int role_id;
        private int disp_type;
        private List<AlmanacBean> almanac;
        private List<HolidayBean> holiday;
        private List<HolidaylistBean> holidaylist;

        public int getStdStg() {
            return StdStg;
        }

        public void setStdStg(int StdStg) {
            this.StdStg = StdStg;
        }

        public int getStdStl() {
            return StdStl;
        }

        public void setStdStl(int StdStl) {
            this.StdStl = StdStl;
        }

        public String get_update_time() {
            return _update_time;
        }

        public void set_update_time(String _update_time) {
            this._update_time = _update_time;
        }

        public String getCambrian_appid() {
            return cambrian_appid;
        }

        public void setCambrian_appid(String cambrian_appid) {
            this.cambrian_appid = cambrian_appid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getSelectday() {
            return selectday;
        }

        public void setSelectday(String selectday) {
            this.selectday = selectday;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getLoc() {
            return loc;
        }

        public void setLoc(String loc) {
            this.loc = loc;
        }

        public int getSiteId() {
            return SiteId;
        }

        public void setSiteId(int SiteId) {
            this.SiteId = SiteId;
        }

        public int get_version() {
            return _version;
        }

        public void set_version(int _version) {
            this._version = _version;
        }

        public int get_select_time() {
            return _select_time;
        }

        public void set_select_time(int _select_time) {
            this._select_time = _select_time;
        }

        public String getClicklimit() {
            return clicklimit;
        }

        public void setClicklimit(String clicklimit) {
            this.clicklimit = clicklimit;
        }

        public String getExtendedLocation() {
            return ExtendedLocation;
        }

        public void setExtendedLocation(String ExtendedLocation) {
            this.ExtendedLocation = ExtendedLocation;
        }

        public String getOriginQuery() {
            return OriginQuery;
        }

        public void setOriginQuery(String OriginQuery) {
            this.OriginQuery = OriginQuery;
        }

        public String getTplt() {
            return tplt;
        }

        public void setTplt(String tplt) {
            this.tplt = tplt;
        }

        public String getResourceid() {
            return resourceid;
        }

        public void setResourceid(String resourceid) {
            this.resourceid = resourceid;
        }

        public String getFetchkey() {
            return fetchkey;
        }

        public void setFetchkey(String fetchkey) {
            this.fetchkey = fetchkey;
        }

        public String getAppinfo() {
            return appinfo;
        }

        public void setAppinfo(String appinfo) {
            this.appinfo = appinfo;
        }

        public int getRole_id() {
            return role_id;
        }

        public void setRole_id(int role_id) {
            this.role_id = role_id;
        }

        public int getDisp_type() {
            return disp_type;
        }

        public void setDisp_type(int disp_type) {
            this.disp_type = disp_type;
        }

        public List<AlmanacBean> getAlmanac() {
            return almanac;
        }

        public void setAlmanac(List<AlmanacBean> almanac) {
            this.almanac = almanac;
        }

        public List<HolidayBean> getHoliday() {
            return holiday;
        }

        public void setHoliday(List<HolidayBean> holiday) {
            this.holiday = holiday;
        }

        public List<HolidaylistBean> getHolidaylist() {
            return holidaylist;
        }

        public void setHolidaylist(List<HolidaylistBean> holidaylist) {
            this.holidaylist = holidaylist;
        }

        public static class AlmanacBean {
            /**
             * avoid : 出行.治病.安葬.开市.
             * date : 2019-2-1
             * suit : 会亲友.纳采.进人口.修造.动土.竖柱.上梁.祭祀.开光.塑绘.祈福.斋醮.嫁娶.安床.移徙.入宅.安香.纳畜.
             */

            private String avoid;
            private String date;
            private String suit;

            public String getAvoid() {
                return avoid;
            }

            public void setAvoid(String avoid) {
                this.avoid = avoid;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSuit() {
                return suit;
            }

            public void setSuit(String suit) {
                this.suit = suit;
            }
        }

        public static class HolidayBean {
            /**
             * desc : 2018年12月30日至2019年1月1日放假调休，共3天。2018年12月29日（星期六）上班。
             * festival : 2019-1-1
             * list : [{"date":"2018-12-29","status":"2"},{"date":"2018-12-31","status":"1"},{"date":"2019-1-1","status":"1"},{"date":"2018-12-30","status":"1"}]
             * list#num#baidu : 4
             * name : 元旦
             * rest : 拼假建议：2019年1月2日（周三）~2019年1月4日（周五）请假3天，可拼8天元旦小长假
             */

            private String desc;
            private String festival;
            @SerializedName("list#num#baidu")
            private int _$ListNumBaidu127; // FIXME check this code
            private String name;
            private String rest;
            private List<ListBean> list;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getFestival() {
                return festival;
            }

            public void setFestival(String festival) {
                this.festival = festival;
            }

            public int get_$ListNumBaidu127() {
                return _$ListNumBaidu127;
            }

            public void set_$ListNumBaidu127(int _$ListNumBaidu127) {
                this._$ListNumBaidu127 = _$ListNumBaidu127;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRest() {
                return rest;
            }

            public void setRest(String rest) {
                this.rest = rest;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * date : 2018-12-29
                 * status : 2
                 */

                private String date;
                private String status;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }
            }
        }

        public static class HolidaylistBean {
            /**
             * name : 元旦
             * startday : 2019-1-1
             */

            private String name;
            private String startday;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStartday() {
                return startday;
            }

            public void setStartday(String startday) {
                this.startday = startday;
            }
        }
    }



}
