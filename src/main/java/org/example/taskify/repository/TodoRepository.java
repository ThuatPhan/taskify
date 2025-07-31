package org.example.taskify.repository;

import org.example.taskify.entity.Todo;
import org.example.taskify.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String> {
    Page<Todo> findAllByCreatedBy(User user, Pageable pageable);
}
