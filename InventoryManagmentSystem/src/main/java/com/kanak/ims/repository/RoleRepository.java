package com.kanak.ims.repository;

import com.kanak.ims.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);

    Optional<Role> findById(int id);
}
