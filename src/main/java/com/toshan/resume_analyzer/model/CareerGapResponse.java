package com.toshan.resume_analyzer.model;

import java.util.List;

public class CareerGapResponse {

    private String currentProfession;
    private String targetRole;

    private int matchScore;

    private List<String> strengths;
    private List<String> weaknesses;
    private List<String> missingSkills;

    private List<String> learningRoadmap;
    private List<String> suggestedJobs;

    private String careerSummary;

    public CareerGapResponse() {
    }

    public CareerGapResponse(
            String currentProfession,
            String targetRole,
            int matchScore,
            List<String> strengths,
            List<String> weaknesses,
            List<String> missingSkills,
            List<String> learningRoadmap,
            List<String> suggestedJobs,
            String careerSummary) {

        this.currentProfession = currentProfession;
        this.targetRole = targetRole;
        this.matchScore = matchScore;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.missingSkills = missingSkills;
        this.learningRoadmap = learningRoadmap;
        this.suggestedJobs = suggestedJobs;
        this.careerSummary = careerSummary;
    }

    public String getCurrentProfession() {
        return currentProfession;
    }

    public String getTargetRole() {
        return targetRole;
    }

    public int getMatchScore() {
        return matchScore;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public List<String> getLearningRoadmap() {
        return learningRoadmap;
    }

    public List<String> getSuggestedJobs() {
        return suggestedJobs;
    }

    public String getCareerSummary() {
        return careerSummary;
    }
}