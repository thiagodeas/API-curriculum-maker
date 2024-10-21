package com.thiagodeas.curriculum_maker.controllers;


import com.thiagodeas.curriculum_maker.models.UserProfile;
import com.thiagodeas.curriculum_maker.services.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/curriculum")
public class CurriculumController {

    @Autowired
    private CurriculumService curriculumService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateCurriculum(@RequestBody UserProfile userProfile){
        String pdfUrl = curriculumService.generatePdf(userProfile);
        return ResponseEntity.ok(pdfUrl);
    }

}
