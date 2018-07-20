package com.dashidao.foundation.dao;

import com.dashidao.core.base.GenericDAO;
import com.dashidao.foundation.domain.Payment;

import org.springframework.stereotype.Repository;

@Repository("paymentDAO")
public class PaymentDAO extends GenericDAO<Payment> {
}
