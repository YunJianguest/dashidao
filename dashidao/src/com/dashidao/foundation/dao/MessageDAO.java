package com.dashidao.foundation.dao;

import com.dashidao.core.base.GenericDAO;
import com.dashidao.foundation.domain.Message;

import org.springframework.stereotype.Repository;

@Repository("messageDAO")
public class MessageDAO extends GenericDAO<Message> {
}

