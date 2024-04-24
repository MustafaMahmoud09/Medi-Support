package com.example.libraries.shered.logic.usecase.declarations

import com.example.libraries.shared.logic.domain.model.DayModel

interface IGetMonthDaysUseCase {

    operator fun invoke(): List<DayModel>

}//end IGetMonthDaysUseCase