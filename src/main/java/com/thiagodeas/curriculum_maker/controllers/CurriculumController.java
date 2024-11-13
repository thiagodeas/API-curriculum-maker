package com.thiagodeas.curriculum_maker.controllers;

import com.thiagodeas.curriculum_maker.models.UserProfile;
import com.thiagodeas.curriculum_maker.services.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/curriculum")
public class CurriculumController {

    @Autowired
    private CurriculumService curriculumService;

    @CrossOrigin(origins = {"http://localhost:5173", "https://curriculum-maker.vercel.app"})
    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateCurriculum(@RequestBody UserProfile userProfile) {
        byte[] pdfBytes = curriculumService.generatePdf(userProfile);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "curriculumMaker.pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }

}
