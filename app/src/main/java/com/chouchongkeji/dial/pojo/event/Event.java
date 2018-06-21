package com.chouchongkeji.dial.pojo.event;

import java.util.Date;

public class Event {
    private Integer id;

    private String eventName;

    private Date created;

    private Date updated;

    public Event(Integer id, String eventName, Date created, Date updated) {
        this.id = id;
        this.eventName = eventName;
        this.created = created;
        this.updated = updated;
    }

    public Event() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName == null ? null : eventName.trim();
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