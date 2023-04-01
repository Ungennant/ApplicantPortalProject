package org.serf.ApplicantPortal.domain;

import org.serf.ApplicantPortal.domain.converters.SubjectsConverter;
import org.serf.ApplicantPortal.domain.enums.ESubject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "faculty_name")
    private String facultyName;
    @Column(name = "recruitment_plan")
    private Integer recruitmentPlan;
    @Convert(converter = SubjectsConverter.class)
    @Column(name = "subjects")
    private List<ESubject> subjects;

    public Faculty() {
    }

    public Faculty(Integer id, String facultyName, Integer recruitmentPlan) {
        this.id = id;
        this.facultyName = facultyName;
        this.recruitmentPlan = recruitmentPlan;
    }

    public Faculty(String facultyName, Integer recruitmentPlan) {
        this.facultyName = facultyName;
        this.recruitmentPlan = recruitmentPlan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public Integer getRecruitmentPlan() {
        return recruitmentPlan;
    }

    public void setRecruitmentPlan(Integer recruitmentPlan) {
        this.recruitmentPlan = recruitmentPlan;
    }

    public List<ESubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<ESubject> subjects) {
        this.subjects = subjects;
    }
}
