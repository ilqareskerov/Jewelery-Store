package com.company.jewelrystore.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import org.hibernate.internal.util.type.PrimitiveWrapperHelper.FloatDescriptor
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class Product (
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val createdDate : LocalDateTime? = null,
    val updatedDate : LocalDateTime? = null,
    val product_name: String?,
    val product_desc:String?,
    val product_price: Float?= Float.MIN_VALUE,
    val product_count: Float?= Float.MIN_VALUE,
    val product_discount: Float?= Float.MIN_VALUE,
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    val images:List<Image>?=null,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "category_id", nullable = false)
    val category: Category?
) {



    override fun toString(): String {
        return "Product(id=$id, createdDate=$createdDate, updatedDate=$updatedDate, product_name=$product_name, product_desc=$product_desc, product_price=$product_price, product_count=$product_count, product_discount=$product_discount, images=$images, category=$category)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false
        if (createdDate != other.createdDate) return false
        if (updatedDate != other.updatedDate) return false
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
        result = 31 * result + (createdDate?.hashCode() ?: 0)
        result = 31 * result + (updatedDate?.hashCode() ?: 0)
        result = 31 * result + (product_name?.hashCode() ?: 0)
        result = 31 * result + (product_desc?.hashCode() ?: 0)
        result = 31 * result + (product_price?.hashCode() ?: 0)
        result = 31 * result + (product_count?.hashCode() ?: 0)
        result = 31 * result + (product_discount?.hashCode() ?: 0)
        result = 31 * result + images.hashCode()
        result = 31 * result + category.hashCode()
        return result
    }
    constructor(product_name: String, product_desc: String, product_price: Float, product_count: Float,product_discount: Float?,category: Category?) : this(
        product_name=product_name,
        product_desc=product_desc,
        product_price=product_price,
        product_count=product_count,
        product_discount=product_discount,
        images=null,
        category=category
    ) {

    }
    constructor(id: String?,product_name: String, product_desc: String, product_price: Float, product_count: Float,product_discount: Float?,category: Category?) : this(
        id=id,
        product_name=product_name,
        product_desc=product_desc,
        product_price=product_price,
        product_count=product_count,
        product_discount=product_discount,
        images=null,
        category=category
    ) {

    }

}