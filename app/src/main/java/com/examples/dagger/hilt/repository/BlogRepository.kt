package com.examples.dagger.hilt.repository

import com.examples.dagger.hilt.db.BlogDao
import com.examples.dagger.hilt.db.CacheMapper
import com.examples.dagger.hilt.model.BlogDomainEntity
import com.examples.dagger.hilt.services.BlogService
import com.examples.dagger.hilt.services.mapper.BlogMapper
import com.examples.dagger.hilt.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class BlogRepository @Inject constructor(
    private val service: BlogService,
    private val blogDao: BlogDao,
    private val cacheMapper: CacheMapper,
    private val networkMapper: BlogMapper
) {
    suspend fun getBlog(): Flow<DataState<List<BlogDomainEntity>>> = flow {
        emit(DataState.Loading)
        try {
            val blogsList = networkMapper.mapFromEntityList(service.getAllBlogs())
            blogsList.forEach {
                blogDao.insert(cacheMapper.mapToEntity(it))
            }
            val cachedBlogs = blogDao.get().map {
                cacheMapper.mapFromEntity(it)
            }
            emit(DataState.Success(cachedBlogs))
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(DataState.Error(ex.message ?: ""))
        }
    }
}
