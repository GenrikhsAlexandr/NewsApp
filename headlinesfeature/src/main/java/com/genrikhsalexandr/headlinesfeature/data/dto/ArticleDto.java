package com.genrikhsalexandr.headlinesfeature.data.dto;
public class ArticleDto {

    private SourceDto source;
    private String title;
    private String urlToImage;
    private String publishedAt;
    private String content;
    private String url;
    private Long id = 0L;

    public ArticleDto(SourceDto source, String title, String urlToImage, String publishedAt, String content, String url) {
        this.source = source;
        this.title = title;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.url = url;
    }

    public SourceDto getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }
}