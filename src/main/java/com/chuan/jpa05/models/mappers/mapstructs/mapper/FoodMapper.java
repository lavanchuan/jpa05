package com.chuan.jpa05.models.mappers.mapstructs.mapper;

import com.chuan.jpa05.exceptions.FoodTypeNotFoundException;
import com.chuan.jpa05.models.dtos.FoodDTO;
import com.chuan.jpa05.models.entities.Food;
import com.chuan.jpa05.repositories.contexts.DbContext;

public class FoodMapper {
    public static FoodDTO entityToDTO(Food entity){
        FoodDTO foodDTO = com.chuan.jpa05.models.mappers.mapstructs.FoodMapper.INSTANCE.toDTO(entity);
        foodDTO.setFtId(entity.getFoodType().getFtId());
        return foodDTO;
    }

    public static Food dtoToEntity(FoodDTO dto, DbContext dbContext){
        Food entity = com.chuan.jpa05.models.mappers.mapstructs.FoodMapper.INSTANCE.toEntity(dto);
        entity.setFoodType(dbContext.foodTypeRepository.findById(dto.getFtId())
                .orElseThrow(()->new FoodTypeNotFoundException(dto.getFtId())));
        return entity;
    }


}
