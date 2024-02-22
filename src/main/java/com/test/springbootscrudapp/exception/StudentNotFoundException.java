package com.test.springbootscrudapp.exception;

public class StudentNotFoundException extends Throwable {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
