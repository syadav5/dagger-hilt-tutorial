package com.examples.dagger.hilt.db

import com.examples.dagger.hilt.model.BlogDomainEntity
import com.examples.dagger.hilt.services.mapper.EntityMapper
import javax.inject.Inject

/**
 * Mapping class to map between domain model object and database object.
 */
class CacheMapper @Inject constructor(): EntityMapper<BlogCacheEntity, BlogDomainEntity> {
    override fun mapFromEntity(entity: BlogCacheEntity): BlogDomainEntity {
        return BlogDomainEntity(
            entity.body,
            entity.category,
            entity.image,
            entity.id, entity.title

        )
    }

    override fun mapToEntity(domainModel: BlogDomainEntity): BlogCacheEntity {
        return BlogCacheEntity(
            domainModel.id,
            domainModel.title,
            domainModel.body,
            domainModel.category,
            domainModel.image
        )
    }

    fun mapCacheEntityList(entities:List<BlogCacheEntity>): List<BlogDomainEntity>{
        return entities.map {mapFromEntity(it)}
    }
}