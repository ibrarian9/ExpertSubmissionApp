package com.app.expertsubmissionapp.core.di

import androidx.room.Room
import com.app.expertsubmissionapp.core.BuildConfig
import com.app.expertsubmissionapp.core.data.ProductRepository
import com.app.expertsubmissionapp.core.data.source.local.LocalDataSource
import com.app.expertsubmissionapp.core.data.source.local.room.ProductDatabase
import com.app.expertsubmissionapp.core.data.source.remote.RemoteDataSource
import com.app.expertsubmissionapp.core.data.source.remote.network.ApiService
import com.app.expertsubmissionapp.core.domain.repository.IProductRepository
import com.app.expertsubmissionapp.core.domain.usecase.ProductInteractor
import com.app.expertsubmissionapp.core.domain.usecase.ProductUseCase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("password".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            ProductDatabase::class.java, "Product.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    single {
        // Logging with level
        val logging = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        val hostname = "fakestoreapi.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/84DWgxXF++4g+IXNtankij3ZjZbbpfs+f8JYu7LmWTI=")
            .add(hostname, "sha256/kIdp6NNEd8wsugYyyIYFsi1ylMCED3hZbSR8ZFsa/A4=")
            .add(hostname, "sha256/mEflZT5enoR1FuXLgYYGqnVEoZvmf9c2bVBpiOjYQ0c=")
            .build()

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(1, TimeUnit.HOURS)
            .connectTimeout(1, TimeUnit.HOURS)
            .readTimeout(1, TimeUnit.HOURS)
            .certificatePinner(certificatePinner)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
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
