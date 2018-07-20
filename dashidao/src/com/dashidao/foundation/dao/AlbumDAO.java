package com.dashidao.foundation.dao;

import com.dashidao.core.base.GenericDAO;
import com.dashidao.foundation.domain.Album;

import org.springframework.stereotype.Repository;

@Repository("albumDAO")
public class AlbumDAO extends GenericDAO<Album> {
}

