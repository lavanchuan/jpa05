package com.chuan.jpa05.controllers;

import com.chuan.jpa05.exceptions.RecipeNotFoundException;
import com.chuan.jpa05.models.dtos.RecipeDTO;
import com.chuan.jpa05.models.entities.Recipe;
import com.chuan.jpa05.models.mappers.mapstructs.mapper.RecipeMapper;
import com.chuan.jpa05.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    @Autowired
    FoodService foodService;

    // GET
    @GetMapping("/find/all")
    public ResponseEntity<?> findRecipeAll(){
        List<RecipeDTO> dtos = new ArrayList<>();
        for(Recipe entity : foodService.dbContext.recipeRepository.findAll()){
            dtos.add(RecipeMapper.entityToDTO(entity));
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findRecipeById(@PathVariable int id){
        return ResponseEntity.ok(
                RecipeMapper.entityToDTO(
                        foodService.dbContext.recipeRepository.findById(id)
                                .orElseThrow(()->new RecipeNotFoundException(id))
                )
        );
    }

    // POST
    @PostMapping("/adds/{fId}")
    public ResponseEntity<?> addRecipes(@RequestBody List<RecipeDTO> dtos, @PathVariable int fId){
        return foodService.addRecipes(dtos, fId);
    }
}
