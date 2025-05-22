package com.thiagodeas.curriculum_maker.services;

import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.thiagodeas.curriculum_maker.dto.CurriculumRequestDto;
import com.thiagodeas.curriculum_maker.exceptions.CurriculumPdfGenerationException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CurriculumServiceTest {
    private CurriculumService curriculumService;
    private TemplateEngine templateEngine;

    @BeforeEach
    void setup() {
        templateEngine = mock(TemplateEngine.class);

        curriculumService = new CurriculumService(templateEngine);
    }

    @Test
    void generatePdfReturnsByteArray() {
        CurriculumRequestDto dto = new CurriculumRequestDto();

        dto.setFullName("Thiago Alves");
        dto.setJobTitle("Backend Developer");

        when(templateEngine.process(eq("curriculum"), any(Context.class)))
        .thenReturn("<html><body><h1>Test</h1></body></html>");

        byte[] pdfBytes = curriculumService.generatePdf(dto);

        assertNotNull(pdfBytes);
        assertTrue(pdfBytes.length > 0);

        verify(templateEngine, times(1)).process(eq("curriculum"), any(Context.class));
    }

    @Test
    void generatePdfThrowsExceptionOnFailure() {
        CurriculumRequestDto dto = new CurriculumRequestDto();

        dto.setFullName("Thiago Alves");

        when(templateEngine.process(anyString(), any(Context.class)))
        .thenThrow(new CurriculumPdfGenerationException("Error when generating PDF"));

        CurriculumPdfGenerationException thrown = assertThrows(
            CurriculumPdfGenerationException.class,
            () -> curriculumService.generatePdf(dto)
        );

        assertEquals("Error when generating PDF", thrown.getMessage());
    }
}
