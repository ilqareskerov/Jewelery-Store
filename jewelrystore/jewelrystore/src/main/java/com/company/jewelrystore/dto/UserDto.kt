package com.company.jewelrystore.dto

import com.company.jewelrystore.model.Role
import com.company.jewelrystore.model.UserDetails

data class UserDto(
    val username:String?,
    val role:Role?,
    val id:String?,
    val isActive: Boolean?=false,
    val fullName:String?,
    val userDetails: List<UserDetailsDto>
){

}
