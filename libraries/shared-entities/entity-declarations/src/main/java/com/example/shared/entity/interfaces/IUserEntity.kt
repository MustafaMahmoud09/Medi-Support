package com.example.shared.entity.interfaces

interface IUserEntity {

    //create table columns here
    val id: Long

    val email: String

    val password: String

    val auth: Boolean

    val createdAt: Long

}//end IUserEntity