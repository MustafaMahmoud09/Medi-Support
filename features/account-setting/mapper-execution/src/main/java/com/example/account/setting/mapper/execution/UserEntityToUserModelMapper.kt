package com.example.account.setting.mapper.execution

import com.example.account.setting.domain.mapper.declarations.child.IUserEntityToUserModelMapper
import com.example.account.setting.domain.model.UserModel
import com.example.libraries.core.local.data.entity.declarations.IUserEntity

class UserEntityToUserModelMapper(
    private val imageUrl: String
) : IUserEntityToUserModelMapper {

    override fun listConvertor(
        list: List<IUserEntity>
    ): List<UserModel> {

        return list.map { userEntity ->
            objectConvertor(
                obj = userEntity
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IUserEntity
    ): UserModel {

        return UserModel(
            image = if (obj.path != null) {
                if (
                    obj.path!!.contains("https://") ||
                    obj.path!!.contains("http://")
                ) {
                    obj.path ?: ""
                } else {
                    imageUrl + (obj.path ?: "")
                }
            } else {
                ""
            },
            lastName = obj.lastName ?: "",
            firstName = obj.firstName ?: "",
            name = (obj.firstName ?: "") + " " + (obj.lastName ?: "")
        )

    }//end objectConvertor

}//end UserEntityToUserModelMapper