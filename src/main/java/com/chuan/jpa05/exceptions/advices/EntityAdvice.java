package com.chuan.jpa05.exceptions.advices;

import com.chuan.jpa05.exceptions.FoodNotFoundException;
import com.chuan.jpa05.exceptions.FoodTypeNotFoundException;
import com.chuan.jpa05.exceptions.MeterialNotFoundException;
import com.chuan.jpa05.exceptions.RecipeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class EntityAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FoodNotFoundException.class)
    public String foodNotFoundHandler(FoodNotFoundException e){
        return e.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FoodTypeNotFoundException.class)
    public String foodTyoeNotFoundHandler(FoodTypeNotFoundException e){
        return e.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MeterialNotFoundException.class)
    public String meterialNotFoundHandler(MeterialNotFoundException e){
        return e.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecipeNotFoundException.class)
    public String recipeNotFoundHandler(RecipeNotFoundException e){
        return e.getMessage();
    }
}
