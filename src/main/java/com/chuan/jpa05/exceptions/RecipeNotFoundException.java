package com.chuan.jpa05.exceptions;

public class RecipeNotFoundException extends RuntimeException{
    public RecipeNotFoundException(int id){
        super("Could not found recipe: " + id);
    }
}
