package com.roboresumesixchallenger.demo.ModelLayer;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Experience {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private long id;

    @NotNull
    @Size(min=2, max=10)
    private String companyName;
    @NotNull
    @Size(min=3, max=20)
    private String title;
    @NotNull
    @Size(min=3, max=20)
    private String duty1;
    @NotNull
    @Size(min=3, max=20)
    private String duty2;


    // move classes to here
     @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "userexp_id")
     private RoboUser roboUser;


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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuty1() {
        return duty1;
    }

    public void setDuty1(String duty1) {
        this.duty1 = duty1;
    }

    public String getDuty2() {
        return duty2;
    }

    public void setDuty2(String duty2) {
        this.duty2 = duty2;
    }


    public RoboUser getRoboUser() {
        return roboUser;
    }

    public void setRoboUser(RoboUser roboUser) {
        this.roboUser = roboUser;
    }

}
