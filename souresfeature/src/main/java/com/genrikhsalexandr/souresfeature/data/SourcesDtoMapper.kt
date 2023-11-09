package com.genrikhsalexandr.souresfeature.data

import com.genrikhsaleksandr.core.domain.model.Source
import com.genrikhsalexandr.souresfeature.data.dto.sources.SourcesListDto
import javax.inject.Inject

class SourcesDtoMapper @Inject constructor() {

    fun mapSourcesDtoToSources(sourcesListDto: SourcesListDto): List<Source> {
        return sourcesListDto.sources.map {
            Source(
                sourceId = it.id,
                sourceName = it.name,
                category = it.category,
                country = it.country,
                language = it.language
            )
        }
    }
}