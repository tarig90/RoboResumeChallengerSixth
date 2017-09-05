package com.directory.demo.ModelLayer;


import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Department
{
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private long id;
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Employee> employeeSet;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departmentHead_id")
    private Employee head;


    public Department(String name){
        this.name=name;
//        this.id=id;
    }

    public Department()
    {}

    @Override
    public String toString(){
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

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    public void addEmployee(Employee employee){
        employee.setDepartment(this);
        this.employeeSet.add(employee);
    }

    public Employee getHead() {
        return head;
    }

    public void setHead(Employee head) {
        this.head = head;
    }

public void removeEmployee(Employee employee){
//        Set<Employee> temp = this.employeeSet;
//        temp.remove(employee);
//        this.setEmployeeSet(temp);
        this.getEmployeeSet().remove(employee);

}

}
