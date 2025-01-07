package com.app.expertsubmissionapp.core.di

import androidx.room.Room
import com.app.expertsubmissionapp.core.data.ProductRepository
import com.app.expertsubmissionapp.core.data.source.local.LocalDataSource
import com.app.expertsubmissionapp.core.data.source.local.room.ProductDatabase
import com.app.expertsubmissionapp.core.data.source.remote.RemoteDataSource
import com.app.expertsubmissionapp.core.data.source.remote.network.ApiService
import com.app.expertsubmissionapp.core.domain.repository.IProductRepository
import com.app.expertsubmissionapp.core.domain.usecase.ProductInteractor
import com.app.expertsubmissionapp.core.domain.usecase.ProductUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val urlBase = "https://fakestoreapi.com/"

val appModule = module {

    factory { get<ProductDatabase>().productDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            ProductDatabase::class.java, "Product.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(1, TimeUnit.HOURS)
            .connectTimeout(1, TimeUnit.HOURS)
            .readTimeout(1, TimeUnit.HOURS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(com.app.expertsubmissionapp.core.di.urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(ApiService::class.java)
    }

    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IProductRepository> { ProductRepository(get(), get()) }

    factory<ProductUseCase> { ProductInteractor(get()) }
}
