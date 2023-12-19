package com.chuan.jpa05.exceptions;

public class FoodNotFoundException extends RuntimeException{
    public FoodNotFoundException(int id){
        super("Cound not found food: " + id);
    }
}
