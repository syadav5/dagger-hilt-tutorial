package com.examples.dagger.hilt.model

data class BlogDomainEntity(
    val body: String,
    val category: String,
    val image: String,
    val id: Int,
    val title: String
)