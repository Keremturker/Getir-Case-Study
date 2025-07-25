package com.kturker.database.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kturker.database.room.dao.ProductDao
import com.kturker.database.room.dao.SuggestedProductDao
import com.kturker.database.room.entity.ProductEntity
import com.kturker.database.room.entity.SuggestedProductEntity

@Database(
    entities = [ProductEntity::class, SuggestedProductEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun suggestedProductDao(): SuggestedProductDao
}