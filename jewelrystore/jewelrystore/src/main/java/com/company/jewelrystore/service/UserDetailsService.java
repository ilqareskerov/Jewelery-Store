package com.company.jewelrystore.service;

import com.company.jewelrystore.dto.CreateUserDetailsRequest;
import com.company.jewelrystore.dto.UserDetailsConvertor;
import com.company.jewelrystore.dto.UserDetailsDto;
import com.company.jewelrystore.dto.UserDto;
import com.company.jewelrystore.model.User;
import com.company.jewelrystore.model.UserDetails;
import com.company.jewelrystore.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;
    private final UserService userService;
    private final UserDetailsConvertor userDetailsConvertor;

    public UserDetailsService(UserDetailsRepository userDetailsRepository, UserService userService, UserDetailsConvertor userDetailsConvertor) {
        this.userDetailsRepository = userDetailsRepository;
        this.userService = userService;
        this.userDetailsConvertor = userDetailsConvertor;
    }

    public UserDetailsDto createUserDetails(CreateUserDetailsRequest createUserDetailsRequest, UserDto user) {
        User user1=userService.findUserByUsername(user.getUsername());
        UserDetails userDetails=new UserDetails(createUserDetailsRequest.getPhoneNumber(),createUserDetailsRequest.getAdress(),createUserDetailsRequest.getPostCode(),user1);
       // user1.getUserDetails().add(userDetails);
        //userService.updateUser(user1);
        return userDetailsConvertor.userDetailsDtoConvert(userDetailsRepository.save(userDetails));

    }
}
