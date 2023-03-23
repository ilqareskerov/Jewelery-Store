package com.company.jewelrystore.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CategoryDto(
    @JsonProperty("id")
    val id:String?,
    @JsonProperty("categoryName")
    val categoryName:String?)
