package com.app.expertsubmissionapp.di

import com.app.expertsubmissionapp.presentation.detail.DetailViewModel
import com.app.expertsubmissionapp.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}