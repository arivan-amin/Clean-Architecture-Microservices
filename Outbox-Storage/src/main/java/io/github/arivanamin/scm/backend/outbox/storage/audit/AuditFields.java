package io.github.arivanamin.scm.backend.outbox.storage.audit;

import jakarta.persistence.*;
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
public class AuditFields {

    @CreatedDate
    @Column (name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column (name = "updated_at", nullable = false)
    LocalDateTime updatedAt;
}
