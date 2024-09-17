package com.api.usuario.service;

import com.api.usuario.config.exception.UserException;
import com.api.usuario.config.security.jwt.JwtUtils;
import com.api.usuario.config.utils.PasswordValidator;
import com.api.usuario.model.dto.UserDto;
import com.api.usuario.model.entity.User;
import com.api.usuario.model.mapper.UserMapper;
import com.api.usuario.repo.UserRepo;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final JwtUtils jwtGenerator;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(final UserRepo userRepo,
                           final JwtUtils jwtGenerator,
                           final UserMapper userMapper,
                           final PasswordEncoder passwordEncoder,
                           final AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.jwtGenerator = jwtGenerator;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public List<UserDto> obtenerUsers() {
        return userMapper.map(userRepo.findAll());
    }

    public UserDto saveUser(UserDto userDto) {

        if (!EmailValidator.getInstance().isValid(userDto.getEmail())) {
            throw new UserException("Favor ingresar un correo válido.");
        }
        if (!PasswordValidator.isValidPassword(userDto.getPassword()))
            throw new UserException("La contraseña no cumple el formato adecuado. " +
                    "(Una mayuscula, letras minúsculas, y dos numeros)");


        boolean existe = obtenerUsers().stream().anyMatch(
                x -> x.getEmail().equalsIgnoreCase(userDto.getEmail()));
        if (existe) {
            throw new UserException("El correo ingresado ya se encuentra registrado.");
        }

        User user = userMapper.userDtoToUser(userDto);

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        if (user.getId() == null) {
            user.setCreated(new Date());
            user.setActive(true);
        } else
            user.setModified(new Date());

        if (!user.getPhones().isEmpty()) {
            user.getPhones().forEach(phone -> phone.setUser(user));
        }

        return userMapper.userToUserDto(userRepo.save(user));
    }
}
