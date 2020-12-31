/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.user.ripository;

import com.itgarden.ERP.module.user.model.Role;
import com.itgarden.ERP.module.user.model.Status;
import com.itgarden.ERP.module.user.model.Users;
import java.util.List;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

    Users findByMobile(String mobile);

    List<Users> findByRole(Role role);

    List<Users> findByRoleAndStatusOrderByIdDesc(Role role, Status status);

    Users findByEmailAndStatus(String email, Status status);

    Users findByIdAndStatus(Long id, Status status);

    List<Users> findByStatus(Status status);

}