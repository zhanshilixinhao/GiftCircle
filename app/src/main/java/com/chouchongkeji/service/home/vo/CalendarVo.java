package com.chouchongkeji.service.home.vo;

/**
 * @author linqin
 * @date 2019/2/25 16:30
 */

public class CalendarVo {

    private String avoid;  //忌
    private String date; //日期
    private String suit;  //适宜

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
