//package com.dengqi7.controller;
//
//import com.dengqi7.mybatis.domain.Article;
//import com.dengqi7.mybatis.service.ArticleService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author dengqi
// * @date 2018-04-26
// */
//@RestController
//@RequestMapping("/article")
//public class ArticleController {
//
//    @Autowired
//    ArticleService articleService;
//
//    Logger logger = LoggerFactory.getLogger(ArticleController.class);
//
//    @GetMapping("/id/{id}")
//    public String getActicleById(@PathVariable("id") Long id){
//        logger.info("入参："+ id);
//        Article article = articleService.selectOne(id);
//        logger.info("返回数据："+ article);
//        return article.toString();
//    }
//
//    @PostMapping("/save")
//    public String save(@RequestBody Article article){
//        logger.info("入参："+ article);
//        articleService.insert(article);
//        logger.info("保存成功");
//        return "保存成功";
//    }
//
//
//}
