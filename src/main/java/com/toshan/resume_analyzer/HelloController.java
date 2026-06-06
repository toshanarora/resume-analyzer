package com.toshan.resume_analyzer;
import com.toshan.resume_analyzer.service.GreetingService;
import com.toshan.resume_analyzer.service.PdfService;
import com.toshan.resume_analyzer.service.AIAnalysisService;
import java.io.IOException;
import java.util.List;
import com.toshan.resume_analyzer.model.SkillAnalysisResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import com.toshan.resume_analyzer.model.CareerGapResponse;
import com.toshan.resume_analyzer.model.FileUploadResponse;
import com.toshan.resume_analyzer.model.ResumeAnalysisResponse;

import org.springframework.web.bind.annotation.RequestPart;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final PdfService pdfService;
    private final AIAnalysisService aiAnalysisService;
    private final GreetingService greetingService;

    public HelloController(
        GreetingService greetingService,
        PdfService pdfService, 
        AIAnalysisService aiAnalysisService) {

    this.greetingService = greetingService;
    this.pdfService = pdfService;
    this.aiAnalysisService = aiAnalysisService;

    }
    

    @GetMapping("/hello")
    public ApiResponse hello(@RequestParam(required = false) String name) {

        String message = greetingService.getGreeting(name);

        return new ApiResponse(message, true);
    }
    @GetMapping("/test")
    public String test() {
      return "Test endpoint working";
    }
    @PostMapping("/upload")
    public FileUploadResponse uploadFile(
        @RequestPart("file") MultipartFile file) {

      return new FileUploadResponse(
            file.getOriginalFilename(),
            file.getSize(),
            file.getContentType());
    }
   
    @PostMapping("/read")
    public String readPdf(
        @RequestParam("file") MultipartFile file) throws IOException {

       return pdfService.extractText(file);
       }
    @PostMapping("/analyze")
    public SkillAnalysisResponse analyzeResume(
        @RequestParam("file") MultipartFile file)
        throws IOException {

      String text = pdfService.extractText(file);

      List<String> skills = greetingService.extractSkills(text);

      return new SkillAnalysisResponse(
            skills,
            skills.size());
    }
    @GetMapping("/ai-test")
    public ResumeAnalysisResponse testAI() throws Exception{

      return aiAnalysisService.analyzeResume(
            "Java Spring Boot AWS Docker");
    }
    @PostMapping("/analyze-ai")
    public ResumeAnalysisResponse analyzeResumeAI(
        @RequestParam("file") MultipartFile file)
        throws Exception {

      String text = pdfService.extractText(file);

      return aiAnalysisService.analyzeResume(text);
    }
    @PostMapping("/career-gap")
    public CareerGapResponse analyzeCareerGap(
        @RequestParam("file") MultipartFile file,
        @RequestParam(required = false) String targetRole)
        throws Exception {

      String text = pdfService.extractText(file);

      return aiAnalysisService.analyzeCareerGap(
            text,
            targetRole);
    }
      
}