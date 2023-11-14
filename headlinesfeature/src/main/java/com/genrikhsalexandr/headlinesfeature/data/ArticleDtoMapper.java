package com.genrikhsalexandr.headlinesfeature.data;

import com.genrikhsaleksandr.core.domain.model.Article;
import com.genrikhsalexandr.headlinesfeature.data.dto.NewsListDto;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleDtoMapper {

    public List<Article> mapNewsListDtoToListArticle(NewsListDto articleDto) {
        return articleDto.getArticles().stream().map(it -> new Article(
                it.getSource().getName(),
                it.getTitle(),
                it.getUrlToImage(),
                it.getPublishedAt(),
                it.getContent(),
                it.getSource().getId(),
                it.getUrl(),
                0L
                )).collect(Collectors.toList());
    }
}