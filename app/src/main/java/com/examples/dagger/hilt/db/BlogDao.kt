package com.examples.dagger.hilt.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import retrofit2.http.DELETE

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogCacheEntity: BlogCacheEntity):Long

    @Query("SELECT * FROM BLOGS")
    suspend fun get():List<BlogCacheEntity>
}