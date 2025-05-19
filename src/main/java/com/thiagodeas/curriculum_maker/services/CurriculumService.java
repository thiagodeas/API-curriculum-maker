package com.thiagodeas.curriculum_maker.services;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.thiagodeas.curriculum_maker.dto.CurriculumRequestDto;
import com.thiagodeas.curriculum_maker.exceptions.CurriculumPdfGenerationException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurriculumService {
    private final TemplateEngine templateEngine;

    public byte[] generatePdf(CurriculumRequestDto dto) {
        Context context = new Context();
        context.setVariable("curriculum", dto);

        String htmlContent = templateEngine.process("curriculum", context);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(htmlContent, null);
            builder.toStream(outputStream);
            builder.run();

            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new CurriculumPdfGenerationException("Error when generating PDF", e);
        }
    }
}
