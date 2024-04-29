package com.example.bmi.domain.dto.declarations.pageRecords

import com.example.bmi.domain.dto.declarations.IBMIDto

interface IData {

    val `data`: List<IBMIDto>?

    val links: ILinks?

}//end IData
