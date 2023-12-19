package com.chuan.jpa05.models.mappers.mapstructs;

import com.chuan.jpa05.models.dtos.RecipeDTO;
import com.chuan.jpa05.models.entities.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeMapper {

    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    Recipe recipeDTOToRecipe(RecipeDTO recipeDTO);

    RecipeDTO recipeToRecipeDTO(Recipe recipe);
}
