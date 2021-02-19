/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.transactions.services;

import com.itgarden.ERP.module.inventory.model.transactions.InventoryAdjustments;
import com.itgarden.ERP.module.sales.model.transactions.Pos;
import com.itgarden.ERP.module.sales.repository.transactions.PosRepository;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class PosService {

    @Autowired
    PosRepository posRepository;

    public String refarance() {

        Pos refe = posRepository.findTopByOrderByInvoicenoDesc();

        Long lastid;

        if (refe != null) {

            lastid = refe.getInvoiceno() + 1;

        } else {

            lastid = 1l;
        }

        int year = Calendar.getInstance().get(Calendar.YEAR);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%05d", lastid));
//        sb.append("/");
//        sb.append(year);
        return sb.toString();
    }

    public boolean referCodeCheck(String posReference) {

        Pos ref = posRepository.findByPosReference(posReference);

        if (ref != null) {

            return true;

        } else {
            return false;
        }

    }

}
