package com.company.jewelrystore.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateProductRequest(
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
