package com.example.github.events.controller;

import com.example.github.events.model.EventCommand;
import com.example.github.events.model.EventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GitHubEventsController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("command", new EventCommand());
        model.addAttribute("isBeforeSubmit", true);
        return "home";
    }

    @PostMapping("/eventsform")
    public String events(@ModelAttribute("command")EventCommand command, Model model) {
        System.out.println("Owner name:" + command.getOwner());
        String url = "https://api.github.com/repos/"+command.getOwner()+"/"+command.getRepo()+"/events";
        ResponseEntity<List<EventData>> response
                = restTemplate.exchange(url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<EventData>>(){});
        List<EventData> eventDataList = response.getBody();
        System.out.println(command.getEventTypesSelected().length);
        if(command.getEventTypesSelected().length > 0) {
            List<EventData> filteredEventDataList = eventDataList.stream().filter(eventData -> {
                return Arrays.asList(command.getEventTypesSelected()).contains(eventData.getType());
            }).collect(Collectors.toList());
            model.addAttribute("eventList", filteredEventDataList);

        } else {
            model.addAttribute("eventList", eventDataList);
        }
        return "home";
    }

    @ModelAttribute("eventTypes")
    public String[] getMultiCheckboxAllValues() {
        return new String[] {
                "PushEvent", "CreateEvent", "DeleteEvent", "ForkEvent",
                "PublicEvent", "PullRequestEvent", "ReleaseEvent"
        };
    }
}
