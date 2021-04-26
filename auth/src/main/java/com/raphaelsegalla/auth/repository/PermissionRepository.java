package com.raphaelsegalla.auth.repository;

import com.raphaelsegalla.auth.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByDescription(String description);
}
