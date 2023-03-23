package com.company.jewelrystore.controller;

import com.company.jewelrystore.dto.CreateUserDetailsRequest;
import com.company.jewelrystore.dto.UserDetailsDto;
import com.company.jewelrystore.dto.UserDto;
import com.company.jewelrystore.service.UserDetailsService;
import com.company.jewelrystore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/details")
public class UserDetailsController {
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    public UserDetailsController(UserDetailsService userDetailsService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDetailsDto> createUserDetails(@RequestBody CreateUserDetailsRequest createUserDetailsRequest){
        UserDto user=userService.findUserInContext();
        return ResponseEntity.ok(userDetailsService.createUserDetails(createUserDetailsRequest,user));


    }
}
