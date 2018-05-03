package com.dengqi7.mybatis.service.impl;

import com.dengqi7.mybatis.dao.ArticleMapper;
import com.dengqi7.mybatis.domain.Article;
import com.dengqi7.mybatis.service.ArticleService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author dengqi
 * @date 2018-04-26
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    private Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Override
    public Article selectOne(long id) {
        return articleMapper.selectOne(id);
    }

    @Override
    public void insert(Article article) {
        article.setCreateDate(new Date());
        if(StringUtils.isEmpty(article.getContent())){
            logger.warn("content:" + article.getContent());
            article.setContent(null);
            article.setCreateDate(null);
        }
        articleMapper.insert(article);
    }
}
