package com.sda.java9.finalproject.repository;

import com.sda.java9.finalproject.security.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository  extends JpaRepository<AppRole, Long> {
}
