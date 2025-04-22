package io.github.arivanamin.scm.backend.notification.storage;

import io.github.arivanamin.scm.backend.common.domain.persistence.StorageAuditData;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table (name = "notifications")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString (callSuper = true)
public class JpaNotification extends StorageAuditData {

    @Id
    @UuidGenerator
    UUID id;

    @NotBlank
    private String recipient;

    @NotBlank
    private String content;
}
