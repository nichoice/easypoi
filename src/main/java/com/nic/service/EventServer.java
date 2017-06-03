package com.nic.service;

import com.nic.pojo.Event;
import com.nic.pojo.Resultmap;

import java.util.List;
import java.util.Map;

/**
 * Created by Nic on 2017/5/23.
 *
 */
public interface EventServer {
    List<Event> getEvents();
    List<Event> getEventsbyTime(Map map);
    List<Resultmap> find(Map map);
}
