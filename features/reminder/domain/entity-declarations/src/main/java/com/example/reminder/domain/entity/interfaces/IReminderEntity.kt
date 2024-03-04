package com.example.reminder.domain.entity.interfaces

import java.time.LocalTime
import java.util.Date

interface IReminderEntity {

    //create table columns here
    val id: Long //primary key

    val name: String

    val time: LocalTime

    val status: Boolean

    val userId: Long //foreign key

    val createdAt: Long

}//end IReminderEntity