package com.example.reminder.domain.usecase

import com.example.reminder.domaim.domain.model.reminder.ReminderPresentationModel
import com.example.reminder.domain.mapper.declarations.child.IReminderWithDaysEntityToReminderModelMapper
import com.example.reminder.domain.usecase.interfaces.IGetUserRemindersUseCase
import com.example.repository.interfaces.IReminderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GetUserRemindersUseCase(
    private val reminderRepository: IReminderRepository,
    private val reminderEntityToReminderModelMapper: IReminderWithDaysEntityToReminderModelMapper
) : IGetUserRemindersUseCase {

    //function for get user reminders
    override suspend fun invoke(): Flow<List<ReminderPresentationModel>> {

        //return stream of data
        return flow {

            //collect data from repo and map this data to model
            reminderRepository.getReminders(
                userId = 1
            ).collect { reminders ->

                //map data from entity to model here
                val remindersModel = reminderEntityToReminderModelMapper.listConvertor(
                    list = reminders
                )

                //emit data in stream here
                emit(remindersModel)

            }//end collect

        }//end flow

    }//end invoke

}//end GetUsersReminderUseCase