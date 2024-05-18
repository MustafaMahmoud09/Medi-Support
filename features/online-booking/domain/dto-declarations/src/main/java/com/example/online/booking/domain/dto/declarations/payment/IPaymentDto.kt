package com.example.online.booking.domain.dto.declarations.payment


interface IPaymentDto {

    val clientSecret: String?

    val customer: String?

    val id: String?

    val metadata: IMetadata?

}//end PaymentDto