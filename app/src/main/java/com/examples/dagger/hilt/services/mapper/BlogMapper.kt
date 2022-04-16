package com.examples.dagger.hilt.services.mapper

import com.examples.dagger.hilt.model.BlogDomainEntity
import com.examples.dagger.hilt.services.BlogNetworkEntity
import javax.inject.Inject

/**
 * Injectable BlogMapper
 * Convets network obect to/from domain object
 */
class BlogMapper @Inject constructor() : EntityMapper<BlogNetworkEntity, BlogDomainEntity> {
    override fun mapFromEntity(entity: BlogNetworkEntity): BlogDomainEntity {
        return BlogDomainEntity(
            body = entity.body,
            category = entity.category,
            image = entity.image,
            id = entity.id,
            title = entity.title
        )
    }

    override fun mapToEntity(domainModel: BlogDomainEntity): BlogNetworkEntity {
        return BlogNetworkEntity(
            domainModel.body,
            domainModel.category,
            domainModel.image,
            domainModel.id,
            domainModel.title
        )
    }

    fun mapFromEntityList(entityList: List<BlogNetworkEntity>) =
        entityList.map { mapFromEntity(entity = it) }
}