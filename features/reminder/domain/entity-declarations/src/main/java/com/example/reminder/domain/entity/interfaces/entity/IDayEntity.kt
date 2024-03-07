package com.example.reminder.domain.entity.interfaces.entity

interface IDayEntity {

    //create table columns here
    val id: Long //primary key

    val day: String

    val createdAt: Long

}//end IDayEntity