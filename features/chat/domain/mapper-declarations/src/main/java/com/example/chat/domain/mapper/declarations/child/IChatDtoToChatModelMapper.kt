package com.example.chat.domain.mapper.declarations.child

import com.example.chat.domain.dto.declarations.chat.IContactDto
import com.example.chat.domain.mapper.declarations.IListMapper
import com.example.chat.domain.model.ChatModel


interface IChatDtoToChatModelMapper
    : IListMapper<IContactDto, ChatModel>