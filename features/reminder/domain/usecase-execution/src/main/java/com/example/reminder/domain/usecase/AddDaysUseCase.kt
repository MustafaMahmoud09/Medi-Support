package com.example.reminder.domain.usecase

import com.example.reminder.domain.usecase.interfaces.IAddDaysUseCase
import com.example.reminder.domain.usecase.interfaces.ICheckAppFirstRunUseCase
import com.example.repository.interfaces.IReminderRepository

class AddDaysUseCase(
    private val reminderRepository: IReminderRepository,
    private val checkAppFirstRunUseCase: ICheckAppFirstRunUseCase
) : IAddDaysUseCase {

    //function for add days
    override suspend fun invoke() {

        //check app is first run or no
        if (checkAppFirstRunUseCase()) {

            //set run reminder feature here
            reminderRepository.setRunReminderFeature(
                value = false
            )

            //store week days here
            reminderRepository.storeWeekDays(
                days = listOf(
                    "Sunday",
                    "Monday",
                    "Tuesday",
                    "Wednesday",
                    "Thursday",
                    "Friday",
                    "Saturday"
                )
            )

        }//end if

    }//end invoke

}//end AddDaysUseCase