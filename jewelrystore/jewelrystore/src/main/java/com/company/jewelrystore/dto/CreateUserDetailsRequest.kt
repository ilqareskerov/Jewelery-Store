package com.company.jewelrystore.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateUserDetailsRequest(
    @JsonProperty("phoneNumber")
    val phoneNumber:String?,
    @JsonProperty("adress")
    val adress:String?,
    @JsonProperty("postCode")
    val postCode:String?
)
