package com.roboresumesixchallenger.demo.ModelLayer;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;


@Entity
public class EducationClass {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=3, max =30)
    private String schoolName;


    @NotNull
    @DateTimeFormat(pattern ="MM/dd/yyyy")
    private Date graduationDate;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_educid")
    public User user;


    public EducationClass() {
    }

    public EducationClass(String schoolName, Date graduationDate) {
        this.schoolName = schoolName;
        this.graduationDate = graduationDate;
//        this.id=id;
    }


    @Override
    public String toString() {
        return "EducationClass{" +
                "id=" + id +
                ", schoolName='" + schoolName + '\'' +
                ", graduationDate=" + graduationDate +
                ", user=" + user +
                '}';
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void AddUser(User temp)
    {


    }






}


