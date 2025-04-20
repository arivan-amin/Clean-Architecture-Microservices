package io.github.arivanamin.cam.backend.base.domain.persistence;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@SuppressWarnings ("PublicConstructor")
@Getter
@Setter
@MappedSuperclass
@EntityListeners (AuditingEntityListener.class)
@ToString
public class StorageAuditData {

    @CreatedDate
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime updatedAt;
}
