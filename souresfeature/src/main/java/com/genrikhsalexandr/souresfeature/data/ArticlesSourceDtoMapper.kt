package com.genrikhsalexandr.souresfeature.data

import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsalexandr.souresfeature.data.dto.articlessource.SourceNewsListDto
import javax.inject.Inject

class ArticlesSourceDtoMapper @Inject constructor() {

    fun mapArticlesSourceDto(sourcesNewsListDto: SourceNewsListDto): List<Article> {
        return sourcesNewsListDto.articles.map {
            Article(
                sourceId = it.source.id,
                title = it.title,
                sourceName = it.source.name,
                urlToImage = null,
                content = null,
                publishedAt =it.publishedAt,
                url = it.url

            )
        }
    }
}