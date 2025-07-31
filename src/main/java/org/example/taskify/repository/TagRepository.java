package org.example.taskify.repository;

import org.example.taskify.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    boolean existsByName(String name);
}
