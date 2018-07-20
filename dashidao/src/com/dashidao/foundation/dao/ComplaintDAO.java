package com.dashidao.foundation.dao;

import com.dashidao.core.base.GenericDAO;
import com.dashidao.foundation.domain.Complaint;

import org.springframework.stereotype.Repository;

@Repository("complaintDAO")
public class ComplaintDAO extends GenericDAO<Complaint> {
}

