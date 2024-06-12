package com.example.notification.domain.dto.declarations.notificationPage

interface IData {

    val currentPage: Int?

    val `data`: List<INotificationDto>?

    val firstPageUrl: String?

    val from: Int?

    val lastPage: Int?

    val lastPageUrl: String?

    val nextPageUrl: Any?

    val path: String?

    val perPage: Int?

    val prevPageUrl: Any?

    val to: Int?

    val total: Int?

}//end IData