package com.company.jewelrystore.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
@Entity
data class Category(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val createdDate : LocalDateTime? = null,
    val updatedDate : LocalDateTime? = null,
    val category_name:String?,
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonIgnore
    val product_list:Set<Product>?=null
) {

    constructor(category_name: String) : this(category_name=category_name, createdDate = null) {

    }

    constructor(id: String, category_name: String) : this(id=id, category_name = category_name, updatedDate = null) {

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (id != other.id) return false
        if (createdDate != other.createdDate) return false
        if (updatedDate != other.updatedDate) return false
        if (category_name != other.category_name) return false
        if (product_list != other.product_list) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (createdDate?.hashCode() ?: 0)
        result = 31 * result + (updatedDate?.hashCode() ?: 0)
        result = 31 * result + (category_name?.hashCode() ?: 0)
        result = 31 * result + product_list.hashCode()
        return result
    }

    override fun toString(): String {
        return "Category(id=$id, createdDate=$createdDate, updatedDate=$updatedDate, category_name=$category_name, product_list=$product_list)"
    }

}