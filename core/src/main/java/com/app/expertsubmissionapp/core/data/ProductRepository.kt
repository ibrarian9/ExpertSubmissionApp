package com.app.expertsubmissionapp.core.data

import com.app.expertsubmissionapp.core.data.source.local.LocalDataSource
import com.app.expertsubmissionapp.core.data.source.remote.RemoteDataSource
import com.app.expertsubmissionapp.core.data.source.remote.network.ApiResponse
import com.app.expertsubmissionapp.core.data.source.remote.response.ProductsItem
import com.app.expertsubmissionapp.core.domain.model.MyProduct
import com.app.expertsubmissionapp.core.domain.repository.IProductRepository
import com.app.expertsubmissionapp.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ProductRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IProductRepository {
    override fun getAllProduct(): Flow<Resource<List<MyProduct>>> =
        object : NetworkBoundRes<List<MyProduct>, List<ProductsItem>>() {
            override fun loadFromDB(): Flow<List<MyProduct>> {
                return localDataSource.getAllProduct().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MyProduct>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ProductsItem>>> =
                remoteDataSource.getAllProduct()

            override suspend fun saveCallResult(data: List<ProductsItem>) {
                localDataSource.insertProduct(DataMapper.mapResponsesToEntities(data))
            }
        }.asFlow()

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