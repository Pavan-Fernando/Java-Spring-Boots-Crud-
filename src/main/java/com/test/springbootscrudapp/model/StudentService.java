package com.test.springbootscrudapp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> studentList(){
        return (List<Student>)  studentRepository.findAll();
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public Student get(Integer id) throws StudentNotFoundException {
      Optional<Student> result = studentRepository.findById(id);
      if (result.isPresent()){
          return result.get();
      }
      throw new StudentNotFoundException("Could not find any users with ID" + id);
    }

    public void deleteStudent(Integer id) throws StudentNotFoundException {
        Long count = studentRepository.countById(id);
        if(count == null || count == 0){
            throw new StudentNotFoundException("Could not find any users with ID" + id);
        }
        studentRepository.deleteById(id);
    }
}
