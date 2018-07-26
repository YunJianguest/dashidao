package com.dashidao.foundation.dao;

import com.dashidao.core.base.GenericDAO;
import com.dashidao.foundation.domain.Goods;

import org.springframework.stereotype.Repository;

@Repository("locationDAO")
public class LocationDAO extends GenericDAO<Goods> {
}

