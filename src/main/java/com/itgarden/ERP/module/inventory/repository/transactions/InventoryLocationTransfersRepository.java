/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.repository.transactions;

import com.itgarden.ERP.module.inventory.model.transactions.InventoryLocationTransfers;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface InventoryLocationTransfersRepository extends JpaRepository<InventoryLocationTransfers, Long> {
   InventoryLocationTransfers findTopByOrderByIdDesc();
}
