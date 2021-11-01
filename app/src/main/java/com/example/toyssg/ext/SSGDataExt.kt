package com.example.toyssg.ext

import com.example.toyssg.api.response.SSGData
import com.example.toyssg.constant.SSGDataTYpe

fun SSGData.transViewTypeToInt(): Int {
    return when (viewType) {
        "image" ->  SSGDataTYpe.TYPE_IMAGE.ordinal
        "current_preview" ->  SSGDataTYpe.TYPE_CURRENT_PREVIEW.ordinal
        else -> SSGDataTYpe.TYPE_PRODUCT_ITEM.ordinal
    }
}