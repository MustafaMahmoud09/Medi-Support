package com.example.reminder.domain.usecase

import com.example.reminder.domaim.domain.model.reminder.NearestReminderPresentationModel
import com.example.reminder.domain.mapper.declarations.child.INearestReminderEntityToNearestReminderModelMapper
import com.example.reminder.domain.usecase.interfaces.IGetNearestRemindersUseCase
import com.example.repository.interfaces.IReminderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.time.LocalTime

class GetNearestReminderUseCase(
    private val reminderRepository: IReminderRepository,
    private val nearestReminderEntityNearestReminderModelMapper: INearestReminderEntityToNearestReminderModelMapper
) : IGetNearestRemindersUseCase {

    //function for provide nearest reminder
    override suspend fun invoke(
        status: Boolean,
        localTime: LocalTime,
        currentDayNumber: Int
    ): Flow<List<NearestReminderPresentationModel>> {

        //create stream of data here
        return flow {

            //observe repository flow here
            reminderRepository.getNearestReminder(
                status = status,
                localTime = localTime,
                currentDayNumber = currentDayNumber
            ).collect { reminder ->

                //convert reminder from entity to model here
                val reminderModel = nearestReminderEntityNearestReminderModelMapper.listConvertor(
                    list = reminder
                )

                //emit reminder model object here
                emit(reminderModel)

            }//end collect

        }//end flow

    }//end invoke

}//end GetNearestActiveReminderUseCase