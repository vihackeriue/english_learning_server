package com.example.english_learning_server.converter;


import com.example.english_learning_server.dto.BaseDTO;
import com.example.english_learning_server.entity.BaseEntity;

public abstract class BaseConverter<DTO extends BaseDTO, Entity extends BaseEntity> {
    public void convertToDTOBase(final DTO dto, final Entity entity ){
        dto.setCreateDate(entity.getCreateDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setUpdatedBy(entity.getUpdatedBy());
    }

    public void convertToEntityBase(final DTO dto, final Entity entity ){
        entity.setCreateDate(dto.getCreateDate());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedDate(dto.getUpdatedDate());
        entity.setUpdatedBy(dto.getUpdatedBy());
    }
    public abstract DTO toDTO(Entity entity);
    public abstract Entity toEntity(DTO dto);

    public abstract Entity toEntity(DTO dto, Entity entity);

}
