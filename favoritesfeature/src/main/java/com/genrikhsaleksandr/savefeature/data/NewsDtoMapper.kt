package com.genrikhsaleksandr.savefeature.data

import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.savefeature.data.database.ArticleDbModel
import com.genrikhsaleksandr.savefeature.data.dto.NewsListDto

class NewsDtoMapper {

    fun mapNewsListDtoToListArticle(articleDto: NewsListDto): List<Article> {
        return articleDto.articles.map {
            Article(
                source = it.source.name,
                title = it.title,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt,
                content = it.content
            )
        }
    }

    fun mapArticleDbModelToArticle(articleDbModel: ArticleDbModel) = Article(
        publishedAt = articleDbModel.publishedAt,
        source = articleDbModel.source,
        title = articleDbModel.title,
        urlToImage = articleDbModel.urlToImage,
        content = articleDbModel.content
    )

    fun mapArticleToArticleDbModel(article: Article) = ArticleDbModel(
        publishedAt = article.publishedAt,
        source = article.source,
        title = article.title,
        urlToImage = article.urlToImage,
        content = article.content
    )
}
