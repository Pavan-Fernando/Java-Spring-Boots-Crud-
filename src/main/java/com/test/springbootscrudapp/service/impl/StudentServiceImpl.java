package com.test.springbootscrudapp.service.impl;

import com.test.springbootscrudapp.model.Student;
import com.test.springbootscrudapp.model.StudentNotFoundException;
import com.test.springbootscrudapp.model.StudentRepository;
import com.test.springbootscrudapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> studentList(){
        return (List<Student>)  studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student get(Integer id) throws StudentNotFoundException {
      Optional<Student> result = studentRepository.findById(id);
      if (result.isPresent()){
          return result.get();
      }
      throw new StudentNotFoundException("Could not find any users with ID" + id);
    }

    @Override
    public void deleteStudent(Integer id) throws StudentNotFoundException {
        Long count = studentRepository.countById(id);
        if(count == null || count == 0){
            throw new StudentNotFoundException("Could not find any users with ID" + id);
        }
        studentRepository.deleteById(id);
    }
}
