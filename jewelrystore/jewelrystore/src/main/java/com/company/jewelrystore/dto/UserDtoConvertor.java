package com.company.jewelrystore.dto;

import com.company.jewelrystore.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConvertor {
    private final UserDetailsConvertor userDetailsConvertor;

    public UserDtoConvertor(UserDetailsConvertor userDetailsConvertor) {
        this.userDetailsConvertor = userDetailsConvertor;
    }

    public UserDto userConvertUserDto(User users){
       List< UserDetailsDto> userDetailsDto=userDetailsConvertor.userDetailsDtoListConvert(users.getUserDetails());
        return new UserDto(users.getUsername(),users.getRole(),users.getId(),users.isActive(),users.getFullName(),userDetailsDto);
    }
    public List<UserDto> userConvertListUserDto(List<User> users){
        return users.stream().map(this::userConvertUserDto).collect(Collectors.toList());
    }
}
