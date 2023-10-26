package com.genrikhsaleksandr.savefeature.data

import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.savefeature.data.dto.NewsListDto

class NewsDtoMapper {

    fun mapNewsListDtoToListArticle(newsListDto: NewsListDto): List<Article> {
        val articles = mutableListOf<Article>()
        val article = Article(
            title = newsListDto.title,
            author = newsListDto.author,
            urlToImage = newsListDto.urlToImage,
            content = newsListDto.content,
            publishedAt = newsListDto.publishedAt
        )
        articles.add(article)
        return articles
    }
}
