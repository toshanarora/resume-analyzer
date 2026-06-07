package com.toshan.resume_analyzer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import com.toshan.resume_analyzer.model.CareerGapResponse;
import com.toshan.resume_analyzer.model.ResumeAnalysisResponse;

@Service
public class AIAnalysisService {


@Value("${gemini.api.key1}")
private String apiKey1;

@Value("${gemini.api.key2}")
private String apiKey2;

private GenerateContentResponse generateWithFallback(
        Content content) throws Exception {

    try {

        System.out.println("Using Gemini API Key 1");

        Client client = Client.builder()
                .apiKey(apiKey1)
                .build();

        return client.models.generateContent(
                "gemini-3-flash-preview",
                List.of(content),
                GenerateContentConfig.builder().build()
        );

    } catch (Exception e) {

        System.out.println(
                "Primary key failed. Switching to Gemini API Key 2");

        Client backupClient = Client.builder()
                .apiKey(apiKey2)
                .build();

        return backupClient.models.generateContent(
                "gemini-3-flash-preview",
                List.of(content),
                GenerateContentConfig.builder().build()
        );
    }
}

public ResumeAnalysisResponse analyzeResume(
        String resumeText) throws Exception {

    String prompt = """
            Analyze the following resume.

            Return ONLY valid JSON.

            Format:

            {
              "profession": "",
              "experience": "",
              "skills": [],
              "certifications": [],
              "projects": [],
              "strengths": [],
              "weaknesses": [],
              "suggestedRoles": [],
              "summary": ""
            }

            Resume:

            """ + resumeText;

    Content content = Content.builder()
            .role("user")
            .parts(List.of(
                    Part.fromText(prompt)
            ))
            .build();

    GenerateContentResponse response =
            generateWithFallback(content);

    String json = response.text();

    json = json.replace("```json", "")
               .replace("```", "")
               .trim();

    ObjectMapper mapper = new ObjectMapper();

    return mapper.readValue(
            json,
            ResumeAnalysisResponse.class
    );
}

public CareerGapResponse analyzeCareerGap(
        String resumeText,
        String targetRole) throws Exception {

    String prompt;

    if (targetRole == null || targetRole.isBlank()) {

        prompt = """
                Analyze the resume.

                The user has not provided a target role.

                Based on skills, education, projects,
                certifications and experience:

                - identify strengths
                - identify weaknesses
                - suggest suitable jobs
                - suggest a learning roadmap
                - provide a career summary

                Return ONLY valid JSON.

                Format:

                {
                  "currentProfession": "",
                  "targetRole": "Not Specified",
                  "matchScore": 100,
                  "strengths": [],
                  "weaknesses": [],
                  "missingSkills": [],
                  "learningRoadmap": [],
                  "suggestedJobs": [],
                  "careerSummary": ""
                }

                Resume:

                """ + resumeText;

    } else {

        prompt = """
                Analyze the resume against the target role.

                Evaluate the candidate honestly.

                Identify:
                - strengths
                - weaknesses
                - missing skills
                - learning roadmap
                - suitable jobs

                Calculate a realistic matchScore from 0 to 100.

                Return ONLY valid JSON.

                Format:

                {
                  "currentProfession": "",
                  "targetRole": "",
                  "matchScore": 0,
                  "strengths": [],
                  "weaknesses": [],
                  "missingSkills": [],
                  "learningRoadmap": [],
                  "suggestedJobs": [],
                  "careerSummary": ""
                }

                Target Role:
                """ + targetRole +

                """

                Resume:

                """ + resumeText;
    }

    Content content = Content.builder()
            .role("user")
            .parts(List.of(
                    Part.fromText(prompt)
            ))
            .build();

    GenerateContentResponse response =
            generateWithFallback(content);

    String json = response.text();

    json = json.replace("```json", "")
               .replace("```", "")
               .trim();

    ObjectMapper mapper = new ObjectMapper();

    return mapper.readValue(
            json,
            CareerGapResponse.class
    );
}


}
