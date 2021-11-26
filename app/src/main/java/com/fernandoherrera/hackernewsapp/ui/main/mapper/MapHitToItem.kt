package com.fernandoherrera.hackernewsapp.ui.main.mapper

import com.fernandoherrera.hackernewsapp.data.mapper.Mapper
import com.fernandoherrera.hackernewsapp.domain.model.Hit
import com.fernandoherrera.hackernewsapp.ui.main.model.HitItem

class MapHitToItem : Mapper<Hit, HitItem> {
    override fun map(from: Hit): HitItem {
        return HitItem(
            from.objectId,
            from.storyTitle ?: "",
            from.author,
            from.createdAt,
            from.storyUrl ?: ""
        )
    }
}
