package com.arivanamin.healthcare.backend.notification.core.entity;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    private String id;
    private String content;
    private long timestamp;
}
