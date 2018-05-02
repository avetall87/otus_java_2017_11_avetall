package ru.spb.otus.atmDepartmnent.atm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by avetall  01.05.18.
 */
public class EventManager {
    private Map<String, List<EventListener>> listeners = new ConcurrentHashMap<>();

    public EventManager(String... eventTypes) {
        for (String eventType : eventTypes) {
            this.listeners.put(eventType, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        int index = users.indexOf(listener);
        if (index != -1)
            users.remove(index);
    }

    public void notify(String eventType) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.update();
        }
    }
}
