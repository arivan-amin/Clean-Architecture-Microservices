package com.cinemayan.catalog.infrastructure.storage.movie;

import com.cinemayan.catalog.domain.movie.entity.Movie;
import com.cinemayan.catalog.domain.movie.storage.MovieSearchCriteria;
import com.cinemayan.catalog.domain.movie.storage.MovieStorage;
import com.cinemayan.core.domain.pagination.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Slf4j
public class MovieJpaStorage implements MovieStorage {

    private final MovieRepository repository;

    @Transactional (readOnly = true)
    @Override
    public PaginatedResponse<Movie> findAll (MovieSearchCriteria params,
                                             PaginationCriteria criteria) {
        MovieSpecification specification =
            new MovieSpecification(params.getSearchQuery(), params.getStartDate(),
                params.getEndDate());

        PageRequest pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        Page<MovieEntity> page = repository.findAll(specification, pageable);

        List<Movie> elements = fetchAllStudentsAndMapToEntity(page.getContent());
        return PaginatedResponse.of(extractPageData(page), elements);
    }

    private static List<Movie> fetchAllStudentsAndMapToEntity (List<MovieEntity> page) {
        return page.stream()
            .map(MovieEntity::toDomain)
            .toList();
    }

    public PageData extractPageData (Page<MovieEntity> page) {
        return PageData.of(page.getNumber(), page.getTotalPages(), page.getSize(),
            page.getTotalElements());
    }

    @Transactional (readOnly = true)
    @Override
    public Optional<Movie> findById (UUID id) {
        return repository.findById(id)
            .map(MovieEntity::toDomain);
    }

    @Transactional (readOnly = true)
    @Override
    public Optional<Movie> findByEmail (String email) {
        return repository.findByEmail(email)
            .map(MovieEntity::toDomain);
    }

    @Transactional
    @Override
    public UUID create (Movie student) {
        return repository.save(MovieEntity.fromDomain(student))
            .getId();
    }

    @Transactional
    @Override
    public void update (Movie updatedStudent) {
        MovieEntity storedStudent =
            MovieEntity.fromDomain(findById(updatedStudent.getId()).orElseThrow());
        updateChangedFields(updatedStudent, storedStudent);
        repository.save(storedStudent);
    }

    private static void updateChangedFields (Movie updatedStudent, MovieEntity storedStudent) {
    }

    @Transactional
    @Override
    public void delete (UUID id) {
        repository.deleteById(id);
    }
}
