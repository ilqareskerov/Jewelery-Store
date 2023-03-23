package com.company.jewelrystore.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime

@Entity
data class Image(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val createdDate : LocalDateTime? = null,
    val updatedDate : LocalDateTime? = null,
    val image_name:String?,
    val image_type:String?,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JsonIgnore
    @JoinColumn(name = "product_id", nullable = false)
    val product:Product?,

) {
    constructor(image_name: String?,image_type: String?,product: Product?) : this(createdDate = null,image_name = image_name, image_type = image_type, product = product){
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Image

        if (id != other.id) return false
        if (createdDate != other.createdDate) return false
        if (updatedDate != other.updatedDate) return false
        if (image_name != other.image_name) return false
        if (image_type != other.image_type) return false
        if (product != other.product) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (createdDate?.hashCode() ?: 0)
        result = 31 * result + (updatedDate?.hashCode() ?: 0)
        result = 31 * result + (image_name?.hashCode() ?: 0)
        result = 31 * result + (image_type?.hashCode() ?: 0)
        result = 31 * result + (product?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Image(id=$id, createdDate=$createdDate, updatedDate=$updatedDate, image_name=$image_name, image_type=$image_type, product=$product)"
    }

}