package com.thiagodeas.curriculum_maker.dto;

import java.util.List;

import lombok.Data;

@Data
public class CurriculumRequestDto {
    private String fullname;
    private String jobTitle;
    private String city;
    private String state;
    private String country;
    private String phone;
    private String email;
    private String linkedin;
    private String github;
    private String aboutMe;

    private List<Experience> experience;
    private List<String> skills;
    private List<Education> education;
    private List<Project> projects;

    @Data
    public static class Experience {
        private String position;
        private String company;
        private String period;
        private String description;
    }

    @Data
    public static class Education {
        private String name;
        private String institution;
        private String year;
    }

    @Data
    public static class Project {
        private String title;
        private String description;
    }
}
