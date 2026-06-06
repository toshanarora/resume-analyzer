package com.toshan.resume_analyzer.service;

import java.util.ArrayList;
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

    public List<String> extractSkills(String text) {

        List<String> knownSkills = List.of(
                "Java",
                "Spring Boot",
                "SQL",
                "AWS",
                "Docker",
                "Git",
                "Kafka",
                "MongoDB",
                "Hibernate",
                "JUnit",
                "Mockito",
                "Spring Security",
                "Microservices",
                "Kubernetes"
        );

        List<String> foundSkills = new ArrayList<>();

        if (text == null || text.isBlank()) {
            return foundSkills;
        }

        for (String skill : knownSkills) {
            if (text.toLowerCase().contains(skill.toLowerCase())) {
                foundSkills.add(skill);
            }
        }

        return foundSkills;
    }
}