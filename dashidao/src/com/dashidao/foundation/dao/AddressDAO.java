package com.dashidao.foundation.dao;

import com.dashidao.core.base.GenericDAO;
import com.dashidao.foundation.domain.Address;

import org.springframework.stereotype.Repository;

@Repository("addressDAO")
public class AddressDAO extends GenericDAO<Address> {
}

