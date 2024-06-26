package com.genrikhsaleksandr.core.data

import com.genrikhsaleksandr.core.data.database.ArticleDbModel
import com.genrikhsaleksandr.core.data.dto.ArticlesListDto
import com.genrikhsaleksandr.core.domain.model.Article
import javax.inject.Inject

class ArticlesListDtoMapper @Inject constructor() {

    fun mapArticlesListDtoToListArticle(articleDto: ArticlesListDto): List<Article> {
        return articleDto.articles.map {
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

    fun mapArticleDbModelToArticle(articleDbModel: ArticleDbModel) = Article(
        publishedAt = articleDbModel.publishedAt,
        sourceName = articleDbModel.sourceName,
        title = articleDbModel.title,
        urlToImage = articleDbModel.urlToImage,
        content = articleDbModel.content,
        sourceId = articleDbModel.sourceId,
        url = articleDbModel.url,
        id = articleDbModel.id
    )

    fun mapArticleToArticleDbModel(article: Article) = ArticleDbModel(
        publishedAt = article.publishedAt,
        sourceName = article.sourceName,
        title = article.title,
        urlToImage = article.urlToImage,
        content = article.content,
        sourceId = article.sourceId,
        url = article.url,
        id = article.id
    )
}