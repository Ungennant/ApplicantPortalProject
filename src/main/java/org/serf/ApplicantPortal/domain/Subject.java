package org.serf.ApplicantPortal.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "rate")
    private Byte rate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    public Subject() {
    }

    public Subject(Integer id, String name, Byte rate, Applicant applicant) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.applicant = applicant;
    }

    public Subject(String name, Byte rate) {
        this.name = name;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getRate() {
        return rate;
    }

    public void setRate(Byte rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id) && Objects.equals(name, subject.name) && Objects.equals(rate, subject.rate) && Objects.equals(applicant, subject.applicant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rate, applicant);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                ", applicant=" + applicant +
                '}';
    }
}
