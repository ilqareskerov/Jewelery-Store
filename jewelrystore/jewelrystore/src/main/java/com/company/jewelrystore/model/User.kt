package com.company.jewelrystore.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
data class User(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val fullName: String?="Full Name",
    @Column(unique = true)
    val username: String?,
    val password:String?,
    val isActive:Boolean?=false,
    @Enumerated(EnumType.STRING)
    val role: Role?,
    @OneToMany(mappedBy = "users", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonIgnore
    val userDetails: List<UserDetails>?
){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (fullName != other.fullName) return false
        if (username != other.username) return false
        if (password != other.password) return false
        if (isActive != other.isActive) return false
        if (role != other.role) return false
        if (userDetails != other.userDetails) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (fullName?.hashCode() ?: 0)
        result = 31 * result + (username?.hashCode() ?: 0)
        result = 31 * result + (password?.hashCode() ?: 0)
        result = 31 * result + (isActive?.hashCode() ?: 0)
        result = 31 * result + (role?.hashCode() ?: 0)
        result = 31 * result + userDetails.hashCode()
        return result
    }

    override fun toString(): String {
        return "Users(id=$id, fullName=$fullName, username=$username, password=$password, isActive=$isActive, role=$role, userDetails=$userDetails)"
    }

    constructor(fullName: String?,username: String?, password: String?, role: Role?) : this(fullName = fullName, username = username, password = password, isActive = false, role = role, userDetails = null){

    }

}

