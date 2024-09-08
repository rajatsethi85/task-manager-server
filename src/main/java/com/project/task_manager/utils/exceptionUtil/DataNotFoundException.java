package com.project.task_manager.utils.exceptionUtil;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(String message) {
        super(message);
    }
}
