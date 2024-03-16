package com.example.libraries.core.local.data.entity.declarations

interface IUserEntity {

    //create table columns here
    val id: Long

    val email: String

    val password: String

    val auth: Boolean

    val createdAt: Long

}//end IUserEntity