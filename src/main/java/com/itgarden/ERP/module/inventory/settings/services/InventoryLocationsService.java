/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.settings.services;

import com.itgarden.ERP.module.inventory.model.settings.InventoryLocations;
import com.itgarden.ERP.module.inventory.repository.settings.InventoryLocationsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class InventoryLocationsService {

    @Autowired
    InventoryLocationsRepository inventoryLocationsRepository;

    public List<InventoryLocations> activelocationlist() {

        List<InventoryLocations> locList = inventoryLocationsRepository.findAll();

        return locList;
    }

}
