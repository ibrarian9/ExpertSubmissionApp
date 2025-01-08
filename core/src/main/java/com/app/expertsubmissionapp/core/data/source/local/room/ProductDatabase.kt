package com.app.expertsubmissionapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.expertsubmissionapp.core.data.source.local.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 3, exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}