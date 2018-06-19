package com.yet.spring.core;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.events.Event;
import com.yet.spring.core.events.EventType;
import com.yet.spring.core.loggers.ConsoleEventLogger;
import com.yet.spring.core.loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggersMap;
    private Event event;

    App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggersMap) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggersMap = loggersMap;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        app.logEvent(EventType.INFO, "message1");
        app.logEvent(EventType.ERROR, "message1");
        app.logEvent(EventType.WARNING, "message1");
        ctx.close();
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    private void logEvent(EventType type, String msg) {
        EventLogger logger = loggersMap.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }
        event.setMsg(type + " - Some message for Client: " + client.getId() + ", ID: " + client.getId());
        logger.logEvent(event);
    }
}
