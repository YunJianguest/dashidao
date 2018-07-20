package com.dashidao.foundation.dao;

import com.dashidao.core.base.GenericDAO;
import com.dashidao.foundation.domain.User;

import org.springframework.stereotype.Repository;

@Repository("userDAO")
public class UserDAO extends GenericDAO<User> {
}

