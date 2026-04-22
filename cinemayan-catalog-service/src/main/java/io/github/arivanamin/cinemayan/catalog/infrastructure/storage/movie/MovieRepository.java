package io.github.arivanamin.cinemayan.catalog.infrastructure.storage.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface MovieRepository
    extends JpaRepository<MovieEntity, UUID>, JpaSpecificationExecutor<MovieEntity> {

    Optional<MovieEntity> findByEmail (String email);
}
