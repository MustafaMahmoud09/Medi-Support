package com.example.reminder.domain.entity.interfaces.entity

interface IReminderDateEntity {

    //create table columns here
    val id: Long //primary key

    val reminderId: Long //foreign key

    val dayId: Long //foreign key

    val createdAt: Long

}//end IReminderDateEntity