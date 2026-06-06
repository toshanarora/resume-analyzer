package com.toshan.resume_analyzer.model;

import java.util.List;

public class ResumeAnalysisResponse {

    private String profession;
    private String experience;

    private List<String> skills;
    private List<String> certifications;
    private List<String> projects;

    private List<String> strengths;
    private List<String> weaknesses;

    private List<String> suggestedRoles;

    private String summary;
    public ResumeAnalysisResponse() {
}

    public ResumeAnalysisResponse(
        
            String profession,
            String experience,
            List<String> skills,
            List<String> certifications,
            List<String> projects,
            List<String> strengths,
            List<String> weaknesses,
            List<String> suggestedRoles,
            String summary) {

        this.profession = profession;
        this.experience = experience;
        this.skills = skills;
        this.certifications = certifications;
        this.projects = projects;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.suggestedRoles = suggestedRoles;
        this.summary = summary;
    }

    public String getProfession() {
        return profession;
    }

    public String getExperience() {
        return experience;
    }

    public List<String> getSkills() {
        return skills;
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public List<String> getProjects() {
        return projects;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public List<String> getSuggestedRoles() {
        return suggestedRoles;
    }

    public String getSummary() {
        return summary;
    }
}