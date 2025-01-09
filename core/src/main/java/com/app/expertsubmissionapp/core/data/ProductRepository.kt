package com.app.expertsubmissionapp.core.data

import com.app.expertsubmissionapp.core.data.source.local.LocalDataSource
import com.app.expertsubmissionapp.core.data.source.remote.RemoteDataSource
import com.app.expertsubmissionapp.core.data.source.remote.network.ApiResponse
import com.app.expertsubmissionapp.core.domain.model.MyProduct
import com.app.expertsubmissionapp.core.domain.repository.IProductRepository
import com.app.expertsubmissionapp.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ProductRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IProductRepository {
    override fun getAllProduct(): Flow<Resource<List<MyProduct>>> = flow {
        emit(Resource.Loading())


        try {

            val localProducts = localDataSource.getAllProduct().firstOrNull()

            if (!localProducts.isNullOrEmpty()) {

                emitAll(localDataSource.getAllProduct().map { products ->
                    Resource.Success(DataMapper.mapEntitiesToDomain(products))
                })
            } else {

                val remoteProducts = remoteDataSource.getAllProduct().first()

                if (remoteProducts is ApiResponse.Success) {
                    val syncedProducts = DataMapper.mapResponsesToEntities(remoteProducts.data)
                    localDataSource.insertProduct(syncedProducts)

                    emitAll(localDataSource.getAllProduct().map { products ->
                        Resource.Success(DataMapper.mapEntitiesToDomain(products))
                    })
                } else if (remoteProducts is ApiResponse.Error) {
                    emit(Resource.Error(remoteProducts.errorMessage))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error fetching products"))
        }
    }

    override fun getDetailProduct(id: Int): Flow<MyProduct> {
        return localDataSource.getDetailProduct(id).map {
            DataMapper.mapEntityToDomain(it)
        }
    }

    override fun getFavoriteProduct(): Flow<List<MyProduct>> {
        return localDataSource.getFavoriteProduct().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteProduct(product: MyProduct, state: Boolean) {
        val productEntity = DataMapper.mapDomainToEntity(product)
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavoriteProduct(productEntity, state)
        }
    }
}