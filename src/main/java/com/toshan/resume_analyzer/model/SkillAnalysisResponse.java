package com.toshan.resume_analyzer.model;

import java.util.List;

public class SkillAnalysisResponse {

    private List<String> skills;
    private int skillsFound;

    public SkillAnalysisResponse(List<String> skills, int skillsFound) {
        this.skills = skills;
        this.skillsFound = skillsFound;
    }

    public List<String> getSkills() {
        return skills;
    }

    public int getSkillsFound() {
        return skillsFound;
    }
}