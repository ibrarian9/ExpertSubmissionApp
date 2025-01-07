package com.app.expertsubmissionapp.core.domain.repository

import com.app.expertsubmissionapp.core.domain.model.MyProduct
import com.app.expertsubmissionapp.core.data.Resource
import kotlinx.coroutines.flow.Flow

interface IProductRepository {

    fun getAllProduct(): Flow<Resource<List<MyProduct>>>

    fun getDetailProduct(id: Int): Flow<MyProduct>

    fun getFavoriteProduct(): Flow<List<MyProduct>>

    fun setFavoriteProduct(product: MyProduct, state: Boolean)
}