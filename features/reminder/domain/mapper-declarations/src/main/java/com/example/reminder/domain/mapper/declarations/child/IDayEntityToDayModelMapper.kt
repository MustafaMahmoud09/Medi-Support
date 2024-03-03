package com.example.reminder.domain.mapper.declarations.child

import com.example.reminder.domaim.domain.model.Day
import com.example.reminder.domain.entity.interfaces.IDayEntity
import com.example.reminder.domain.mapper.declarations.IListMapper

interface IDayEntityToDayModelMapper : IListMapper<IDayEntity, Day>