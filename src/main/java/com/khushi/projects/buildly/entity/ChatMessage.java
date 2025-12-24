package com.khushi.projects.buildly.entity;

import com.khushi.projects.buildly.enums.MessageRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ChatMessage {

    Long id;

    ChatSession chatSession;

    MessageRole messageRole;

    String content;

    String toolCalls; // JSON Array

    Integer tokensUsed;

    Instant createdAt;

}
