package com.example.blood.sugar.domain.dto.declarations.pageRecords

import com.example.blood.sugar.domain.dto.declarations.IBloodSugarDto

interface IData {

    val currentPage: Int?

    val lastPage: Int?

    val records: List<IBloodSugarDto?>?

}//end IData