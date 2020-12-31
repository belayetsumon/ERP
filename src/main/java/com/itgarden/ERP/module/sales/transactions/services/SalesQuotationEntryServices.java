/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.transactions.services;

import com.itgarden.ERP.module.sales.model.transactions.SalesQuotationEntry;
import com.itgarden.ERP.module.sales.repository.transactions.SalesQuotationEntryRepository;
import com.itgarden.ERP.module.sales.repository.transactions.SalesQuotationItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class SalesQuotationEntryServices {

    @Autowired
    SalesQuotationEntryRepository salesQuotationEntryRepository;

    @Autowired
    SalesQuotationItemRepository salesQuotationItemRepository;

    public Page<SalesQuotationEntry> allQuation(Pageable pageable) {

        Page<SalesQuotationEntry> allquation = salesQuotationEntryRepository.findAll(pageable);

        return allquation;
    }

}
