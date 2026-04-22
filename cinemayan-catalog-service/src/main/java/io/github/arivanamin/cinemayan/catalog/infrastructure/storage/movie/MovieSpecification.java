package io.github.arivanamin.cinemayan.catalog.infrastructure.storage.movie;

import io.github.arivanamin.cinemayan.catalog.domain.entity.GradeLevel;
import io.github.arivanamin.cinemayan.catalog.domain.entity.StudentStatus;
import io.github.arivanamin.cinemayan.core.domain.gender.Gender;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.util.*;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class MovieSpecification implements Specification<MovieEntity> {

    private final String searchQuery;
    private final Gender gender;
    private final List<StudentStatus> statuses;
    private final List<GradeLevel> gradeLevels;
    private final Instant startDate;
    private final Instant endDate;

    @Override
    public Predicate toPredicate (Root<MovieEntity> root, CriteriaQuery<?> query,
                                  CriteriaBuilder criteria) {

        List<Predicate> predicates =
            Stream.of(bySearchQuery(root, criteria), byStatus(root), byGradeLevel(root),
                    byGender(root, criteria), byDateOfBirthStart(root, criteria),
                    byDateOfBirthEnd(root, criteria), byCreatedAtStart(root, criteria),
                    byCreatedAtEnd(root, criteria))
                .filter(Objects::nonNull)
                .toList();

        return criteria.and(predicates.toArray(Predicate[]::new));
    }

    private Predicate bySearchQuery (Root<MovieEntity> root, CriteriaBuilder criteria) {
        return Optional.ofNullable(searchQuery)
            .map(_ -> getSearchQueryPattern(root, criteria))
            .orElse(null);
    }

    private Predicate byStatus (Root<MovieEntity> root) {
        return Optional.ofNullable(statuses)
            .map(_ -> root.get("status")
                .in(statuses))
            .orElse(null);
    }

    private Predicate byGradeLevel (Root<MovieEntity> root) {
        return Optional.ofNullable(gradeLevels)
            .map(_ -> root.get("gradeLevel")
                .in(gradeLevels))
            .orElse(null);
    }

    private Predicate byGender (Root<MovieEntity> root, CriteriaBuilder criteria) {
        return Optional.ofNullable(gender)
            .map(_ -> criteria.equal(root.get("gender"), gender))
            .orElse(null);
    }

    private Predicate byDateOfBirthStart (Root<MovieEntity> root, CriteriaBuilder criteria) {
        return Optional.ofNullable(startDate)
            .map(_ -> criteria.greaterThanOrEqualTo(root.get("dateOfBirth"), startDate))
            .orElse(null);
    }

    private Predicate byDateOfBirthEnd (Root<MovieEntity> root, CriteriaBuilder criteria) {
        return Optional.ofNullable(endDate)
            .map(_ -> criteria.lessThan(root.get("dateOfBirth"), endDate))
            .orElse(null);
    }

    private Predicate byCreatedAtStart (Root<MovieEntity> root, CriteriaBuilder criteria) {
        return Optional.ofNullable(startDate)
            .map(_ -> criteria.greaterThanOrEqualTo(root.get("createdAt"), startDate))
            .orElse(null);
    }

    private Predicate byCreatedAtEnd (Root<MovieEntity> root, CriteriaBuilder criteria) {
        return Optional.ofNullable(endDate)
            .map(_ -> criteria.lessThan(root.get("createdAt"), endDate))
            .orElse(null);
    }

    private Predicate getSearchQueryPattern (Root<MovieEntity> root, CriteriaBuilder criteria) {
        String likePattern = "%" + searchQuery.toLowerCase(Locale.ENGLISH) + "%";
        return criteria.or(criteria.like(criteria.lower(root.get("firstName")), likePattern),
            criteria.like(criteria.lower(root.get("lastName")), likePattern),
            criteria.like(criteria.lower(root.get("email")), likePattern),
            criteria.like(criteria.lower(root.get("address")), likePattern),
            criteria.like(criteria.lower(root.get("phoneNumber")), likePattern));
    }
}
