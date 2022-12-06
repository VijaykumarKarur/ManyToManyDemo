package com.nexturn.manytomanydemo.exception;

import java.text.MessageFormat;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(Long id){
        super(MessageFormat.format("Employee {0} is Not Found", id));
    }
}
