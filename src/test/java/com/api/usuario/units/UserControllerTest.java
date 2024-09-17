package com.api.usuario.units;

import com.api.usuario.model.dto.SignInDto;
import com.api.usuario.model.dto.UserDto;
import com.api.usuario.model.dto.UserNewDto;
import com.api.usuario.model.entity.Token;
import com.api.usuario.model.entity.User;
import com.api.usuario.model.mapper.UserMapper;
import com.api.usuario.service.UserService;
import com.api.usuario.service.login.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private UserService userService;

    @Mock
    private LoginService loginService;

    @Mock
    private UserMapper userMapper;

    @DisplayName("Guardar un usuario.")
    @Test
    void guardar() throws Exception {

        Token token = new Token("1234");
        User userDto = User.builder()
                .name("Marcus Phoenix")
                .email("mark_pho@gmail.com")
                .password("Gowrules123")
                .token(token.getToken())
                .build();

        given(userMapper.userDtoToUser(userService.saveUser(any(UserNewDto.class)))).willReturn(userDto);

        ResultActions resultActions = mockMvc.perform(post("/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)));

        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.token", is(userDto.getToken())));

    }

    @DisplayName("Login un usuario.")
    @Test
    void login() throws Exception {

        Token token = new Token("1234");
        UserDto userDto = UserDto.builder()
                .email("mark_pho@gmail.com")
                .password("Gowrules123")
                .token(token.getToken())
                .build();

        given(loginService.obtenerLogin(any(SignInDto.class))).willReturn(userDto);

        ResultActions resultActions = mockMvc.perform(put("/login/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userDto)));

        resultActions.andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("Listar usuarios.")
    @Test
    void cargarUsuarios() throws Exception {

        List<UserDto> usersList = new ArrayList<>();

        given(userService.obtenerUsers()).willReturn(usersList);

        ResultActions resultActions = mockMvc.perform(get("/users/all")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andDo(print())
                .andExpect(status().isOk());

    }
}
