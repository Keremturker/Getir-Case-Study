package com.kturker.database.room

import androidx.room.ColumnInfo

data class ProductWithCart(
    @ColumnInfo(name = "id")
    val id: String = "",
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "description")
    val description: String = "",
    @ColumnInfo(name = "imageURL")
    val imageURL: String = "",
    @ColumnInfo(name = "price")
    val price: Double = 0.0,
    @ColumnInfo(name = "priceText")
    val priceText: String = "",
    @ColumnInfo(name = "quantity")
    val quantity: Int
)
