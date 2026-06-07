package com.toshan.resume_analyzer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.toshan.resume_analyzer.model.CareerGapResponse;
import com.toshan.resume_analyzer.model.ResumeAnalysisResponse;
import com.toshan.resume_analyzer.service.AIAnalysisService;
import com.toshan.resume_analyzer.service.PdfService;

@Controller
public class WebController {

    private final PdfService pdfService;
    private final AIAnalysisService aiAnalysisService;

    public WebController(
            PdfService pdfService,
            AIAnalysisService aiAnalysisService) {

        this.pdfService = pdfService;
        this.aiAnalysisService = aiAnalysisService;
    }

    @PostMapping("/career-gap-ui")
    public String analyzeCareerGapUI(
            @RequestParam("file") MultipartFile file,
            @RequestParam(required = false) String targetRole,
            Model model) throws Exception {

        String text = pdfService.extractText(file);

        CareerGapResponse report =
                aiAnalysisService.analyzeCareerGap(
                        text,
                        targetRole);

        model.addAttribute("report", report);

        return "result";
    }
    @PostMapping("/career-discovery-ui")
    public String analyzeCareerDiscoveryUI(
          @RequestParam("file") MultipartFile file,
          Model model) throws Exception {

        String text = pdfService.extractText(file);

        ResumeAnalysisResponse report =
            aiAnalysisService.analyzeResume(text);

        model.addAttribute("report", report);

        return "career-discovery";
}
}