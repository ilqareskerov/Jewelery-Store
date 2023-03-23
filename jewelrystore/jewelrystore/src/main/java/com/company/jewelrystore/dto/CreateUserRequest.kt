package com.company.jewelrystore.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateUserRequest(
    @JsonProperty("fullname")
    val fullname: String?,
    @JsonProperty("username")
    val username: String?,
    @JsonProperty("password")
    val password:String?
)
