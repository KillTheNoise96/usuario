package com.api.usuario.units;

import com.api.usuario.config.security.jwt.JwtUtils;
import com.api.usuario.model.dto.SignInDto;
import com.api.usuario.model.dto.UserDto;
import com.api.usuario.model.entity.Phone;
import com.api.usuario.model.entity.Token;
import com.api.usuario.model.entity.User;
import com.api.usuario.model.mapper.UserMapper;
import com.api.usuario.repo.UserRepo;
import com.api.usuario.service.UserServiceImpl;
import com.api.usuario.service.login.LoginServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;
    @InjectMocks
    private LoginServiceImpl loginService;
    @Mock
    private UserMapper userMapper;
    @Mock
    private JwtUtils jwtGenerator;
    private User user;
    private final List<User> userList = new ArrayList<>();
    private UserDto userDto;
    private SignInDto sign;

    private Token token;

    @BeforeEach
    void setup() {
        user = user.builder().name("Marcus Phoenix").email("mark_pho@gmail.com").password("Gowrules123").build();
        Phone phone = Phone.builder().cityCode("123").countryCode("123").number("12345678").build();
        List<Phone> phonesList = new ArrayList<>();
        phonesList.add(phone);

        user.setPhones(phonesList);

        userDto = userDto.builder().name(user.getName()).email(user.getEmail()).password(user.getPassword()).build();

        sign = sign.builder().email(user.getEmail()).password(user.getPassword()).build();

        userList.add(user);

        token = new Token("1234");
    }

    @DisplayName("Prueba guardar un usuario.")
    @Test
    void guardar() {
        given(userRepo.save(user)).willReturn(user);
        given(userMapper.userDtoToUser(userDto)).willReturn(user);
//        given(jwtGenerator.generateToken(user)).willReturn(token);
        User savedUser = userMapper.userDtoToUser(userService.saveUser(userDto));
        assertThat(savedUser).isNotNull();
    }

    @DisplayName("Obtener usuario por email y clave")
    @Test
    void obtenerUsuarioPorEmailClave() {
        given(userRepo.findAll()).willReturn(userList);
//        given(jwtGenerator.generateToken(any(User.class))).willReturn(token);
        given(userRepo.save(user)).willReturn(user);
        UserDto userFound = loginService.obtenerLogin(sign);
        assertThat(userFound).isNotNull();
    }

    @DisplayName("Comprobar si existe email")
    @Test
    void existeEmail() {
        given(userRepo.findAll()).willReturn(userList);
        assertThat(userService.obtenerUsers().stream().anyMatch(x ->
                x.getEmail().equalsIgnoreCase("mark_pho@gmail.com"))).isTrue();
    }
}
