package com.examly.springapp.exceptions;


public class PlanAlreadyExistException extends RuntimeException{
    
    public PlanAlreadyExistException(String s){
        super(s);
    }
}