package com.company.jewelrystore.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
@Entity
data class UserDetails(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val phoneNumber:String?,
    val adress:String?,
    val postCode:String?,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id", nullable = false)
    val users:User
){
    constructor(phoneNumber: String, adress: String, postCode: String,users: User) : this(
        id=null,
        phoneNumber=phoneNumber,
        adress = adress,
        postCode=postCode,
        users = users
    ) {

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserDetails

        if (id != other.id) return false
        if (phoneNumber != other.phoneNumber) return false
        if (adress != other.adress) return false
        if (postCode != other.postCode) return false
        if (users != other.users) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (phoneNumber?.hashCode() ?: 0)
        result = 31 * result + (adress?.hashCode() ?: 0)
        result = 31 * result + (postCode?.hashCode() ?: 0)
        result = 31 * result + users.hashCode()
        return result
    }

    override fun toString(): String {
        return "UserDetails(id=$id, phoneNumber=$phoneNumber, adress=$adress, postCode=$postCode, users=$users)"
    }

}
