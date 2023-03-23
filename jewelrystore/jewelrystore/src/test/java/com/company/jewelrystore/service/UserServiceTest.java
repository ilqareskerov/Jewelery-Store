package com.company.jewelrystore.service;

import com.company.jewelrystore.dto.UserDetailsConvertor;
import com.company.jewelrystore.dto.UserDto;
import com.company.jewelrystore.dto.UserDtoConvertor;
import com.company.jewelrystore.model.Role;
import com.company.jewelrystore.model.User;
import com.company.jewelrystore.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;
    private  UserRepository userRepository;
    private  UserDtoConvertor userDtoConvertor;
    private  UserDetailsConvertor userDetailsConvertor;

    @BeforeEach
    public void setUp(){
        userRepository= Mockito.mock(UserRepository.class);
        userDtoConvertor=Mockito.mock(UserDtoConvertor.class);
        userDetailsConvertor=Mockito.mock(UserDetailsConvertor.class);
        userService=new UserService(userRepository,userDtoConvertor,userDetailsConvertor);
    }
    @Test
    public void testFindUser_whenUserIsExists_shouldReturnUserDto(){

    }
}