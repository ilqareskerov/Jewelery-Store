package com.company.jewelrystore.dto

import com.company.jewelrystore.model.Category
import java.math.BigDecimal

data class ProductDto(
    val id:String?,
    val product_name: String?,
    val product_desc:String?,
    val product_price: Float?= Float.MIN_VALUE,
    val product_count: Float?= Float.MIN_VALUE,
    val product_discount: Float?= Float.MIN_VALUE,
    val category: Category
)
