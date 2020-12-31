/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.user.services;

import com.itgarden.ERP.module.user.model.Users;
import com.itgarden.ERP.module.user.ripository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author User
 */
@Component
public class UserComponent {

    @Autowired
    UsersRepository usersRepository;

    public Users curentuser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Users users = usersRepository.findByEmail(auth.getName());

        return users;
    }

}
