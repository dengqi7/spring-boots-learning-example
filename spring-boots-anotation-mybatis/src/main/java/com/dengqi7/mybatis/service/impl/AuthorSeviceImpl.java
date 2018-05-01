package com.dengqi7.mybatis.service.impl;

import com.dengqi7.mybatis.dao.AuthorMapper;
import com.dengqi7.mybatis.domain.Author;
import com.dengqi7.mybatis.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dengqi
 * @date 2018-04-28
 */
@Service
public class AuthorSeviceImpl implements AuthorService {

    @Autowired
    AuthorMapper authorMapper;

    @Override
    public Author selectAuthorByInId(long id) {
        return authorMapper.selectAuthorByInId(id);
    }

    @Override
    public Author selectAuthorAllInfoById(long id) {
        return authorMapper.selectAuthorAllInfoById(id);
    }

    @Override
    public void insert(Author author) {
        authorMapper.insert(author);
    }

    @Override
    public void update(Author author) {
        authorMapper.update(author);
    }

    @Override
    public void delete(long id) {
        authorMapper.delete(id);
    }
}
