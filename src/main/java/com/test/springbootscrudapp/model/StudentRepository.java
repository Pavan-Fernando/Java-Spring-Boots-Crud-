package com.test.springbootscrudapp.model;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    public  Long countById(Integer id);
}
