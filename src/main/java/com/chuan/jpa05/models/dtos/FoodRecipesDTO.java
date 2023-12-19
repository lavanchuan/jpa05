package com.chuan.jpa05.models.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

public class FoodRecipesDTO implements Serializable {

    @NotNull
    private FoodDTO foodDTO;
    private List<RecipeDTO> recipeDTOS;

    public FoodDTO getFoodDTO() {
        return foodDTO;
    }

    public FoodRecipesDTO setFoodDTO(FoodDTO foodDTO) {
        this.foodDTO = foodDTO;
        return this;
    }

    public List<RecipeDTO> getRecipeDTOS() {
        return recipeDTOS;
    }

    public FoodRecipesDTO setRecipeDTOS(List<RecipeDTO> recipeDTOS) {
        this.recipeDTOS = recipeDTOS;
        return this;
    }
}
