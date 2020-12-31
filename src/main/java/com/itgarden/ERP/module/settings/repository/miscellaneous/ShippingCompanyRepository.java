/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.repository.miscellaneous;

import com.itgarden.ERP.module.settings.model.miscellaneous.ShippingCompany;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface ShippingCompanyRepository extends JpaRepository<ShippingCompany, Long> {
    
}
