package com.app.expertsubmissionapp.core.data.source.local

import com.app.expertsubmissionapp.core.data.source.local.entity.ProductEntity
import com.app.expertsubmissionapp.core.data.source.local.room.ProductDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val productDao: ProductDao) {

    fun getAllProduct(): Flow<List<ProductEntity>> = productDao.getAllProduct()

    fun getDetailProduct(id: Int): Flow<ProductEntity> = productDao.getDetailProduct(id)

    fun getFavoriteProduct(): Flow<List<ProductEntity>> = productDao.getFavoriteProduct()

    suspend fun insertProduct(producList: List<ProductEntity>) = productDao.insertProduct(producList)

    fun setFavoriteProduct(product: ProductEntity, newState: Boolean) {
        product.isFavorite = newState
        productDao.updateFavoriteProduct(product)
    }
}