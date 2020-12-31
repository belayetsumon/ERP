/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.transactions.services;

import com.itgarden.ERP.module.sales.model.transactions.SalesDelivery;
import com.itgarden.ERP.module.sales.repository.transactions.SalesDeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class SalesDeliveryService {

    @Autowired
    SalesDeliveryRepository salesDeliveryRepository;

    public Page<SalesDelivery> allDelivery(Pageable pageable) {

        Page<SalesDelivery> allDelivery = salesDeliveryRepository.findAll(pageable);

        return allDelivery;
    }

}
