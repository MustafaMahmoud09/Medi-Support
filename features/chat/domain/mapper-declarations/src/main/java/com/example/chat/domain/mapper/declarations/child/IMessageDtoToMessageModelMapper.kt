package com.example.chat.domain.mapper.declarations.child

import com.example.chat.domain.dto.declarations.message.IMessageDto
import com.example.chat.domain.mapper.declarations.IListMapper
import com.example.chat.domain.model.MessageModel


interface IMessageDtoToMessageModelMapper
    : IListMapper<IMessageDto, MessageModel>