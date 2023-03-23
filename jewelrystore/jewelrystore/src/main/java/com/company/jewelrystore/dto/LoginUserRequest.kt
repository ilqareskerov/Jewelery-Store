package com.company.jewelrystore.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginUserRequest(
    @JsonProperty("username")
    val username:String?,
    @JsonProperty("password")
    val password:String?
)
