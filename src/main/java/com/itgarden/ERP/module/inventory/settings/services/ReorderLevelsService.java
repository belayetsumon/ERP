/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.settings.services;

import com.itgarden.ERP.module.inventory.model.settings.Items;
import com.itgarden.ERP.module.inventory.model.settings.ReorderLevels;
import com.itgarden.ERP.module.inventory.repository.settings.ReorderLevelsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class ReorderLevelsService {

    @Autowired
    ReorderLevelsRepository reorderLevelsRepository;

    public List<ReorderLevels> reorderLevelsByItem(Items itemId) {

        List<ReorderLevels> reorderList = reorderLevelsByItem(itemId);

        return reorderList;
    }

}
