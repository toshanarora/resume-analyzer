package com.toshan.resume_analyzer;
import com.toshan.resume_analyzer.service.GreetingService;
import java.util.List;
import com.toshan.resume_analyzer.model.SkillAnalysisResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final GreetingService greetingService;

    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
    @GetMapping("/analyze")
    public SkillAnalysisResponse analyzeSkills(
        @RequestParam(required = false) String skills) {

         List<String> skillList = greetingService.extractSkills(skills);

        return new SkillAnalysisResponse(
            skillList,
            skillList.size());
}

    @GetMapping("/hello")
    public ApiResponse hello(@RequestParam(required = false) String name) {

        String message = greetingService.getGreeting(name);

        return new ApiResponse(message, true);
    }
}