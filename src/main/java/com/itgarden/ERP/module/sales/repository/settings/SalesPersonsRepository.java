/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.repository.settings;

import com.itgarden.ERP.module.sales.model.settings.SalesPersons;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface SalesPersonsRepository extends JpaRepository<SalesPersons, Long> {
    
}
