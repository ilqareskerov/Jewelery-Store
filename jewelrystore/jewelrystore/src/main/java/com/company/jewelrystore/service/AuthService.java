package com.company.jewelrystore.service;

import com.company.jewelrystore.dto.*;
import com.company.jewelrystore.exception.GenericException;
import com.company.jewelrystore.model.Role;
import com.company.jewelrystore.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final TokenService tokenService;
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final UserDtoConvertor  userDtoConvertor;
    private final AuthenticationManager authenticationManager;
    public AuthService(TokenService tokenService, UserService userService, BCryptPasswordEncoder encoder, UserDtoConvertor userDtoConvertor, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.encoder = encoder;
        this.userDtoConvertor = userDtoConvertor;
        this.authenticationManager = authenticationManager;
    }
    public TokenResponseDto login(LoginUserRequest loginUserRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserRequest.getUsername(), loginUserRequest.getPassword()));
            return new TokenResponseDto(tokenService.generateToken(authentication),userService.findUser(loginUserRequest.getUsername()));
        }catch (Exception exception){
            throw new GenericException(HttpStatus.NOT_FOUND,ErrorCode.USER_NOT_FOUND,"Invalid username or password");
        }
    }
    @Transactional
    public UserDto singUp(CreateUserRequest createUserRequest){
        boolean isExist=userService.existByName(createUserRequest.getUsername());
     if(isExist){
      throw new GenericException(HttpStatus.FOUND,createUserRequest.getUsername()+" is alread used");
     }
     User users=new User(createUserRequest.getFullname(),createUserRequest.getUsername(),encoder.encode(createUserRequest.getPassword()),Role.USER);
     return userDtoConvertor.userConvertUserDto(userService.createUser(users));
    }
}
