package com.newcoder.community.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AController {

    @RequestMapping("/welcome")
    @ResponseBody
    public String Welcome(){
        return "Hello New Coders!";
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + " " + value);
        }

        response.setContentType("text/html");
        try(
            PrintWriter writer = response.getWriter();
        ){
            writer.write("<h1>New Coder</h1>");
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    // GET request
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10")int limit){
        System.out.println(current);
        System.out.println(limit);
        return "requested students";
    }

    @RequestMapping(path = "/students/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "one student by id";
    }

    // POST Request
    @RequestMapping(path = "/students", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success!";
    }

    //response for html data
    @RequestMapping(path = "/professor", method = RequestMethod.GET)
    public ModelAndView getProfessor(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","Li");
        mav.addObject("age",40);
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","UIUC");
        model.addAttribute("age",200);
        return "/demo/view";
    }

    // response for json data( async request)
    // use json to convert java object to a string that can be converted to js object by browser.
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody       // without this meaning return a html
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","Claudia");
        emp.put("age",25);
        emp.put("salary",3000.0);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody       // without this meaning return a html
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> emplist = new ArrayList<>();

        Map<String, Object> emp = new HashMap<>();
        emp.put("name","Claudia");
        emp.put("age",25);
        emp.put("salary",3000.0);

        Map<String, Object> emp1 = new HashMap<>();
        emp1.put("name","Lydia");
        emp1.put("age",23);
        emp1.put("salary",4000.0);

        emplist.add(emp);
        emplist.add(emp1);
        return emplist;
    }


}
