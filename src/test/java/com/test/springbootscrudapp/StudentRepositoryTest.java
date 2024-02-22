package com.test.springbootscrudapp;

import com.test.springbootscrudapp.model.Student;
import com.test.springbootscrudapp.model.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository repository;

    @Test
    public void testAddStudent(){
        Student student = new Student();
        student.setName("chandima");
        student.setEmail("chandima@gmail.com");
        student.setPhone("02478523698");
        student.setAddress("Wadduwa");
        student.setAge(24);

        Student saveStudent = repository.save(student);

        Assertions.assertThat(saveStudent).isNotNull();
        Assertions.assertThat(saveStudent.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<Student> students = repository.findAll();
        Assertions.assertThat(students).hasSizeGreaterThan(0);

        for (Student student : students){
            System.out.println(student);
        }
    }
    @Test
    public void testUpdate(){
        Integer studentId = 1;
        Optional<Student> optionalStudent = repository.findById(studentId);
        Student student = optionalStudent.get();
        student.setAge(41);
        repository.save(student);

        Student updateStudent = repository.findById(studentId).get();
        Assertions.assertThat(updateStudent.getAge()).isEqualTo(41);
    }
    @Test
    public void testGet(){
        Integer studentId = 2;
        Optional<Student> optionalStudent = repository.findById(studentId);
        Assertions.assertThat(optionalStudent).isPresent();
        System.out.println(optionalStudent.get());
    }

    @Test
    public void testDelete() {
        Integer studentId = 6;
        repository.deleteById(studentId);
        Optional<Student> optionalStudent = repository.findById(studentId);
        Assertions.assertThat(optionalStudent).isNotPresent();
    }
}
