package io.github.arivanamin.lms.backend.outbox.storage.audit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@SuppressWarnings ("PublicConstructor")
@MappedSuperclass
@Getter
@EntityListeners (AuditingEntityListener.class)
@ToString
public class AuditFields {

    @CreatedDate
    @Column (name = "created_at", nullable = false, updatable = false)
    Instant createdAt;

    @LastModifiedDate
    @Column (name = "updated_at", nullable = false)
    Instant updatedAt;
}
