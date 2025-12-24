package com.khushi.projects.buildly.entity;

import com.khushi.projects.buildly.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectMember {

    @EmbeddedId
    ProjectMemberId id;

    @ManyToOne
    @MapsId("projectId")
    Project project;

    @ManyToOne
    @MapsId("userId")
    User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ProjectRole projectRole;

    Instant invitedAt;
    Instant acceptedAt;

}
