package com.example.online.booking.mapper.execution

import com.example.online.booking.domain.dto.declarations.payment.IPaymentDto
import com.example.online.booking.domain.mapper.declarations.child.IPaymentDtoToPaymentModelMapper
import com.example.online.booking.domain.model.PaymentModel

class PaymentDtoToPaymentModelMapper : IPaymentDtoToPaymentModelMapper {

    override fun listConvertor(list: List<IPaymentDto>): List<PaymentModel> {

        return list.map { paymentDto ->
            objectConvertor(
                obj = paymentDto
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(obj: IPaymentDto): PaymentModel {

        return PaymentModel(
            paymentIntent = obj.clientSecret ?: "",
            bookingId = obj.metadata?.bookingId ?: 0
        )

    }//end objectConvertor

}//end PaymentDtoToPaymentModelMapper