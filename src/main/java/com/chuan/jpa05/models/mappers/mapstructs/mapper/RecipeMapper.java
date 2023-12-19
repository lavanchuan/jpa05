package com.chuan.jpa05.models.mappers.mapstructs.mapper;

import com.chuan.jpa05.exceptions.FoodTypeNotFoundException;
import com.chuan.jpa05.exceptions.MeterialNotFoundException;
import com.chuan.jpa05.models.dtos.RecipeDTO;
import com.chuan.jpa05.models.entities.Recipe;
import com.chuan.jpa05.repositories.contexts.DbContext;

public class RecipeMapper {

    public static Recipe dtoToEntity(RecipeDTO dto, DbContext dbContext){
        Recipe entity = com.chuan.jpa05.models.mappers.mapstructs.RecipeMapper.INSTANCE.recipeDTOToRecipe(dto);
        entity.setFood(dbContext.foodRepository.findById(dto.getfId()).orElseThrow(()->new FoodTypeNotFoundException(dto.getfId())));
        entity.setMeterial(dbContext.meterialRepository.findById(dto.getmId()).orElseThrow(()->new MeterialNotFoundException(dto.getmId())));
        return entity;
    }

    public static RecipeDTO entityToDTO(Recipe entity){
        RecipeDTO dto = com.chuan.jpa05.models.mappers.mapstructs.RecipeMapper.INSTANCE.recipeToRecipeDTO(entity);
        dto.setfId(entity.getFood().getfId());
        dto.setmId(entity.getMeterial().getmId());
        return dto;
    }

}
