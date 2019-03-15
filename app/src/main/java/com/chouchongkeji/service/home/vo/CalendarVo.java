package com.chouchongkeji.service.home.vo;

/**
 * @author linqin
 * @date 2019/2/25 16:30
 */

public class CalendarVo {

    private String gongli; //公历日期
    private String nongli; //农历日期
    private String avoid;  //忌
    private String suit;  //适宜
    private String jieri; //公历节日
    private String jieqi24;
    private Long date; //日期


    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getAvoid() {
        return avoid;
    }

    public void setAvoid(String avoid) {
        this.avoid = avoid;
    }


    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getGongli() {
        return gongli;
    }

    public void setGongli(String gongli) {
        this.gongli = gongli;
    }

    public String getNongli() {
        return nongli;
    }

    public void setNongli(String nongli) {
        this.nongli = nongli;
    }

    public String getJieri() {
        return jieri;
    }

    public void setJieri(String jieri) {
        this.jieri = jieri;
    }

    public String getJieqi24() {
        return jieqi24;
    }

    public void setJieqi24(String jieqi24) {
        this.jieqi24 = jieqi24;
    }
}
