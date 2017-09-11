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
import java.util.ArrayList;

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
    public String index(Principal p, Model model) {



        RoboUser roboUser = userRepository.findByUsername(p.getName());
//        String username = principal.getName();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        boolean hasUserRole = authentication.getAuthorities().stream()
//                .anyMatch(r -> r.getAuthority().equals("JobSeeker"));
//
//        boolean hasRecruitrRole = authentication.getAuthorities().stream()
//                .anyMatch(r -> r.getAuthority().equals("Recruiter"));
//
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

    @RequestMapping("/loginpage")
    public String login(Model model) {
        return "loginpage";
    }


    @RequestMapping("/secure")
    public String secure(Principal principal, Model model) {

//        Person pperson = personRepo.findByUsername(p.getName());
//        String username = principal.getName();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        boolean hasUserRole = authentication.getAuthorities().stream()
//                .anyMatch(r -> r.getAuthority().equals("JobSeeker"));
//
//        boolean hasRecruitrRole = authentication.getAuthorities().stream()
//                .anyMatch(r -> r.getAuthority().equals("Recruiter"));
//
//
//        if(hasUserRole == true)
//        {return  "addjob";}
//        else if(hasRecruitrRole == true)
//        {return "displayall";}
//        else {
//            return "index";
//        }


        //in oder to use old route, will fix later
//        model.addAttribute("newperson", userRepository.findByUsername(principal.getName()));
//
//        // if the person has a role of jobseek, check if there are jobs matches the skill, and send out notification!!!!
//        if (userRepository.findByUsername(principal.getName()).getRoles().iterator().next().getRole().equalsIgnoreCase("JobSeeker")) {
//
////
//           // System.out.println("---------role:" + personRepo.findByUsername(p.getName()).getRoles().iterator().next().getRoleName());
//
//            RoboUser roboUser1 = userService.finByUsername(principal.getName());
//
//            ArrayList<Job> matchingJob =  new ArrayList<Job>();
//
//            if (person.getSkills().isEmpty())
//            {
//                model.addAttribute("message", "Welcome to our Resume database. Please enter your skills to your resume, and we will check for the matching job for you!");
//                model.addAttribute("matchingJob", matchingJob);
//            }



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
    public String showRegistrationseekerPage(Model model) {


        model.addAttribute("user", new RoboUser());

        return "regseek";
    }

    @RequestMapping(value = "/rgseek", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") RoboUser roboUser, BindingResult result, Model model) {
        Role r = new Role(1, "JobSeeker");


        roleRepository.save(r);


        model.addAttribute("user", roboUser);

        if (result.hasErrors()) {
            return "regseek";
        } else {
            userService.saveUser(roboUser);
            model.addAttribute("message", "Seeker Account Successfully Created.");
        }

        return "index";
    }

    @RequestMapping(value = "/rgrec", method = RequestMethod.GET)
    public String showRegistrationrecruiterPage(Model model) {


        model.addAttribute("user", new RoboUser());
        return "regrec";
    }

    @RequestMapping(value = "/rgrec", method = RequestMethod.POST)
    public String processRegistrationrecruiterPage(@Valid @ModelAttribute("user") RoboUser roboUser, BindingResult result, Model model) {


        Role s = new Role(2, "Recruiter");


        roleRepository.save(s);

        model.addAttribute("user", roboUser);

        if (result.hasErrors()) {
            return "regrec";
        } else {
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
    public String loginPage() {

        return "loginpage";
    }

    //
    // adding a roboUser section
    @GetMapping("/adduser")
    public String addUser(Model model) {
        model.addAttribute("roboUser", new RoboUser());

        return "adduser";
    }

    @PostMapping("/adduser")
    public String postAdd(@Valid Model model, RoboUser roboUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "adduser";
        }
        model.addAttribute("roboUser", roboUser);

        userRepository.save(roboUser);
        return "confirmuser";

    }

    // adding education

    @GetMapping("/addeducation/{id}")
    public String getEDucation(@PathVariable("id") long id, RoboUser roboUser, Model model) {
        EducationClass educ = new EducationClass();
        educ.setRoboUser(userRepository.findOne(id));
        model.addAttribute("newedu", educ);

        return "addeducation";
    }

    @PostMapping("/addeducation")
    public String postEducation(@Valid @ModelAttribute("newedu") EducationClass edu, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "addeducation";
        }
        educationRepository.save(edu);
        return "confirmeducation";
    }


    @RequestMapping("/updateED/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model) {
        model.addAttribute("userList", userRepository.findOne(id));
        model.addAttribute("newedu", educationRepository.findOne(id));
        return "addeducation";
    }
    // add experience

    @GetMapping("/addexperience/{id}")
    public String getExperience(@PathVariable("id") long id, RoboUser roboUser, Model model) {
        Experience exper = new Experience();
        exper.setRoboUser(userRepository.findOne(id));
        model.addAttribute("newexp", exper);

        return "addexperience";
    }

    @PostMapping("/addexperience")
    public String postExperience(@Valid @ModelAttribute("newexp") Experience exp, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addexperience";
        }
        experienceRepository.save(exp);
        return "confirmexperience";
    }


    //update experience
    @RequestMapping("/updateEx/{id}")
    public String updateExperience(@PathVariable("id") long id, Model model) {

        model.addAttribute("newexp", experienceRepository.findOne(id));
        return "addexperience";
    }


    // add skills

    @GetMapping("/addskills/{id}")
    public String getSkills(@PathVariable("id") long id, RoboUser roboUser, Model model) {
        SkillsClass skl = new SkillsClass();
        skl.setRoboUser(userRepository.findOne(id));
        model.addAttribute("newskl", skl);

        return "addskills";
    }

    @PostMapping("/addskills")
    public String postEducation(@Valid @ModelAttribute("newskl") SkillsClass skla, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addskills";
        }
        skillRepository.save(skla);
        return "confirmskills";
    }

    @RequestMapping("/updateSkl/{id}")
    public String updateSKill(@PathVariable("id") long id, Model model) {

        model.addAttribute("newskl", skillRepository.findOne(id));
        return "addskills";
    }

    // display all


    @RequestMapping("/displayall")
    public String displayAll(Model model)

    {
        model.addAttribute("allusers", userRepository.findAll());
        model.addAttribute("allskills", skillRepository.findAll());
        model.addAttribute("allexperience", experienceRepository.findAll());
        model.addAttribute("alleducation", educationRepository.findAll());

        return "displayall";
    }

    @RequestMapping("/update/{type}/{id}")
    public String update(@PathVariable("type") String type, @PathVariable("id") long id, Model model) {
        String output = null;
        switch (type) {
            case "roboUser":
                //stuff
                model.addAttribute("roboUser", userRepository.findOne(id));
                //model.addAttribute("listeduc", educationRepository.findAll());
                output = "adduser";
                break;
            case "educationClass":
                //stuff
                model.addAttribute("newedu", educationRepository.findOne(id));
                //  model.addAttribute("listuzer", userRepository.findAll());
                // output="addemployee";
                output = "addeducation";
                break;
        }
        return output;
    }


    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model) {
        model.addAttribute("userdetail", userRepository.findOne(id));
        model.addAttribute("listeducation", educationRepository.findOne(id));
        model.addAttribute("listexperience", experienceRepository.findOne(id));
        model.addAttribute("listskills", skillRepository.findOne(id));
        return "show";
    }

    @RequestMapping("delete/sortof/{id}")
    public String deleteUser(@PathVariable("sortof") String sortof, @PathVariable("id") long id) {
        switch (sortof) {

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
    @GetMapping("/addjob")
    public String getJobs(RoboUser roboUser, Model model, Role role, Principal p) {


        String username =  p.getName();
        JobDetils j =  new JobDetils();
        j.setRb(userRepository.findByUsername(username));


        SkillsClass skillsz = new SkillsClass();
        skillsz.setSkillOne("Select skill");
        skillRepository.save(skillsz);
        model.addAttribute("jobs", new JobDetils());
        model.addAttribute("listSkills", skillRepository.findAll());

        return "addjob";
    }


    @PostMapping("/addjob")
    public String postJobs(@ModelAttribute("id") RoboUser roboUser, Model model, JobDetils jb) {


        model.addAttribute("jobs", jb);
        jobRepository.save(jb);
        return "confirmjob";
    }


    @RequestMapping("/displayjobs")
    public String displayJObs(Model model) {

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
    public String searchJobs(@ModelAttribute("searchJob") JobDetils jb, Model model) {
        Iterable<JobDetils> listJobs = jobRepository.findByTitle(jb.getTitle());
        model.addAttribute("ljobs", listJobs);

        return "jobresult";
    }


    //  request company search

    @GetMapping("/searchcompany")
    public String companySearch(Model model, JobDetils j) {
        model.addAttribute("lcompany", j);
        return "searchcompany";
    }


    @RequestMapping("/searchcompany")
    public String searchCompanies(@ModelAttribute("searchcompany") JobDetils jb, Model model) {
        Iterable<JobDetils> listCompanies = jobRepository.findByEmployerName(jb.getEmployerName());
        model.addAttribute("lcompany", listCompanies);
        return "companyresult";
    }

    // search for people
    @GetMapping("/searchpeople")
    public String companyPeople(Model model, RoboUser j) {
        model.addAttribute("peepz", j);
        return "searchpeople";
    }


    @RequestMapping("/searchpeople")
    public String searchPeoples(@ModelAttribute("peepz") RoboUser roboUser, Model model) {
        Iterable<RoboUser> listpeople = userRepository.findAllByUsername(roboUser.getUsername());
        model.addAttribute("peepz", listpeople);
        return "peopleresult";
    }

    // search education

    @GetMapping("/searchschool")
    public String searcheducation(Model model, EducationClass edu) {
        model.addAttribute("edu", edu);
        return "searchschool";
    }


    @RequestMapping("/searchschool")
    public String postsearchschool(@ModelAttribute("edu") EducationClass edu, Model model) {
        Iterable<EducationClass> listschool = educationRepository.findAllBySchoolName(edu.getSchoolName());
        model.addAttribute("edu", edu);
        return "schoolresults";
    }


    @GetMapping("/matchnotify")
    public String matchAndNotify(Principal p, Model model)
    {

        //in oder to use old route, will fix later
        model.addAttribute("newperson", userRepository.findByUsername(p.getName()));

        // if the person has a role of jobseek, check if there are jobs matches the skill, and send out notification!!!!
        if (userRepository.findByUsername(p.getName()).getRoles().iterator().next().getRole().equalsIgnoreCase("JobSeeker")) {

            System.out.println("------" + p.getName());

           // System.out.println("---------role:" + userRepository.findByUsername(p.getName()).getRoles().iterator().next().getUsers());

            RoboUser rbuser = userService.finByUsername(p.getName());

            ArrayList<JobDetils> matchingJob =  new ArrayList<JobDetils>();

            if (rbuser.getSkillClass().isEmpty())
            {
                model.addAttribute("message", "Welcome to our Resume database. Please enter your skills to your resume, and we will check for the matching job for you!");
                model.addAttribute("matchingJob", matchingJob);
            }

            else{

                for (SkillsClass s : rbuser.getSkillClass()) {

                    if (jobRepository.findAllBySkil(s.getSkillOne()) != null) {
                        ArrayList<JobDetils> alljobs = (ArrayList<JobDetils>) jobRepository.findAllBySkil(s.getSkillOne());

                        for(JobDetils item:alljobs)
                        {
                            matchingJob.add(item);
                        }

                    }
                }
                if (matchingJob.isEmpty())
                {
                    model.addAttribute("message", "No job matches your the skill you have, come back later!");
                    model.addAttribute("matchingJob", matchingJob);
                }
                else{
                    model.addAttribute("message", "We find some job postings that match your skill");
                    model.addAttribute("matchingJob", matchingJob);

                }
            }

            return "notificationS";
        }

        //for recruiter, will have a notification method like the above later!
        else {
            return "index";
        }
    }

    // serach for education
//    @GetMapping("/searchschool")
//    public String companySchool(Model model, RoboUser j)
//    {
//        model.addAttribute("lpeepz", j);
//        return "searchSchool";
//    }
//
//
//    @RequestMapping("/searchschool")
//    public String searchPeoples(@ModelAttribute("searchschool") EducationClass edu, Model model)
//    {
//        Iterable<EducationClass> listedu = educationRepository.findBySchoolName(edu.getSchoolName());
//        model.addAttribute("lcompany", edu);
//        return "companyresult";
//    }

    // add skills to user

    @GetMapping("/addskilltojob/{id}")
    public String addMovie(@PathVariable("id") long jobID, Model model) {
      model.addAttribute("job", jobRepository.findOne(new Long(jobID)));
       model.addAttribute("skillList", skillRepository.findAll());

        //model.addAttribute("job", jobRepository.findOne(new Long(jobID)))
        return "addskilltojob";
    }

    @PostMapping("/addskilltojob/{movid}")
    public String addSKillToJOb(@RequestParam("skills") String skillId, @PathVariable("jobid") long jobId,  @ModelAttribute("skill") SkillsClass a, Model model)
    {

        SkillsClass s =  new SkillsClass();
//        System.out.println("Actor ID"+actorID);
//        System.out.println("Movie ID"+movieID);
        SkillsClass m = skillRepository.findOne(new Long(skillId));

        m.AddJOb(jobRepository.findOne(new Long(jobId)));
       // m.addActor(actorRepository.findOne(new Long(actorID)));
        skillRepository.save(m);

        model.addAttribute("jobList",jobRepository.findAll());
        model.addAttribute("skillList",skillRepository.findAll());
        //System.out.println("Actor ID from anActor"+a.getId());
        return "redirect:/";
    }
}




