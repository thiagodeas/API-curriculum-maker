package com.thiagodeas.curriculum_maker.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserProfile {
    private String fullName;
    private String jobTitle;
    private String nationality;
    private String maritalStatus;
    private String city;
    private String state;
    private String country;
    private String phone;
    private String email;
    private String linkedin;
    private String github;
    private String aboutMe;
    private String frontEndKnowledge;
    private String backEndKnowledge;
    private String databaseKnowledge;
    private String cloudKnowledge;
    private String othersKnowledge;
    private List<Education> education;
    private List<Project> projects;
    private List<AdditionalActivity> additionalActivities;
}
