package org.serf.ApplicantPortal.domain;

import org.serf.ApplicantPortal.domain.enums.ESubject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "applicant")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_year")
    private int birthYear;
    @ManyToOne
    @JoinColumn(name = "fk_faculty")
    private Faculty faculty;
    @OneToMany(mappedBy = "applicant")
    private List<Subject> subjects;
    @Column(name = "profile_image")
    private String profileImage;
    @Column(name = "image_name")
    private String imageName;

    public Applicant() {
    }

    public Applicant(Integer id, String firstName, String lastName, int birthYear, Faculty faculty, List<Subject> subjects, String profileImage, String imageName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.faculty = faculty;
        this.subjects = subjects;
        this.profileImage = profileImage;
        this.imageName = imageName;
    }

    public Applicant(String firstName, String lastName, int birthYear, Faculty faculty, List<Subject> subjects, String profileImage, String imageName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.faculty = faculty;
        this.subjects = subjects;
        this.profileImage = profileImage;
        this.imageName = imageName;
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

    public String getImageName() {
        return imageName;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthYear=" + birthYear +
                ", faculty=" + faculty +
                ", subjects=" + subjects +
                ", profileImage='" + profileImage + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
