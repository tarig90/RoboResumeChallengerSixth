package com.directory.demo.ModelLayer;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Teams
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToMany
    private Set<Employee> teamToEmp;

    public Teams()
    {}

    public Teams(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getTeamToEmp() {
        return teamToEmp;
    }

    public void setTeamToEmp(Set<Employee> teamToEmp) {
        this.teamToEmp = teamToEmp;
    }

    public void addEmployee(Employee employee){
        this.getTeamToEmp().add(employee);
    }


}
