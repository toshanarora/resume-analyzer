package com.toshan.resume_analyzer.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String getGreeting(String name) {

        if (name == null) {
            return "Hello Guest";
        }

        return "Hello " + name;
    }

    public List<String> extractSkills(String skills) {

        if (skills == null || skills.isBlank()) {
            return List.of();
        }

        return Arrays.asList(skills.split(","));
    }
}