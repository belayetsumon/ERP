/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.fixed_assets.repository.settings;

import com.itgarden.ERP.module.fixed_assets.model.settings.FixedAssets;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface FixedAssetsRepository extends JpaRepository<FixedAssets, Long> {
    
}
