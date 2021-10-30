package com.example.toyssg.api.response

data class SSGItemResponse(
    val data: List<SSGData>,
    val result: String
)


data class SSGData(
    val data: SSGItem,
    val viewType: String
)

data class SSGItem(
    var detail: String? = null,
    var image: String? = null,
    var name: String? = null,
    var price: String? = null
)