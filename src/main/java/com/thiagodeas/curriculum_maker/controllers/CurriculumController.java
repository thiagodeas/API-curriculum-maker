package com.thiagodeas.curriculum_maker.controllers;

import com.thiagodeas.curriculum_maker.models.UserProfile;
import com.thiagodeas.curriculum_maker.services.CurriculumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/curriculum")
@Tag(name = "Curriculum Controller", description = "Gera PDFs de currículos a partir de dados do " +
        "usuário")
public class CurriculumController {

    @Autowired
    private CurriculumService curriculumService;

    @CrossOrigin(origins = {"http://localhost:5173", "https://curriculum-maker.vercel.app"})
    @Operation(summary = "Gera um currículo minimalista em PDF", description = "Recebe dados do " +
            "usuário e devolve um PDF formatado com o currículo", requestBody =
    @RequestBody(description = "Dados de perfil do usuário", content = @Content(schema =
    @Schema(implementation = UserProfile.class))), responses =
            {@ApiResponse(responseCode = "200", description = "PDF gerado com sucesso!",
                    content = @Content(mediaType = "application/pdf")),
                    @ApiResponse(responseCode = "400", description = "Erro na entrada de dados")})

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateCurriculum(@RequestBody UserProfile userProfile) {
        byte[] pdfBytes = curriculumService.generatePdf(userProfile);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "curriculumMaker.pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }

}
