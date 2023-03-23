package com.company.jewelrystore.dto

import com.company.jewelrystore.model.Category
import com.company.jewelrystore.model.Image
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import java.math.BigDecimal
import java.time.LocalDateTime

data class CreateProductRequest(
    @JsonProperty("product_name")
    val product_name: String?,
    @JsonProperty("product_desc")
    val product_desc:String?,
    @JsonProperty("product_price")
    val product_price: Float?=Float.MIN_VALUE,
    @JsonProperty("product_count")
    val product_count: Float?=Float.MIN_VALUE,
    @JsonProperty("product_discount")
    val product_discount: Float?=Float.MIN_VALUE,
    @JsonProperty("category_id")
    val category_id: String
)
