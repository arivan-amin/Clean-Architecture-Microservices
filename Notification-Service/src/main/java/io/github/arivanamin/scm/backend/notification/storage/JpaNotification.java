package io.github.arivanamin.scm.backend.notification.storage;

import io.github.arivanamin.scm.backend.common.domain.persistence.StorageAuditData;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table (name = "notifications")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults (level = AccessLevel.PRIVATE)
@ToString (callSuper = true)
public class JpaNotification extends StorageAuditData {

    @Id
    @UuidGenerator
    UUID id;

    @NotBlank
    String recipient;

    @NotBlank
    String content;
}
