package com.app.expertsubmissionapp.favorite.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.expertsubmissionapp.core.domain.usecase.ProductUseCase

class FavoriteViewModel(productUseCase: ProductUseCase) : ViewModel() {
    val favoriteProduct = productUseCase.getFavoriteProduct().asLiveData()
}