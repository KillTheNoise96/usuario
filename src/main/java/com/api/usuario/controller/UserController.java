package com.api.usuario.controller;

import com.api.usuario.model.dto.UserDto;
import com.api.usuario.model.dto.UserNewDto;
import com.api.usuario.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<?> saveUserInfo(@RequestBody @Valid UserNewDto userNewDto) {
        return new ResponseEntity<>(userService.saveUser(userNewDto),
                HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.obtenerUsers(), HttpStatus.OK);
    }
}
