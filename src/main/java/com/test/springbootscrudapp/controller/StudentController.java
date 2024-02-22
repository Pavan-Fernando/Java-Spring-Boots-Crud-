package com.test.springbootscrudapp.controller;

import com.test.springbootscrudapp.model.Student;
import com.test.springbootscrudapp.model.StudentNotFoundException;
import com.test.springbootscrudapp.model.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {

    @GetMapping("")
    public String showHomePage(){
        return "index";
    }

    @Autowired
    private StudentService studentService;

    @GetMapping("/studentsDetails")
    public String showStudentList(Model model){
        List<Student> studentsList = studentService.studentList();
        model.addAttribute("studentList", studentsList);
        return "studentsDetails";
    }

    @GetMapping("/addstudent")
    public String addNewStudent(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("pageTitle", "Add New Student");
        return "addNewStudent";
    }
    @PostMapping("/add/student")
    public String addStudent(Student student){
        studentService.save(student);

        return "redirect:/api/v/student/studentsDetails";
    }

    @GetMapping("/student/edit/{id}")
    public  String studentEdit(@PathVariable("id") Integer id, Model model){
        try {
            Student student = studentService.get(id);
            model.addAttribute("student", student);
            model.addAttribute("pageTitle", "Edit Student (ID: " +id+ ")");
            return "addNewStudent";
        }catch (StudentNotFoundException e){
            return "redirect:/api/v/student/studentsDetails";
        }
    }

    @GetMapping("/student/delete/{id}")
    public  String deleteStudent(@PathVariable("id") Integer id){
        try {
            studentService.deleteStudent(id);
            System.out.println("Successfully deleted!");
        }catch (StudentNotFoundException e){
            e.printStackTrace();
        }
        return "redirect:/api/v/student/studentsDetails";
    }

}
