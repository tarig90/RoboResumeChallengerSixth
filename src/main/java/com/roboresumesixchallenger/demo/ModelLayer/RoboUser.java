package com.roboresumesixchallenger.demo.ModelLayer;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "USER_DATA")
public class RoboUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Email
    @Column(name = "email", nullable = false)
    private String emailAddress;

    @NotNull
    @Size(min=3, max =30)
    @Column(name = "password")
    private String password;


    @NotNull
    @Size(min=3, max =30)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min=3, max =30)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "username")
    private String username;




    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @OneToMany(mappedBy = "roboUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   public Set<Experience> experiences;




    @OneToMany(mappedBy = "roboUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<EducationClass> educationClass;



    @OneToMany(mappedBy = "roboUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<SkillsClass> skillClass;


//    public RoboUser(String firstName, String lastName, String emailAddress, Set<Experience> experiences, Set<EducationClass> educationClass, Set<SkillsClass> skillClass) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.emailAddress = emailAddress;
//        this.experiences = new HashSet<Experience>();
//        this.educationClass = new HashSet<EducationClass>();
//        this.skillClass = new HashSet<SkillsClass>();
//    }

    public RoboUser()
    {
    }





//    @Override
//    public String toString() {
//        return "RoboUser{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", emailAddress='" + emailAddress + '\'' +
//                ", experiences=" + experiences +
//                ", educationClass=" + educationClass +
//                ", skillClass=" + skillClass +
//               '}';
//    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role)
    {
        this.roles.add(role);
    }
}
