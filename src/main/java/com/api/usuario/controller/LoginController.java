package com.api.usuario.controller;

import com.api.usuario.model.dto.SignInDto;
import com.api.usuario.service.login.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(final LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody @Valid SignInDto sign) {
        return new ResponseEntity<>(loginService.obtenerLogin(sign), HttpStatus.OK);
    }
}
