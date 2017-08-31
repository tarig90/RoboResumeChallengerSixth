package com.roboresumesixchallenger.demo.ModelLayer;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Experience {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private long id;
    private String companyName;



    @OneToMany(mappedBy = "experience", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> employeeSet;

    public Experience(String companyName){
        this.companyName=companyName;
//        this.id=id;
    }

    public Experience()
    {}

    @Override
    public String toString(){
        return this.companyName;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Set<User> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<User> employeeSet) {
        this.employeeSet = employeeSet;
    }



}
