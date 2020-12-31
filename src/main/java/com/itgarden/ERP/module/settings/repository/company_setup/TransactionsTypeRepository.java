/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.repository.company_setup;

import com.itgarden.ERP.module.settings.model.company_setup.TransactionsType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface TransactionsTypeRepository extends JpaRepository<TransactionsType, Long> {

    TransactionsType findBySlug(String slug);

}
