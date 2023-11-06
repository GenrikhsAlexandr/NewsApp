package com.genrikhsalexandr.souresfeature.data

import com.genrikhsaleksandr.core.domain.model.Sources
import com.genrikhsalexandr.souresfeature.data.dto.SourcesListDto
import javax.inject.Inject

class SourcesDtoMapper @Inject constructor() {

    fun mapSourcesDtoToSources(sourcesListDto: SourcesListDto): List<Sources> {
        return sourcesListDto.sources.map {
            Sources(
                id = it.id,
                name = it.name,
                category = it.category,
                country = it.country,
                description = it.description
            )
        }
    }
}