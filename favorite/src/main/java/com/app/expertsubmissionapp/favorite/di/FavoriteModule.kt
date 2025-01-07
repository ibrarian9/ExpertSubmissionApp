package com.app.expertsubmissionapp.favorite.di

import com.app.expertsubmissionapp.favorite.presentation.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}

