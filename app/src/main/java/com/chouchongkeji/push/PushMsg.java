package com.chouchongkeji.push;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linqin
 * @date 2018/12/28 9:57
 */

public class PushMsg {

    public List<Integer> users;
    public String title;
    public String text;
    public TransmissionContent transmissionContent;

    public static PushMsg msg() {
        return new PushMsg();
    }

    public PushMsg users(List<Integer> users) {
        this.users = users;
        return this;
    }

    public PushMsg user(Integer user) {
        if (this.users == null) {
            this.users = new ArrayList<>();
        }
        users.add(user);
        return this;
    }

    public PushMsg title(String title) {
        this.title = title;
        return this;
    }

    public PushMsg text(String text) {
        this.text = text;
        return this;
    }

    public PushMsg transmissionContent(TransmissionContent transmissionContent) {
        this.transmissionContent = transmissionContent;
        return this;
    }

    public PushMsg messageType(Integer messageType) {
        if ( this.transmissionContent == null) {
            this.transmissionContent = TransmissionContent.create();
        }
        this.transmissionContent.messageType = messageType;
        return this;
    }

    public PushMsg messageId(Integer messageId) {
        if ( this.transmissionContent == null) {
            this.transmissionContent = TransmissionContent.create();
        }
        this.transmissionContent.messageId = messageId;
        return this;
    }

    public static class TransmissionContent {
        public Integer messageType;
        public Integer messageId;

        public static TransmissionContent create() {
            return new TransmissionContent();
        }

        public TransmissionContent messageType(Integer messageType) {
            this.messageType = messageType;
            return this;
        }

        public TransmissionContent messageId(Integer messageId) {
            this.messageId = messageId;
            return this;
        }
    }

}
