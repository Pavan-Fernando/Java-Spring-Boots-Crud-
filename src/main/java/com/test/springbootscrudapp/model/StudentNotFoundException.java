package com.test.springbootscrudapp.model;

public class StudentNotFoundException extends Throwable {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
