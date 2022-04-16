package com.examples.dagger.hilt.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data Class Representing table row in the database.
 */
@Entity(tableName = "blogs")
data class BlogCacheEntity(
 @PrimaryKey(autoGenerate = false)
 @ColumnInfo(name = "id") val id:Int,
 @ColumnInfo(name = "title") val title:String,
 @ColumnInfo(name = "body") val body:String,
 @ColumnInfo(name = "category") val category:String,
 @ColumnInfo(name = "image") val image:String,
 )