package com.springlessons.nonreactive.mapper;

public interface Mapper<Entity, Dto> {
    Dto toDto(Entity entity);
    Entity toEntity(Dto dto);
}
