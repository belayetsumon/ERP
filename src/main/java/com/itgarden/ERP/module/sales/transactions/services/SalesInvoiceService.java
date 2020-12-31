/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.transactions.services;

import com.itgarden.ERP.module.sales.model.transactions.SalesInvoice;
import com.itgarden.ERP.module.sales.repository.transactions.SalesInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class SalesInvoiceService {

    @Autowired
    SalesInvoiceRepository salesInvoiceRepository;

    public Page<SalesInvoice> allInvoice(Pageable pageable) {

        Page<SalesInvoice> allInvoice = salesInvoiceRepository.findAll(pageable);

        return allInvoice;
    }

}
