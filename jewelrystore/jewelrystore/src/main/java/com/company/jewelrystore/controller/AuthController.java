package com.company.jewelrystore.controller;

import com.company.jewelrystore.dto.CreateUserRequest;
import com.company.jewelrystore.dto.LoginUserRequest;
import com.company.jewelrystore.dto.TokenResponseDto;
import com.company.jewelrystore.dto.UserDto;
import com.company.jewelrystore.service.AuthService;
import com.company.jewelrystore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginUserRequest loginUserRequest){

        return ResponseEntity.ok(authService.login(loginUserRequest));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> singUp(@RequestBody CreateUserRequest createUserRequest){
        return ResponseEntity.ok(authService.singUp(createUserRequest));
    }
}
