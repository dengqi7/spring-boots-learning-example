package com.dengqi7.mybatis.controller;

import com.dengqi7.mybatis.domain.Article;
import com.dengqi7.mybatis.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dengqi
 * @date 2018-04-26
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/id/{id}")
    public String getActicleById(@PathVariable("id") Long id){
        System.out.println("入参："+ id);
        Article article = articleService.selectOne(id);
        System.out.println("返回数据："+ article);
        return article.toString();
    }

    @PostMapping("/save")
    public String save(@RequestBody Article article){
        System.out.println("入参："+ article);
        articleService.insert(article);
        return "保存成功";
    }


}
