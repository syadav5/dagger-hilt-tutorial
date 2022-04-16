package com.examples.dagger.hilt.di

import android.content.Context
import androidx.room.Room
import com.examples.dagger.hilt.db.BlogDao
import com.examples.dagger.hilt.db.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun providesBlogDatabase(@ApplicationContext context: Context): BlogDatabase {
        return Room.databaseBuilder(context, BlogDatabase::class.java, BlogDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesBlogDao(blogDatabase: BlogDatabase): BlogDao {
        return blogDatabase.blogDao()
    }
}