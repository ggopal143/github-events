package com.example.github.events.service;

import com.example.github.events.model.EventData;

import java.util.List;

public interface EventService {
    List<EventData> getEvents(String owner, String repo);
}
