package com.nexturn.manytomanydemo.exception;

import java.text.MessageFormat;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(Long id){
        super(MessageFormat.format("Project {0} is Not Found", id));
    }
}
