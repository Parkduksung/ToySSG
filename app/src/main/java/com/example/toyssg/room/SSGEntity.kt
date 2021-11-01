package com.example.toyssg.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.toyssg.api.response.SSGItem

@Entity(tableName = "ssg_table")
data class SSGEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "detail") var detail: String? = "",
    @ColumnInfo(name = "image") var image: String? = "",
    @ColumnInfo(name = "name") var name: String? = "",
    @ColumnInfo(name = "price") var price: String? = "",
) {
    fun toSSGItem(): SSGItem =
        SSGItem(detail, image, name, price)
}
