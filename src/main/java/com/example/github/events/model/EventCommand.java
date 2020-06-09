package com.example.github.events.model;

import lombok.Data;

@Data
public class EventCommand {

    private String owner;
    private String repo;
    private String[] eventTypesSelected;
}
