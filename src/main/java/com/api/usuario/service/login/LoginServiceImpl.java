package com.api.usuario.service.login;

import com.api.usuario.config.exception.UserException;
import com.api.usuario.config.exception.UserNotFoundException;
import com.api.usuario.config.security.jwt.JwtUtils;
import com.api.usuario.config.utils.PasswordValidator;
import com.api.usuario.model.dto.SignInDto;
import com.api.usuario.model.dto.UserDto;
import com.api.usuario.model.entity.User;
import com.api.usuario.model.mapper.UserMapper;
import com.api.usuario.repo.UserRepo;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepo userRepo;
    private final JwtUtils jwtGenerator;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginServiceImpl(final UserRepo userRepo,
                            final JwtUtils jwtGenerator,
                            final UserMapper userMapper,
                            final AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.jwtGenerator = jwtGenerator;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
    }

    public UserDto obtenerLogin(SignInDto sign) {
        if (sign != null) {
            if (StringUtils.isBlank(sign.getEmail()))
                throw new UserException("Correo: Requerido.");
            if (StringUtils.isBlank(sign.getPassword()))
                throw new UserException("Contraseña: Requerida.");
            if (!PasswordValidator.isValidPassword(sign.getPassword()))
                throw new UserException("La contraseña no cumple el formato adecuado. " +
                        "(Una mayuscula, letras minúsculas, y dos numeros)");
        } else
            throw new UserNotFoundException("Favor ingresar datos.");

        User found = userRepo.findByEmail(sign.getEmail()).orElse(null);

        if (found == null)
            throw new UserNotFoundException("Usuario no encontrado.");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(sign.getEmail(), sign.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        found.setToken(jwtGenerator.generateToken(authentication));
        found.setLastLogin(new Date());

        return userMapper.userToUserDto(userRepo.save(found));
    }
}
