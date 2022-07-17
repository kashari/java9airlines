package com.sda.java9.finalproject.security.repository;

import com.sda.java9.finalproject.security.entity.AppRole;
import com.sda.java9.finalproject.security.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    Optional<AppRole> findByName(RoleName name);
}
