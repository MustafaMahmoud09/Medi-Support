package com.example.account.setting.domain.mapper.declarations.child

import com.example.account.setting.domain.mapper.declarations.IListMapper
import com.example.account.setting.domain.model.UserModel
import com.example.libraries.core.local.data.entity.declarations.IUserEntity

interface IUserEntityToUserModelMapper :
    IListMapper<IUserEntity, UserModel>