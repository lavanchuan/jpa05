package com.chuan.jpa05.exceptions;

public class FoodTypeNotFoundException extends RuntimeException{
    public FoodTypeNotFoundException(int id){
        super("Could not found food type: " + id);
    }
}
