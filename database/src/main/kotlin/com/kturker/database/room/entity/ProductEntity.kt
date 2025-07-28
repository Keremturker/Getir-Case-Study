package com.kturker.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = "",
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "attribute")
    val attribute: String = "",
    @ColumnInfo(name = "shortDescription")
    val shortDescription: String = "",
    @ColumnInfo(name = "thumbnailURL")
    val thumbnailURL: String = "",
    @ColumnInfo(name = "imageURL")
    val imageURL: String = "",
    @ColumnInfo(name = "price")
    val price: Double = 0.0,
    @ColumnInfo(name = "priceText")
    val priceText: String = ""
)
