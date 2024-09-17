package com.api.usuario.service;

import com.api.usuario.model.dto.UserDto;
import com.api.usuario.model.dto.UserNewDto;

import java.util.List;

public interface UserService {

    List<UserDto> obtenerUsers();

    UserDto saveUser(UserNewDto userNewDto);

}
