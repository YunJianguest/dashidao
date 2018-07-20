package com.dashidao.foundation.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dashidao.core.dao.IGenericDAO;
import com.dashidao.foundation.domain.Invoice;
import com.dashidao.foundation.service.IInvoiceService;

@Service
@Transactional
public class InvoiceService
    implements IInvoiceService {
    @Resource(name = "invoiceDAO")
    private IGenericDAO<Invoice> invoiceDAO;

    public boolean save(Invoice acc){
        try {
            this.invoiceDAO.save(acc);
            return true;
        } catch (Exception e){
        }

        return false;
    }

    
}




