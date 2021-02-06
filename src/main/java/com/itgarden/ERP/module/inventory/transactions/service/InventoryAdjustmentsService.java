/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.transactions.service;

import com.itgarden.ERP.module.inventory.model.settings.Items;
import com.itgarden.ERP.module.inventory.model.transactions.InventoryAdjustments;
import com.itgarden.ERP.module.inventory.repository.transactions.InventoryAdjustmentsRepository;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class InventoryAdjustmentsService {

    @Autowired
    InventoryAdjustmentsRepository inventoryAdjustmentsRepository;

    public String refarance() {

        InventoryAdjustments refe = inventoryAdjustmentsRepository.findTopByOrderByIdDesc();

        Long lastid;

        if (refe != null) {

            lastid = refe.getId() + 1;

        } else {

            lastid = 1l;
        }

        int year = Calendar.getInstance().get(Calendar.YEAR);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%05d", lastid));
        sb.append("/");
        sb.append(year);
        return sb.toString();
    }

    public boolean referCodeCheck(String inAdReference) {

        InventoryAdjustments ref = inventoryAdjustmentsRepository.findByInAdReference(inAdReference);

        if (ref != null) {

            return true;

        } else {
            return false;
        }

    }
}
