package com.directory.demo.ControllerLayer;


import com.directory.demo.ModelLayer.Department;
import com.directory.demo.ModelLayer.Employee;
import com.directory.demo.ModelLayer.Teams;
import com.directory.demo.RepositoryLayer.DepartmentRepository;
import com.directory.demo.RepositoryLayer.EmployeeRepository;
import com.directory.demo.RepositoryLayer.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.management.MalformedObjectNameException;
import javax.validation.Valid;

@Controller
public class MainController {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TeamRepository teamRepository;


    @RequestMapping("/")
    public String indexPage() {
//
//        departmentRepository.save(new Department("Operations"));  //issue with id?
//        departmentRepository.save(new Department("Accounting"));
//        departmentRepository.save(new Department("Human Resources"));
//
//        Department dep=new Department();
//        dep.setName("a department name");
//        departmentRepository.save(dep);
        return "index";
    }

    // login Area
    @GetMapping("/loginpg")
    public String loginPage()

    {

        return "loginpg";
    }

//    input form for employees
    @GetMapping("/addemployee")
    public String showEmployee(Model model) {



        if(teamRepository.count()<1)
        {
            Teams teams = new Teams();
            teams.setName("Select Team");
            teamRepository.save(teams);

        }
        model.addAttribute("newemp", new Employee());
        model.addAttribute("listdpt", departmentRepository.findAll());
        model.addAttribute("listteam", teamRepository.findAll());

        return "addemployee";
    }

//    Accepts employee form input, saves to repository, then displays confirmation
    @PostMapping("/addemployee")
    public String getEmployee(@RequestParam("Teams")Long teamId, Employee emp, BindingResult result,Model model) {


        emp.addTeam(teamRepository.findOne(teamId));

        model.addAttribute("emp",emp);
        employeeRepository.save(emp);
        return "confirmemp";
    }


    @GetMapping("/addDept")
    public String getDept(Model model) {

        model.addAttribute("newdpt", new Department());
        model.addAttribute("listemp", employeeRepository.findAll());

        return "addDept";
    }

    //    Accepts employee form input, saves to repository, then displays confirmation
    @PostMapping("/addDept")
    public String postDept(@Valid Department dpt, BindingResult result,Model model) {
//        if (result.hasErrors()){
//            return "addemployee";
//        }
        model.addAttribute("newdpt", dpt);
        departmentRepository.save(dpt);
        return "confirmdpt";
    }

    @RequestMapping("/update/{type}/{id}")
    public String update(@PathVariable("type")String type, @PathVariable("id")long id , Model model)
    {
        String output=null;
        switch(type){
            case "department":
                //stuff
                model.addAttribute("newdpt",departmentRepository.findOne(id));
                model.addAttribute("listemp", employeeRepository.findAll());
                output="addDept";
                break;
            case "employee":
                //stuff
                model.addAttribute("newemp",employeeRepository.findOne(id));
                model.addAttribute("listdpt", departmentRepository.findAll());
                output="addemployee";
                break;
        }
        return output;
    }

    @RequestMapping("/delete/{type}/{id}")
    public String delete(@PathVariable("type")String type, @PathVariable("id")long id)
    {
        switch(type){
            case "department":
                //stuff
                System.out.println("Deleting" + departmentRepository.findOne(id));
                departmentRepository.delete(id);

                break;
            case "employee":
                //stuff
                Employee tempEmployee = employeeRepository.findOne(id);
                Department tempDepartment = tempEmployee.getDepartment();
                tempDepartment.removeEmployee(tempEmployee);
                tempEmployee.setDepartment(null);

                System.out.println("Deleting" + tempEmployee);
                employeeRepository.delete(id);
                break;
        }
        return "redirect:/displayall";
    }

    @RequestMapping("/displayall")
    public String displayAll(Model model)

    {
        model.addAttribute("allDepartment", departmentRepository.findAll());
        model.addAttribute("allEmployee", employeeRepository.findAll());

        return "displayall";
    }

    // create team controller section

    @RequestMapping("/addteam")
    public String teamGet(Model model)
    {
     model.addAttribute("newteam", new Teams());

     return "addteam";

    }

    @PostMapping("/addteam")
     public String  addteampost(Model model, Teams teams)
    {

        model.addAttribute("newteam",  teams );
        teamRepository.save(teams);
        return "confirmteam";
    }


}
