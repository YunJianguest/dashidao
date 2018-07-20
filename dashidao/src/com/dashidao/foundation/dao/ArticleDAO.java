package com.dashidao.foundation.dao;

import com.dashidao.core.base.GenericDAO;
import com.dashidao.foundation.domain.Article;

import org.springframework.stereotype.Repository;

@Repository("articleDAO")
public class ArticleDAO extends GenericDAO<Article> {
}
