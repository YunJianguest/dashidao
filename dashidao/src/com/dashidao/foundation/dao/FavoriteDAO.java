package com.dashidao.foundation.dao;

import com.dashidao.core.base.GenericDAO;
import com.dashidao.foundation.domain.Favorite;

import org.springframework.stereotype.Repository;

@Repository("favoriteDAO")
public class FavoriteDAO extends GenericDAO<Favorite> {
}

