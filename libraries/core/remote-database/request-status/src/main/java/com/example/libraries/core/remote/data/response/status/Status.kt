package com.example.libraries.core.remote.data.response.status

sealed class Status<out T>{

    data class Success<T>(val data : T) : Status<T>()

    data class Error(val message : String) : Status<Nothing>()

    object Loading : Status<Nothing>()

    fun toData() : T? = if(this is Success) data else null

}//end Status