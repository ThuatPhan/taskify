package org.example.taskify.repository;

import java.util.Collection;
import java.util.List;

import org.example.taskify.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
    boolean existsByName(String name);

    List<Permission> findAllByNameIn(Collection<String> name);
}
