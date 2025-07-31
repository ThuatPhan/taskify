package org.example.taskify.repository;

import org.example.taskify.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
    boolean existsByName(String name);

    List<Permission> findAllByNameIn(Collection<String> name);
}
