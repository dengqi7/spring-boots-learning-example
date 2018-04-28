package com.dengqi7.mybatis.controller;

import com.dengqi7.mybatis.domain.Author;
import com.dengqi7.mybatis.service.AuthorService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengqi
 * @date 2018-04-28
 */
@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @RequestMapping("/id/{id}")
    public Author getOneById(@PathVariable("id") Long id){
        System.out.println("入参：" + id );
        Author author = authorService.selectAuthorByInId(id);
        System.out.println("返回：" + author);
        return author;
    }

    @RequestMapping("/info/{id}")
    public Author getAuthorAllInfoById(@PathVariable("id") Long id){
        System.out.println("入参：" + id );
        Author author = authorService.selectAuthorAllInfoById(id);
        System.out.println("返回：" + author);
        return author;
    }

    @RequestMapping("/save")
    public void save(@RequestBody Author author){
        System.out.println("入参：" + author );
        authorService.insert(author);
        System.out.println("保存成功");
    }

    @RequestMapping("/update")
    public void update(@RequestBody Author author){
        System.out.println("入参：" + author );
        authorService.insert(author);
        System.out.println("保存成功");
    }

}
