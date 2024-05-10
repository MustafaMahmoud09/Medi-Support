package com.example.account.setting.data.repository.execution.cacheHelperExecution

import android.util.Log
import com.example.account.setting.data.repository.execution.cacheHelperDeclarations.ICacheAccountRepositoryHelper
import com.example.account.setting.data.repository.execution.cacheHelperDeclarations.IServerAccountRepositoryHelper
import com.example.account.setting.data.source.remote.data.dto.execution.profileInfo.ProfileInfoResponseDto
import com.example.account.setting.data.source.remote.data.requests.AccountSettingRequest
import com.example.account.setting.domain.dto.declarations.profileInfo.IProfileInfoResponseDto
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ServerAccountRepositoryHelper(
    private val accountSettingRequest: AccountSettingRequest,
    private val wrapper: ResponseWrapper,
    private val cacheAccountRepositoryHelper: ICacheAccountRepositoryHelper,
): IServerAccountRepositoryHelper {

    //function for make request on server for get account info
    override suspend fun getAccountInfoServer(
        accessToken: String
    ) {

        CoroutineScope(Dispatchers.IO).launch {

            while (true) {

                var breakCondition = false

                try {

                    //make request on serve for get article by id here
                    //collect request status here
                    wrapper.wrapper<IProfileInfoResponseDto, ProfileInfoResponseDto> {
                        accountSettingRequest.getProfileInfo()
                    }.collectLatest { status ->

                        when (status) {

                            is Status.Success -> {
                                //if status code equal 200
                                //process is success
                                if (status.toData()?.statusCode == 200) {

                                    //make cache article in local database here
                                    cacheAccountRepositoryHelper.cacheUserAccountInfo(
                                        accountInfo = status.toData()?.body!!,
                                        accessToken = accessToken
                                    )

                                }//end if

                                breakCondition = true
                                return@collectLatest
                            }//end success case

                            is Status.Loading -> {

                            }//end loading case

                            is Status.Error -> {
                                return@collectLatest
                            }//end error case

                        }//end when

                    }//end collectLatest

                } catch (ex: Exception) {
                    ex.message?.let { Log.d("ERROR", it) }
                }//end catch

                if (breakCondition) {
                    return@launch
                }//end if

            }//end while

        }//end CoroutineScope

    }//end getAccountInfoServer

}//end ServerAccountRepositoryHelper