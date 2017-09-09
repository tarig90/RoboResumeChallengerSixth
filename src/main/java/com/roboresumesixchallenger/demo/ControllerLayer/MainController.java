package com.roboresumesixchallenger.demo.ControllerLayer;


import com.roboresumesixchallenger.demo.ModelLayer.*;

import com.roboresumesixchallenger.demo.RepositoryLayer.*;
import com.roboresumesixchallenger.demo.SecurityLayer.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.security.Principal;

@Controller
public class MainController {


//    @Autowired
//    EducationRepository educationRepository;


    @Autowired
    private UserService userService;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    JobRepository jobRepository;




    @RequestMapping("/")
    public String index(Principal principal, Model model,RoboUser roboUser)
    {









//        String username = principal.getName();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        boolean hasUserRole = authentication.getAuthorities().stream()
//                .anyMatch(r -> r.getAuthority().equals("JobSeeker"));
//
//        boolean hasRecruitrRole = authentication.getAuthorities().stream()
//                .anyMatch(r -> r.getAuthority().equals("Recruiter"));

//
//        if(hasUserRole == true)
//        {return  "jobsearch";}
//        else if(hasRecruitrRole == true)
//        {return "addjob";}
//        else {
//            return "index";
//        }

  return "index";
    }

    @RequestMapping("/login")
    public String login(Model model)
    {
        return "login";
    }


    @RequestMapping("/secure")
    public String secure()
    {
        return "secure";
    }


//    @RequestMapping(value ="/register",method = RequestMethod.GET)
//    public String showRegistrationPage(Model model)
//    {
//
//
//
//        model.addAttribute("user", new RoboUser());
//        return "registration";
//    }
//
//    @RequestMapping(value = "/register",method = RequestMethod.POST)
//    public String processRegistrationPage(@Valid @ModelAttribute("user") RoboUser user, BindingResult result, Model model, Role role)
//    {
//
//
//
//        model.addAttribute("roles", roleRepository.findAll());
//
//        if(result.hasErrors())
//        {
//            return "registration";
//        }
//
//        else if(user.getRoles().equals("Recruiter"))
//        {
//            model.addAttribute("user", user);
//            userService.saveRecruit(user);
//            return "addjob";
//
//        }
//        else
//        {
//            model.addAttribute("user", user);
//            //user.addRole(role);
//           userService.saveUser(user);
//           // userService.saveRecruit(user);
//            model.addAttribute("message", "User account successfully");
//            return "adduser";
//        }
//
//
//        //return "index";
//    }





    @RequestMapping(value = "/rgseek", method = RequestMethod.GET)
    public String showRegistrationseekerPage (Model model)
    {


        model.addAttribute("user", new RoboUser());

        return "regseek";
    }

    @RequestMapping(value = "/rgseek", method = RequestMethod.POST)
    public String processRegistrationPage (@Valid @ModelAttribute("user") RoboUser roboUser, BindingResult result, Model model)
    {
        Role r =  new Role(1,"JobSeeker");



        roleRepository.save(r);


        model.addAttribute("user", roboUser);

        if (result.hasErrors())
        {
            return "regseek";
        } else
        {
            userService.saveUser(roboUser);
            model.addAttribute("message", "Seeker Account Successfully Created.");
        }

        return "index";
    }

    @RequestMapping(value = "/rgrec", method = RequestMethod.GET)
    public String showRegistrationrecruiterPage (Model model)
    {


        model.addAttribute("user", new RoboUser());
        return "regrec";
    }

    @RequestMapping(value = "/rgrec", method = RequestMethod.POST)
    public String processRegistrationrecruiterPage (@Valid @ModelAttribute("user") RoboUser roboUser, BindingResult result, Model model)
    {


        Role s =  new Role(2,"Recruiter");


        roleRepository.save(s);

        model.addAttribute("user", roboUser);

        if (result.hasErrors())
        {
            return "regrec";
        } else
        {
            userService.saveRecruit(roboUser);
            model.addAttribute("message", "Recruiter Account Successfully Created.");
        }

        return "index";
    }


//    @RequestMapping("/")
//    public String indexPage()
//    {
//        return "index";
//    }

    // login Area
    @GetMapping("/loginpage")
    public String loginPage()
    {

             return "loginpage";
    }

//
    // adding a roboUser section
    @GetMapping("/adduser")
    public String addUser(Model model)
    {
        model.addAttribute("roboUser", new RoboUser());

        return "adduser";
    }

    @PostMapping("/adduser")
    public String postAdd(@Valid Model model, RoboUser roboUser, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "adduser";
        }
     model.addAttribute("roboUser", roboUser);

     userRepository.save(roboUser);
     return "confirmuser";

    }

    // adding education

    @GetMapping("/addeducation/{id}")
    public String getEDucation(@PathVariable("id") long id, RoboUser roboUser, Model model)
    {
        EducationClass educ =  new EducationClass();
        educ.setRoboUser(userRepository.findOne(id));
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
    public String getExperience(@PathVariable("id") long id, RoboUser roboUser, Model model)
    {
       Experience exper =  new Experience();
        exper.setRoboUser(userRepository.findOne(id));
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
    public String getSkills(@PathVariable("id") long id, RoboUser roboUser, Model model)
    {
        SkillsClass skl =  new SkillsClass();
        skl.setRoboUser(userRepository.findOne(id));
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
            case "roboUser":
                //stuff
                model.addAttribute("roboUser",userRepository.findOne(id));
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

    @RequestMapping("delete/sortof/{id}")
    public String deleteUser(@PathVariable("sortof") String sortof, @PathVariable("id") long id)
    {
        switch(sortof){

            case "roboUser":
                userRepository.delete(id);
                break;

            case "educationClass":
                educationRepository.delete(id);
                break;

            case "skillsClass":
                skillRepository.delete(id);
                break;

            case "experience":
                experienceRepository.delete(id);
                break;

        }
        return "redirect:/displayall";
    }


    // job section
    @RequestMapping("/addjob")
    public String getJobs(RoboUser roboUser, Model model, Role role)
    {
        model.addAttribute("jobs", new JobDetils());
        model.addAttribute("listSkills", skillRepository.findAll());

        return "addjob";
    }


    @PostMapping("/addjob")
    public String postJobs(@ModelAttribute("jobs") JobDetils jb,Model model)
    {
        jobRepository.save(jb);
        return "confirmjob";
    }


    @RequestMapping("/displayjobs")
    public String displayJObs(Model model)
    {

        model.addAttribute("jobs", jobRepository.findAll());
        return "displayjobs";
    }


    //request search jobs section

    @GetMapping("/searchjobs")
    public String jobsearch(Model model) {

        model.addAttribute("ljobs", new JobDetils());
        return "searchjobs";

    }

    //post search jobs section
    @PostMapping("/searchjobs")
    public String searchJobs(@ModelAttribute("searchJob") JobDetils jb, Model model)
    {
        Iterable<JobDetils> listJobs = jobRepository.findByTitle(jb.getTitle());
        model.addAttribute("ljobs", listJobs );

        return "jobresult";
    }



    //  request company search

    @GetMapping("/searchcompany")
    public String companySearch(Model model)
    {
        model.addAttribute("lcompany", new JobDetils());
        return "searchcompany";
    }


    @RequestMapping("/searchcompany")
    public String searchCompanies(@ModelAttribute("searchcompany") JobDetils jb, Model model)
    {
        Iterable<JobDetils> listCompanies = jobRepository.findByEmployerName(jb.getEmployerName());
         model.addAttribute("lcompany", listCompanies);
        return "companyresult";
    }



}
