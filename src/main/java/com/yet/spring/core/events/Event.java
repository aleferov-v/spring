package com.yet.spring.core.events;

import java.text.DateFormat;
import java.util.Date;

public class Event {
    private int id;
    private String msg;
    private Date date;
    private DateFormat df;
    private EventType type;

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Event:\t" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date);
    }
}
