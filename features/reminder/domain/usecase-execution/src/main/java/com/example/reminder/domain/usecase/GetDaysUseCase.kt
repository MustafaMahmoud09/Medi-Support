package com.example.reminder.domain.usecase

import com.example.reminder.domaim.domain.model.Day
import com.example.reminder.domain.mapper.declarations.child.IDayEntityToDayModelMapper
import com.example.reminder.domain.usecase.interfaces.IGetDaysUseCase
import com.example.repository.interfaces.IReminderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GetDaysUseCase(
    private val reminderRepository: IReminderRepository,
    private val dayEntityToDayModelMapper: IDayEntityToDayModelMapper
) : IGetDaysUseCase {

    //function for collect days from repository and convert this data to model
    //and emit this data in stream of data
    override suspend fun invoke(): Flow<List<Day>> {

        //create stream of data here
        return flow {

            //collect stream of data from repository here
            reminderRepository.getWeekDays().collect { days ->

                //convert data from entity to model here
                val daysModel = dayEntityToDayModelMapper.listConvertor(
                    list = days
                )

                //emit data in stream of data here
                emit(daysModel)

            }//end collect

        }//end flow

    }//end invoke

}//end GetDaysUseCase