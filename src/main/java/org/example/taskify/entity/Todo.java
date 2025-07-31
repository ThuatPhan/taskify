package org.example.taskify.entity;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "todos")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false)
    String title;

    @Column(length = 500)
    String description;

    @Column(updatable = false)
    @CreationTimestamp
    Instant createdAt;

    @UpdateTimestamp
    Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    User createdBy;

    @ManyToMany
    @JoinTable(
            name = "todo_tags",
            joinColumns = @JoinColumn(name = "todo_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    Set<Tag> tags = new HashSet<>();
}
