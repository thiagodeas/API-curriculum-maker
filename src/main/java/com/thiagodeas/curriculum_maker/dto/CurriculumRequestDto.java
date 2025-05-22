package com.thiagodeas.curriculum_maker.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CurriculumRequestDto {

    @NotBlank(message = "Nome completo é obrigatório.")
    private String fullName;

    @NotBlank(message = "Cargo pretendido é obrigatório.")
    private String jobTitle;

    @NotBlank(message = "Cidade é obrigatória.")
    private String city;

    @NotBlank(message = "Estado é obrigatório.")
    private String state;

    @NotBlank(message = "País é obrigatório.")
    private String country;

    @NotBlank(message = "O número de telefone é obrigatório.")
    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$", message = "Telefone inválido. Use o formato (DD) 00000-0000.")
    private String phone;

    @NotBlank(message = "Email é obrigatório.")
    @Email(message = "Email inválido.")
    private String email;

    @NotBlank(message = "URL do LInkedIn é obrigatória.")
    @Pattern(regexp = "^(https?://)?(www\\.)?linkedin\\.com/.*$", message = "URL do LinkedIn inválida.")
    private String linkedin;

    @NotBlank(message = "URL do GitHub é obrigatória.")
    @Pattern(regexp = "^(https?://)?(www\\.)?github\\.com/.*$", message = "URL do GitHub inválida.")
    private String github;

    @NotBlank(message = "Sobre mim é obrigatório.")
    private String aboutMe;

    private List<Experience> experience;

    @NotEmpty(message = "A lista de categorias de habilidade é obrigatória.")
    @Valid
    private List<KnowledgeByCategory> knowledgeByCategory;

    @NotEmpty(message = "A lista de formações é obrigatória.")
    @Valid
    private List<Education> education;

    private List<Project> projects;
    private List<AdditionalActivity> additionalActivities;

    @Data
    public static class Experience {
      @NotBlank(message = "Cargo é obrigatório.")
      private String position;

      @NotBlank(message = "Empresa é obrigatória.")
      private String company;

      @NotBlank(message = "Período é obrigatório.")
      @Pattern(
        regexp = "^(0[1-9]|1[0-2])/\\d{4}\\s-\\s((0[1-9]|1[0-2])/\\d{4}|Atualmente)$",
        message = "Período deve estar no formato MM/AAAA - MM/AAAA ou MM/AAAA - Atualmente."
      )
      private String period;

      @NotBlank(message = "Descrição da experiência é obrigatória.")
      private String description;
    }   

    @Data
    public static class KnowledgeByCategory {
        @NotBlank(message = "Nome da categoria é obrigatório.")
        private String categoryName;

        @NotEmpty(message = "A lista de habilidades não pode estar vazia.")
        private List<@NotBlank(message = "Habilidade não pode estar vazia.") String> skills;
    }

    @Data
    public static class Education {
        @NotBlank(message = "Nome do curso é obrigatório.")
        private String name;

        @NotBlank(message = "Instituição é obrigatória.")
        private String institution;

        @Pattern(regexp = "^\\d{4}$", message = "Ano deve ter 4 dígitos.")
        private String year;
    }

    @Data
    public static class Project {
        @NotBlank(message = "Título do projeto é obrigatório.")
        private String title;

        @NotBlank(message = "Descrição do projeto é obrigatória.")
        private String description;
    }

    @Data
    public static class AdditionalActivity {
        @NotBlank(message = "Descrição da atividade adicional é obrigatória.")
        private String description;
    }
}
