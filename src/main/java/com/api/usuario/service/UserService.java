package com.api.usuario.service;

import com.api.usuario.model.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> obtenerUsers();

    UserDto saveUser(UserDto userDto);

}
