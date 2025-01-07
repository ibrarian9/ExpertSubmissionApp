package com.app.expertsubmissionapp.core.domain.usecase

import com.app.expertsubmissionapp.core.data.Resource
import com.app.expertsubmissionapp.core.domain.model.MyProduct
import kotlinx.coroutines.flow.Flow

interface ProductUseCase {
    fun getAllProduct(): Flow<Resource<List<MyProduct>>>
    fun getDetailProduct(id: Int): Flow<MyProduct>
    fun getFavoriteProduct(): Flow<List<MyProduct>>
    fun setFavoriteProduct(product: MyProduct, state: Boolean)
}