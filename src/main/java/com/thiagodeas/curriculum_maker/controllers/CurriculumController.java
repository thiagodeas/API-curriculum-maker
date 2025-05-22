package com.thiagodeas.curriculum_maker.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagodeas.curriculum_maker.dto.CurriculumRequestDto;
import com.thiagodeas.curriculum_maker.services.CurriculumService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/curriculum")
@RequiredArgsConstructor
public class CurriculumController {
    
    private final CurriculumService curriculumService;

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generatePdf(@RequestBody @Valid CurriculumRequestDto requestDto) {
        byte[] pdfBytes = curriculumService.generatePdf(requestDto);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=curriculo.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
