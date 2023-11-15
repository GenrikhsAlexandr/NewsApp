package com.genrikhsalexandr.searchfeature.data.dto

import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.domain.model.Source
import javax.inject.Inject

class SearchDtoMapper @Inject constructor() {
    fun mapSearchListDtoToArticleList(searchListDto: SearchListDto): List<Article> {
        return searchListDto.articles.map {
            Article(
                sourceName = it.source.name,
                title = it.title,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt,
                content = it.content,
                sourceId = it.source.id,
                url = it.url
            )
        }
    }
}