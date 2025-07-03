package com.example.chat.domain.mapper.declarations

interface IObjectMapper<IT, OT> {

    /**
     * abstract function tack object have input type and convert it to output type
     *
     * @return ST
     **/
    fun objectConvertor(obj: IT): OT

}//end BaseMapper