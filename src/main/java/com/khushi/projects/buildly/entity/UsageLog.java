package com.khushi.projects.buildly.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class UsageLog {

    Long id;

    User user;
    Project project;

    String action;

    Integer tokensUsed;

    String metadata; // JSON of {model, prompt..}

    Instant createdAt;
}
