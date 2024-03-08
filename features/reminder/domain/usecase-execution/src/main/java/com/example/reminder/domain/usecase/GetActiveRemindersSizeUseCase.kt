package com.example.reminder.domain.usecase

import com.example.reminder.domain.usecase.interfaces.IGetActiveRemindersSizeUseCase
import com.example.repository.interfaces.IReminderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GetActiveRemindersSizeUseCase(
    private val reminderRepository: IReminderRepository
) : IGetActiveRemindersSizeUseCase {

    //function for provide active reminders size
    override suspend fun invoke(): Flow<Long> {

        //create use case flow here
        return flow {

            //observe repository flow here
            reminderRepository.getRemindersByStatus(
                status = true
            ).collect { reminders ->

                //emit reminder size here
                emit(reminders.size.toLong())

            }//end collect

        }//end flow

    }//end invoke

}//end GetActiveRemindersSizeUseCase