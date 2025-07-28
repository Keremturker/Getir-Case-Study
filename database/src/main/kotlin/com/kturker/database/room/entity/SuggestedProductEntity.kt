package com.kturker.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SuggestedProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = "",
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "price")
    val price: Double = 0.0,
    @ColumnInfo(name = "priceText")
    val priceText: String = "",
    @ColumnInfo(name = "imageURL")
    val imageURL: String = "",
    @ColumnInfo(name = "shortDescription")
    val shortDescription: String = "",
    @ColumnInfo(name = "squareThumbnailURL")
    val squareThumbnailURL: String = "",
    @ColumnInfo(name = "status")
    val status: Int = 0,
    @ColumnInfo(name = "category")
    val category: String = "",
    @ColumnInfo(name = "unitPrice")
    val unitPrice: Double = 0.0
)
