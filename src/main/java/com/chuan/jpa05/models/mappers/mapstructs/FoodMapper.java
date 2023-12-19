package com.chuan.jpa05.models.mappers.mapstructs;

import com.chuan.jpa05.models.dtos.FoodDTO;
import com.chuan.jpa05.models.entities.Food;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Mapper
@Service
public interface FoodMapper {
    FoodMapper INSTANCE = Mappers.getMapper(FoodMapper.class);

    FoodDTO toDTO(Food food);
    Food toEntity(FoodDTO foodDTO);
}
