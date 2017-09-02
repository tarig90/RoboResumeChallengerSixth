package com.roboresumesixchallenger.demo.ModelLayer;


import javax.persistence.*;

@Entity
public class SkillsClass {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String SkillOne;
    private String SkillTwo;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userskill_id")
    private User user;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSkillOne() {
        return SkillOne;
    }

    public void setSkillOne(String skillOne) {
        SkillOne = skillOne;
    }

    public String getSkillTwo() {
        return SkillTwo;
    }

    public void setSkillTwo(String skillTwo) {
        SkillTwo = skillTwo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
