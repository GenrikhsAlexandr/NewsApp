package com.genrikhsalexandr.souresfeature.data

import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsalexandr.souresfeature.data.dto.articlessource.SourceNewsListDto
import javax.inject.Inject

class ArticlesSourceDtoMapper @Inject constructor() {

    fun mapArticlesSourceDto(sourcesNewsListDto: SourceNewsListDto): List<Article> {
        return sourcesNewsListDto.articles.map {
            Article(
                id = it.source.id,
                title = it.title,
                source = it.source.name,
                urlToImage = null,
                content = null,
                publishedAt =it.publishedAt,
                url = it.url

            )
        }
    }
}