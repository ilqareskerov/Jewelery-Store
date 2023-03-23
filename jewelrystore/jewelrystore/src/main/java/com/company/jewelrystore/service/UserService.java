package com.company.jewelrystore.service;

import com.company.jewelrystore.dto.ErrorCode;
import com.company.jewelrystore.dto.UserDetailsConvertor;
import com.company.jewelrystore.dto.UserDto;
import com.company.jewelrystore.dto.UserDtoConvertor;
import com.company.jewelrystore.exception.GenericException;
import com.company.jewelrystore.model.User;
import com.company.jewelrystore.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoConvertor userDtoConvertor;
    private final UserDetailsConvertor userDetailsConvertor;


    public UserService(UserRepository userRepository, UserDtoConvertor userDtoConvertor, UserDetailsConvertor userDetailsConvertor) {
        this.userRepository = userRepository;
        this.userDtoConvertor = userDtoConvertor;
        this.userDetailsConvertor = userDetailsConvertor;
    }
    public boolean existByName(String username){
        return userRepository.existsByUsername(username);
    }
    public User createUser(User users){
        return userRepository.save(users);
    }
    public User updateUser(User user){return userRepository.save(user);
    }
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserDto findUser(String username) {
        var user = findUserByUsername(username);
        return new UserDto(user.getUsername(),user.getRole(),user.getId(),user.isActive(),user.getFullName(),userDetailsConvertor.userDetailsDtoListConvert(user.getUserDetails()));
    }
    public List<UserDto> getAllUser(){
        return userDtoConvertor.userConvertListUserDto(userRepository.findAll());
    }
    public UserDto findUserInContext() {
        final Authentication authentication = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).orElseThrow(()-> new GenericException(HttpStatus.UNAUTHORIZED, ErrorCode.USER_NOT_FOUND,"User not found"));
        final UserDetails details = Optional.ofNullable((UserDetails) authentication.getPrincipal()).orElseThrow(()-> new GenericException(HttpStatus.UNAUTHORIZED, ErrorCode.USER_NOT_FOUND,"User not found"));
        return findUser(details.getUsername());
    }
}
