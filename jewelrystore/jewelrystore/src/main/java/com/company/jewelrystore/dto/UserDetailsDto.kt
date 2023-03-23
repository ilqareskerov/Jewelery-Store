package com.company.jewelrystore.dto

import com.company.jewelrystore.model.User
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

data class UserDetailsDto(
                           val id: String? = "",
                           val phoneNumber:String?,
                           val adress:String?,
                           val postCode:String?
)
