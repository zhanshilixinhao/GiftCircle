package com.chouchongkeji.dial.pojo.home;

import java.util.Date;

public class Almanac {
    private String day;

    private String gongli;

    private String nongli;

    private String avoid;

    private String suit;

    private String jieri;

    private String jieqi24;

    private Date created;

    private Date updated;

    public Almanac(String day, String gongli, String nongli, String avoid, String suit, String jieri, String jieqi24, Date created, Date updated) {
        this.day = day;
        this.gongli = gongli;
        this.nongli = nongli;
        this.avoid = avoid;
        this.suit = suit;
        this.jieri = jieri;
        this.jieqi24 = jieqi24;
        this.created = created;
        this.updated = updated;
    }

    public Almanac() {
        super();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }

    public String getGongli() {
        return gongli;
    }

    public void setGongli(String gongli) {
        this.gongli = gongli == null ? null : gongli.trim();
    }

    public String getNongli() {
        return nongli;
    }

    public void setNongli(String nongli) {
        this.nongli = nongli == null ? null : nongli.trim();
    }

    public String getAvoid() {
        return avoid;
    }

    public void setAvoid(String avoid) {
        this.avoid = avoid == null ? null : avoid.trim();
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit == null ? null : suit.trim();
    }

    public String getJieri() {
        return jieri;
    }

    public void setJieri(String jieri) {
        this.jieri = jieri == null ? null : jieri.trim();
    }

    public String getJieqi24() {
        return jieqi24;
    }

    public void setJieqi24(String jieqi24) {
        this.jieqi24 = jieqi24 == null ? null : jieqi24.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}