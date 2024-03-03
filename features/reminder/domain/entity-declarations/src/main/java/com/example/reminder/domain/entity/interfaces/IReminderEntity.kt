package com.example.reminder.domain.entity.interfaces

interface IReminderEntity {

    //create table columns here
    val id: Long //primary key

    val name: String

    val time: Long

    val status: Boolean

    val userId: Long //foreign key

    val createdAt: Long

}//end IReminderEntity