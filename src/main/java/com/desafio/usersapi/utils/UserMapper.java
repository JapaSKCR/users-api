package com.desafio.usersapi.utils;


import com.desafio.usersapi.dto.UserDTO;
import com.desafio.usersapi.entity.User;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd/MM/yyyy")
    User toModel(UserDTO userDTO);

    UserDTO toDto(User user);







}
