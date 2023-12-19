package com.chuan.jpa05.services;

import com.chuan.jpa05.models.dtos.FoodDTO;
import com.chuan.jpa05.models.dtos.FoodRecipesDTO;
import com.chuan.jpa05.models.dtos.RecipeDTO;
import com.chuan.jpa05.models.entities.Food;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IFoodService {
    ResponseEntity<?> addFood(FoodDTO foodDTO);
    ResponseEntity<?> addRecipe(RecipeDTO recipeDTO);
    ResponseEntity<?> addRecipes(List<RecipeDTO> dtos, int fId);
    ResponseEntity<?> findFoodByNameAndMeterial(String fName, String mName);

    ResponseEntity<?> addFoodRecipes(@Valid FoodRecipesDTO foodRecipesDTO);

    ResponseEntity<?> findFoodRecipes(int fId);
    ResponseEntity<?> findFoodByMNames(String condition);

}
