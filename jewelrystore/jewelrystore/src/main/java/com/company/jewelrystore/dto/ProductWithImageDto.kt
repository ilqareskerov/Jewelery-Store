package com.company.jewelrystore.dto

import com.company.jewelrystore.model.Category
import com.company.jewelrystore.model.Image

data class ProductWithImageDto(
    val id:String?,
    val product_name: String?,
    val product_desc:String?,
    val product_price: Float?= Float.MIN_VALUE,
    val product_count: Float?= Float.MIN_VALUE,
    val product_discount: Float?= Float.MIN_VALUE,
    val images:List<ImageDto>,
    val category: CategoryDto
)
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductWithImageDto

        if (id != other.id) return false
        if (product_name != other.product_name) return false
        if (product_desc != other.product_desc) return false
        if (product_price != other.product_price) return false
        if (product_count != other.product_count) return false
        if (product_discount != other.product_discount) return false
        if (images != other.images) return false
        if (category != other.category) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (product_name?.hashCode() ?: 0)
        result = 31 * result + (product_desc?.hashCode() ?: 0)
        result = 31 * result + (product_price?.hashCode() ?: 0)
        result = 31 * result + (product_count?.hashCode() ?: 0)
        result = 31 * result + (product_discount?.hashCode() ?: 0)
        result = 31 * result + images.hashCode()
        result = 31 * result + category.hashCode()
        return result
    }
}