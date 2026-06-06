package com.toshan.resume_analyzer.model;

public class AIAnalysisResponse {

    private String profession;
    private String summary;

    public AIAnalysisResponse(String profession, String summary) {
        this.profession = profession;
        this.summary = summary;
    }

    public String getProfession() {
        return profession;
    }

    public String getSummary() {
        return summary;
    }
}