package com.codeLab.authLab.repositories;

import com.codeLab.authLab.model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<SiteUser, Long> {
    SiteUser findByUsername(String username);

}

