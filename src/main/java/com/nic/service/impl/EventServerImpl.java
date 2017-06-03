package com.nic.service.impl;

import com.nic.mapper.EventMapper;
import com.nic.pojo.Event;
import com.nic.pojo.Resultmap;
import com.nic.service.EventServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * Created by Nic on 2017/5/23.
 *
 */
@Service
public class EventServerImpl implements EventServer {
    @Autowired
    private EventMapper eventMapper;

    public void setEventMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    @Override
    public List<Event> getEvents() {
        return eventMapper.getEvents();
    }

    @Override
    public List<Event> getEventsbyTime(Map map) {
        return eventMapper.getEventsbyTime(map);
    }

    @Override
    public List<Resultmap> find(Map map) {
        return eventMapper.find(map);
    }


}
