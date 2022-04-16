package com.examples.dagger.hilt.di

import com.examples.dagger.hilt.db.BlogDao
import com.examples.dagger.hilt.db.CacheMapper
import com.examples.dagger.hilt.repository.BlogRepository
import com.examples.dagger.hilt.services.BlogService
import com.examples.dagger.hilt.services.mapper.BlogMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object BlogRepositoryModule {

    @Singleton
    @Provides
    fun providesBlogRepository(
        blogService: BlogService,
        blogDao: BlogDao,
        blogMapper: BlogMapper,
        cacheMapper: CacheMapper
    ): BlogRepository {
        return BlogRepository(
            blogService,
            blogDao,
            cacheMapper,
            blogMapper
        )
    }
}