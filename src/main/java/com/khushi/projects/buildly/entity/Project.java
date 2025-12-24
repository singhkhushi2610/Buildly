package com.khushi.projects.buildly.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "projects",
    indexes = {
    @Index(name = "idx_project_updated_at_desc", columnList = "updated_at DESC, deleted_at"),
    @Index(name = "idx_project_deleted_at", columnList = "deleted_at")
        }
)
public class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Builder.Default
    @Column(name="is_public", nullable = false)
    Boolean isPublic = Boolean.FALSE;

    @CreationTimestamp
    Instant createdAt;

    @UpdateTimestamp
    Instant updatedAt;

    Instant deletedAt;

}
