package com.test.springbootscrudapp.service;

import com.test.springbootscrudapp.model.Student;
import com.test.springbootscrudapp.exception.StudentNotFoundException;

import java.util.List;

public interface StudentService {

    List<Student> studentList();
    void save(Student student);
    Student get(Integer id) throws StudentNotFoundException;
    void deleteStudent(Integer id) throws StudentNotFoundException;
}
