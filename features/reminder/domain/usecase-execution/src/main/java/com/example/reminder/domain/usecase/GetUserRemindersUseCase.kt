package com.example.reminder.domain.usecase

import com.example.reminder.domaim.domain.model.reminder.ReminderPresentationModel
import com.example.reminder.domain.mapper.declarations.child.IReminderWithDaysEntityToReminderModelMapper
import com.example.reminder.domain.usecase.interfaces.IGetUserRemindersUseCase
import com.example.repository.interfaces.IReminderRepository

class GetUserRemindersUseCase(
    private val reminderRepository: IReminderRepository,
    private val reminderEntityToReminderModelMapper: IReminderWithDaysEntityToReminderModelMapper
) : IGetUserRemindersUseCase {

    //function for get user reminders
    override suspend fun invoke(
        page: Int,
        pageSize: Int
    ): List<ReminderPresentationModel> {

        //get reminders from data source
        val remindersEntity = reminderRepository.getReminders(
            userId = 1,
            page = page,
            pageSize = pageSize
        )

        //map reminders from entity to domain model and return it
        return reminderEntityToReminderModelMapper.listConvertor(
            list = remindersEntity
        )

    }//end invoke

}//end GetUsersReminderUseCase