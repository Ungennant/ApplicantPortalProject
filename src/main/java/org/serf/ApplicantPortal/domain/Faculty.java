package org.serf.ApplicantPortal.domain;

import org.serf.ApplicantPortal.domain.enums.EFaculty;
import org.serf.ApplicantPortal.domain.enums.ESubject;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private EFaculty facultyName;
    @ElementCollection
    private List<ESubject> subjects;
    @Column(name = "recruitment_plan")
    private Integer recruitmentPlan;

    public Faculty() {
    }

    public Faculty(Integer id, EFaculty facultyName, List<ESubject> subjects, Integer recruitmentPlan) {
        this.id = id;
        this.facultyName = facultyName;
        this.subjects = subjects;
        this.recruitmentPlan = recruitmentPlan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EFaculty getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(EFaculty facultyName) {
        this.facultyName = facultyName;
    }

    public List<ESubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<ESubject> subjects) {
        this.subjects = subjects;
    }

    public Integer getRecruitmentPlan() {
        return recruitmentPlan;
    }

    public void setRecruitmentPlan(Integer recruitmentPlan) {
        this.recruitmentPlan = recruitmentPlan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty)) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(id, faculty.id) && facultyName == faculty.facultyName && Objects.equals(subjects, faculty.subjects) && Objects.equals(recruitmentPlan, faculty.recruitmentPlan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, facultyName, subjects, recruitmentPlan);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", facultyName=" + facultyName +
                ", subjects=" + subjects +
                ", recruitmentPlan=" + recruitmentPlan +
                '}';
    }
}
