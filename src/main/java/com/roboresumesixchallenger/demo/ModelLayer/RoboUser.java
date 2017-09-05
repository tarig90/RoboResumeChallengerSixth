package com.roboresumesixchallenger.demo.ModelLayer;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
public class RoboUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=3, max =30)
    private String firstName;

    @NotNull
    @Size(min=3, max =30)
    private String lastName;

    @NotNull
    @Size(min=3, max =30)
    @Email
    private String emailAddress;



    @OneToMany(mappedBy = "roboUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   public Set<Experience> experiences;




    @OneToMany(mappedBy = "roboUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<EducationClass> educationClass;



    @OneToMany(mappedBy = "roboUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<SkillsClass> skillClass;


    public RoboUser(String firstName, String lastName, String emailAddress, Set<Experience> experiences, Set<EducationClass> educationClass, Set<SkillsClass> skillClass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.experiences = new HashSet<Experience>();
        this.educationClass = new HashSet<EducationClass>();
        this.skillClass = new HashSet<SkillsClass>();
    }

    public RoboUser()
    {


    }



    @Override
    public String toString() {
        return "RoboUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", experiences=" + experiences +
                ", educationClass=" + educationClass +
                ", skillClass=" + skillClass +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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



    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }




    public Set<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;
    }

    public Set<EducationClass> getEducationClass() {
        return educationClass;
    }

    public void setEducationClass(Set<EducationClass> educationClass) {
        this.educationClass = educationClass;
    }

    public Set<SkillsClass> getSkillClass() {
        return skillClass;
    }

    public void setSkillClass(Set<SkillsClass> skillClass) {
        this.skillClass = skillClass;
    }


   public void addEdu(EducationClass ed)
   {
       ed.setRoboUser(this);
       this.educationClass.add(ed);
   }
}
