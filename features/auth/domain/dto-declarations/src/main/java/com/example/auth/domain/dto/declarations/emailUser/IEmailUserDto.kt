package com.example.auth.domain.dto.declarations.emailUser

//interface for contain on email user dto declaration
interface IEmailUserDto {

    val accessToken: String?

    val tokenType: String?

    val user: IUser?

}//end EmailUserDto