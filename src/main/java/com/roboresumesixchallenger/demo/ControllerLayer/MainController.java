package com.roboresumesixchallenger.demo.ControllerLayer;


import com.roboresumesixchallenger.demo.ModelLayer.EducationClass;
import com.roboresumesixchallenger.demo.ModelLayer.Experience;
import com.roboresumesixchallenger.demo.ModelLayer.SkillsClass;
import com.roboresumesixchallenger.demo.ModelLayer.User;

import com.roboresumesixchallenger.demo.RepositoryLayer.EducationRepository;
import com.roboresumesixchallenger.demo.RepositoryLayer.ExperienceRepository;
import com.roboresumesixchallenger.demo.RepositoryLayer.SkillRepository;
import com.roboresumesixchallenger.demo.RepositoryLayer.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class MainController {


//    @Autowired
//    EducationRepository educationRepository;

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    SkillRepository skillRepository;


    @RequestMapping("/")
    public String indexPage()
    {
        return "index";
    }


    // adding a user section
    @GetMapping("/adduser")
    public String addUser(Model model)
    {
        model.addAttribute("user", new User());

        return "adduser";
    }

    @PostMapping("/adduser")
    public String postAdd(@Valid Model model, User user, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "adduser";
        }
     model.addAttribute("user", user);

     userRepository.save(user);
     return "confirmuser";

    }

    // adding education

    @GetMapping("/addeducation/{id}")
    public String getEDucation(@PathVariable("id") long id, User user, Model model)
    {
        EducationClass educ =  new EducationClass();
        educ.setUser(userRepository.findOne(id));
        model.addAttribute("newedu", educ);

        return "addeducation";
    }

    @PostMapping("/addeducation")
    public String postEducation(@Valid @ModelAttribute("newedu") EducationClass edu, BindingResult bindingResult)
    {


        if(bindingResult.hasErrors())
        {
            return "addeducation";
        }
        educationRepository.save(edu);
        return "confirmeducation";
    }


    @RequestMapping("/updateED/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("userList", userRepository.findOne(id));
        model.addAttribute("newedu", educationRepository.findOne(id));
        return "addeducation";
    }
    // add experience

    @GetMapping("/addexperience/{id}")
    public String getExperience(@PathVariable("id") long id, User user, Model model)
    {
       Experience exper =  new Experience();
        exper.setUser(userRepository.findOne(id));
        model.addAttribute("newexp", exper);

        return "addexperience";
    }

    @PostMapping("/addexperience")
    public String postExperience(@Valid @ModelAttribute("newexp") Experience exp, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "addexperience";
        }
        experienceRepository.save(exp);
        return "confirmexperience";
    }


    //update experience
    @RequestMapping("/updateEx/{id}")
    public String updateExperience(@PathVariable("id") long id, Model model){

        model.addAttribute("newexp", experienceRepository.findOne(id));
        return "addexperience";
    }


    // add skills

    @GetMapping("/addskills/{id}")
    public String getSkills(@PathVariable("id") long id, User user, Model model)
    {
        SkillsClass skl =  new SkillsClass();
        skl.setUser(userRepository.findOne(id));
        model.addAttribute("newskl", skl);

        return "addskills";
    }

    @PostMapping("/addskills")
    public String postEducation(@Valid @ModelAttribute("newskl") SkillsClass skla, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "addskills";
        }
        skillRepository.save(skla);
        return "confirmskills";
    }

    @RequestMapping("/updateSkl/{id}")
    public String updateSKill(@PathVariable("id") long id, Model model){

        model.addAttribute("newskl", skillRepository.findOne(id));
        return "addskills";
    }

    // display all


    @RequestMapping("/displayall")
    public String displayAll(Model model)

    {
        model.addAttribute("allusers", userRepository.findAll());
        model.addAttribute("allskills", skillRepository.findAll());
        model.addAttribute("allexperience",experienceRepository.findAll());
        model.addAttribute("alleducation", educationRepository.findAll());

        return "displayall";
    }

    @RequestMapping("/update/{type}/{id}")
    public String update(@PathVariable("type")String type, @PathVariable("id")long id , Model model)
    {
        String output=null;
        switch(type){
            case "user":
                //stuff
                model.addAttribute("user",userRepository.findOne(id));
                //model.addAttribute("listeduc", educationRepository.findAll());
                output="adduser";
                break;
            case "educationClass":
                //stuff
                model.addAttribute("newedu",educationRepository.findOne(id));
              //  model.addAttribute("listuzer", userRepository.findAll());
               // output="addemployee";
                output="addeducation";
                break;
        }
        return output;
    }


    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("userdetail", userRepository.findOne(id));
        model.addAttribute("listeducation", educationRepository.findOne(id));
        model.addAttribute("listexperience", experienceRepository.findOne(id));
        model.addAttribute("listskills", skillRepository.findOne(id));
        return "show";
    }

}
