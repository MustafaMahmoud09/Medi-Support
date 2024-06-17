package com.example.room.mapper.execution

import com.example.domain_model.OnlineRoomModel
import com.example.room.domain.dto.declarations.roomToken.IOnlineRoomDto
import com.example.room.domain.mapper.declarations.child.IOnlineRoomDtoToOnlineRoomModelMapper

class OnlineRoomDtoToOnlineRoomModelMapper : IOnlineRoomDtoToOnlineRoomModelMapper {

    override fun listConvertor(
        list: List<IOnlineRoomDto>
    ): List<OnlineRoomModel> {

        return list.map { onlineRoomDto ->
            objectConvertor(
                obj = onlineRoomDto
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IOnlineRoomDto
    ): OnlineRoomModel {

        return OnlineRoomModel(
            token = obj.generateToken ?: "",
            roomName = obj.generateToken ?: "",
            callId = obj.callId ?: 0
        )

    }//end objectConvertor

}//end OnlineRoomDtoToOnlineRoomModelMapper