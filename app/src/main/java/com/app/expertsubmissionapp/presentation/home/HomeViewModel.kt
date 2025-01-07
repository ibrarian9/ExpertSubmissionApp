package com.app.expertsubmissionapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.expertsubmissionapp.core.domain.usecase.ProductUseCase

class HomeViewModel(productUseCase: ProductUseCase) : ViewModel() {
    val product = productUseCase.getAllProduct().asLiveData()
}