package com.example.article.domain.mapper.declarations

interface IListMapper<IT, OT> : IObjectMapper<IT, OT> {

    /**
     * abstract function tack list have input type and convert it to list have output type
     *
     * @return List<OT>
     **/
    fun listConvertor(list: List<IT>): List<OT>

}//end IListMapper