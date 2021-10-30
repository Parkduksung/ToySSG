package com.example.toyssg.api.response

import com.google.gson.annotations.SerializedName

data class SSGItemResponse(
    @SerializedName("data") val data: List<SSGData>,
    @SerializedName("result") val result: String
)


data class SSGData(
    @SerializedName("data") val item: SSGItem,
    @SerializedName("viewtype") val viewType: String
)

data class SSGItem(
    @SerializedName("detail") var detail: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("price") var price: String? = null
)