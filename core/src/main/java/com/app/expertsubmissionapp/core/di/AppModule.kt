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
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val hostname = "fakestoreapi.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/NhyY+Wdb5NLalYvJmG9JbPeZ+6LgXjCLmTILNyHwKIA=")
            .add(hostname, "sha256/81Wf12bcLlFHQAfJluxnzZ6Frg+oJ9PWY/Wrwur8viQ=")
            .add(hostname, "sha256/hxqRlPTu1bMS/0DITB1SSu0vd4u/8l8TjPgfaAp63Gc=")
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
