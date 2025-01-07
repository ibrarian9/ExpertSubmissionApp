package com.app.expertsubmissionapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyProduct(

    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: String,
    val title: String,
    val isFavorite: Boolean

): Parcelable