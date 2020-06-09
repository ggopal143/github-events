package com.example.github.events.model;

import lombok.Data;

@Data
public class EventData {
    private String type;
    private Actor actor;
    private String created_at;
}
