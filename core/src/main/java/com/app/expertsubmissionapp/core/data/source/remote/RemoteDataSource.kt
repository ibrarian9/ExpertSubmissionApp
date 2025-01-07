package com.app.expertsubmissionapp.core.data.source.remote

import android.util.Log
import com.app.expertsubmissionapp.core.data.source.remote.network.ApiResponse
import com.app.expertsubmissionapp.core.data.source.remote.network.ApiService
import com.app.expertsubmissionapp.core.data.source.remote.response.ProductsItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(
    private val api: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getAllProduct(): Flow<ApiResponse<List<ProductsItem>>> {
        return flow {
            try {
                val response = api.getAllProduct()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(ioDispatcher)
    }
}