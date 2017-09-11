package com.roboresumesixchallenger.demo.ModelLayer;


import javax.persistence.*;
import java.util.Set;

@Entity
public class JobDetils {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String employerName;

    private long salaryRange;

    private String salaryDescription;

    public RoboUser getRb() {
        return rb;
    }

    public void setRb(RoboUser rb) {
        this.rb = rb;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private RoboUser rb;

    private String skil;

    @ManyToMany
    private Set<SkillsClass> skillz;

    public JobDetils(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public long getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(long salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getSalaryDescription() {
        return salaryDescription;
    }

    public void setSalaryDescription(String salaryDescription) {
        this.salaryDescription = salaryDescription;
    }

    public Set<SkillsClass> getSkillz() {
        return skillz;
    }

    public void setSkillz(Set<SkillsClass> skillz) {
        this.skillz = skillz;
    }

    public void addSKill(SkillsClass skill)
    {
        this.getSkillz().add(skill);
    }

    public String getSkil() {
        return skil;
    }

    public void setSkil(String skil) {
        this.skil = skil;
    }
}
