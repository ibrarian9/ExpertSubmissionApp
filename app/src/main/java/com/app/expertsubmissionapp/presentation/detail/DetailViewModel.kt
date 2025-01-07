package com.app.expertsubmissionapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.expertsubmissionapp.core.domain.model.MyProduct
import com.app.expertsubmissionapp.core.domain.usecase.ProductUseCase
import kotlinx.coroutines.flow.Flow

class DetailViewModel(private val productUseCase: ProductUseCase) : ViewModel() {
    fun setFavorite(product: MyProduct, newStatus: Boolean) {
        productUseCase.setFavoriteProduct(product, newStatus)
    }

    fun detailProduct(id: Int) = productUseCase.getDetailProduct(id).asLiveData()
}