package com.chuan.jpa05.models.mappers.mapstructs;

import com.chuan.jpa05.models.dtos.FoodTypeDTO;
import com.chuan.jpa05.models.entities.FoodType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodTypeMapper {
    FoodTypeMapper INSTANCE = Mappers.getMapper(FoodTypeMapper.class);

    FoodType toEntity(FoodTypeDTO dto);

    FoodTypeDTO toDTO(FoodType entity);
}
