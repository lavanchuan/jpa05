package com.chuan.jpa05.models.mappers.mapstructs;

import com.chuan.jpa05.models.dtos.MeterialDTO;
import com.chuan.jpa05.models.entities.Meterial;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MeterialMapper {
    MeterialMapper INSTANCE = Mappers.getMapper(MeterialMapper.class);

    Meterial toEntity(MeterialDTO dto);

    MeterialDTO toDTO(Meterial entity);
}
