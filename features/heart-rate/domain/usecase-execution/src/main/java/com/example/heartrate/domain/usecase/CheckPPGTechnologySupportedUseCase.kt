package com.example.heartrate.domain.usecase

import com.example.heart.rate.domain.repository.declarations.IHeartRateRepository
import com.example.heart.rate.domain.usecase.declarations.ICheckPPGTechnologySupportedUseCase

class CheckPPGTechnologySupportedUseCase(
    private val heartRateRepository: IHeartRateRepository
) : ICheckPPGTechnologySupportedUseCase {

    //function for check device support ppg technology or no
    override fun invoke(): Boolean {

        return heartRateRepository.isFlashCameraExist()
                && heartRateRepository.isLightSensorExist()

    }//end invoke

}//end CheckPPGTechnologySupportedUseCase