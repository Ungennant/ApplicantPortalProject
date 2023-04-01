package org.serf.ApplicantPortal.dto;

import org.serf.ApplicantPortal.domain.Faculty;

import java.util.List;

public class ApplicantDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private int birthYear;
    private Faculty faculty;
    private String profileImage;

    public ApplicantDTO(Integer id, String firstName, String lastName, int birthYear, Faculty faculty, String profileImage) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.faculty = faculty;

        this.profileImage = profileImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
