package com.example.libraries.core.local.data.entity.declarations

interface IUserEntity {

    //create table columns here
    val id: Long

    val firstName: String?

    val lastName: String?

    val email: String?

    val token: String?

    val path: String?

    val auth: Boolean

    val remember: Boolean

    val count: Int

    val createdAt: Long

}//end IUserEntity