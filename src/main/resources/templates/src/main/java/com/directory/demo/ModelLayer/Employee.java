package com.directory.demo.ModelLayer;


import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Employee
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;


    private long cellphone;
    private long workNUmber;

   @ManyToMany
    private Set<Teams> empToTeam;



    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

   public Employee()
   {}

    public Employee(String firstName){
        this.firstName = firstName;
//        this.id=id;
    }
    @Override
    public String toString()
    {
        return this.firstName;
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

    public long getCellphone() {
        return cellphone;
    }

    public void setCellphone(long cellphone) {
        this.cellphone = cellphone;
    }

    public long getWorkNUmber() {
        return workNUmber;
    }

    public void setWorkNUmber(long workNUmber) {
        this.workNUmber = workNUmber;
    }


    public Set<Teams> getEmpToTeam() {
        return empToTeam;
    }

    public void setEmpToTeam(Set<Teams> empToTeam) {
        this.empToTeam = empToTeam;
    }

    public void addTeam(Teams team)
    {
        team.addEmployee(this);
        this.getEmpToTeam().add(team);
    }



}
