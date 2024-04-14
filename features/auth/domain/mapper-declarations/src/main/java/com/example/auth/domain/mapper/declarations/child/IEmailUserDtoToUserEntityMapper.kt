package com.example.auth.domain.mapper.declarations.child

import com.example.auth.domain.dto.declarations.emailUser.IEmailUserDto
import com.example.auth.domain.mapper.declarations.IObjectMapper
import com.example.libraries.core.local.data.entity.declarations.IUserEntity

interface IEmailUserDtoToUserEntityMapper
    : IObjectMapper<IEmailUserDto, IUserEntity>