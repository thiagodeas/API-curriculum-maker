package com.thiagodeas.curriculum_maker.controllers;


import com.thiagodeas.curriculum_maker.models.UserProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/curriculum")
public class CurriculumController {

    @PostMapping("/generate")
    public ResponseEntity<String> generateCurriculum(@RequestBody UserProfile userProfile){
        String pdfUrl = curriculumService.generatePDF(userProfile);
        return ResponseEntity.ok(pdfurl);
    }

}
