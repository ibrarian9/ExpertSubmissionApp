package com.app.expertsubmissionapp.core.domain.usecase

import com.app.expertsubmissionapp.core.domain.model.MyProduct
import com.app.expertsubmissionapp.core.domain.repository.IProductRepository
import kotlinx.coroutines.flow.Flow

class ProductInteractor(private val productRepository: IProductRepository): ProductUseCase {

    override fun getAllProduct() = productRepository.getAllProduct()

    override fun getDetailProduct(id: Int): Flow<MyProduct> = productRepository.getDetailProduct(id)

    override fun getFavoriteProduct() = productRepository.getFavoriteProduct()

    override fun setFavoriteProduct(product: MyProduct, state: Boolean)
    = productRepository.setFavoriteProduct(product, state)
}