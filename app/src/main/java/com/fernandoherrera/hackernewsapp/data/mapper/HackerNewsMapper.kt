package com.fernandoherrera.hackernewsapp.data.mapper

import com.fernandoherrera.hackernewsapp.data.model.local.HitEntity
import com.fernandoherrera.hackernewsapp.data.model.remote.HitDto
import com.fernandoherrera.hackernewsapp.domain.model.Hit
import com.fernandoherrera.hackernewsapp.utils.DateUtil

class MapHitDtoToEntity : Mapper<HitDto, HitEntity> {
    override fun map(from: HitDto): HitEntity {
        return HitEntity(
            from.objectID,
            from.storyTitle ?: from.title,
            from.author,
            DateUtil.fromStringToDateTime(from.createdAt),
            from.storyUrl
        )
    }
}

class MapHitEntityToDomain : Mapper<HitEntity, Hit> {
    override fun map(from: HitEntity): Hit {
        return Hit(
            from.objectId,
            from.storyTitle,
            from.author,
            from.createdAt,
            from.storyUrl
        )
    }
}
