package com.company.jewelrystore.dto;

import com.company.jewelrystore.model.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsConvertor {

    public UserDetailsDto userDetailsDtoConvert(UserDetails userDetails){
        return new UserDetailsDto(userDetails.getId(),userDetails.getPhoneNumber(),userDetails.getAdress(),userDetails.getPostCode());
    }
    public List<UserDetailsDto> userDetailsDtoListConvert(List<UserDetails> userDetails){
        return userDetails.stream().map(this::userDetailsDtoConvert).collect(Collectors.toList());
    }
}
