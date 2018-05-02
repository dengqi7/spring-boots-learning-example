package com.generator.domain;

import java.util.Date;

public class Article {
    private Integer id;

    private String title;

    private Date createDate;

    private Date createTime;

    private Integer authorId;

    private String content;

    public Article(Integer id, String title, Date createDate, Date createTime, Integer authorId, String content) {
        this.id = id;
        this.title = title;
        this.createDate = createDate;
        this.createTime = createTime;
        this.authorId = authorId;
        this.content = content;
    }

    public Article() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}