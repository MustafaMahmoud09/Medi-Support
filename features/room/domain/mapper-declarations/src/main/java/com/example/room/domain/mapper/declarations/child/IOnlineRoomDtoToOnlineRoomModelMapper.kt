package com.example.room.domain.mapper.declarations.child

import com.example.domain_model.OnlineRoomModel
import com.example.room.domain.dto.declarations.roomToken.IOnlineRoomDto
import com.example.room.domain.mapper.declarations.IListMapper

interface IOnlineRoomDtoToOnlineRoomModelMapper
    : IListMapper<IOnlineRoomDto, OnlineRoomModel>