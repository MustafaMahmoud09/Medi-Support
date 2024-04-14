package com.example.auth.mapper.execution

import com.example.auth.domain.dto.declarations.emailUser.IEmailUserDto
import com.example.auth.domain.mapper.declarations.child.IEmailUserDtoToUserEntityMapper
import com.example.libraries.core.local.data.entity.declarations.IUserEntity

class EmailUserDtoToUserEntityMapper: IEmailUserDtoToUserEntityMapper {

    override fun objectConvertor(obj: IEmailUserDto): IUserEntity {
        TODO("Not yet implemented")
    }

}//end EmailUserDtoToUserEntityMapper