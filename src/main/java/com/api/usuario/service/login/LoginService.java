package com.api.usuario.service.login;

import com.api.usuario.model.dto.SignInDto;
import com.api.usuario.model.dto.UserDto;

public interface LoginService {

    UserDto obtenerLogin(SignInDto sign);
}
