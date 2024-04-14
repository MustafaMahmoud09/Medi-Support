package com.example.auth.domain.usecase.declarations

interface ICheckUserAuthUseCase {

    suspend operator fun invoke(): Boolean

}//end CheckUserAuthUseCase