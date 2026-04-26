package com.cinemayan.catalog.domain.movie.storage;

import io.github.arivanamin.cinemayan.catalog.domain.movie.entity.GradeLevel;
import io.github.arivanamin.cinemayan.catalog.domain.movie.entity.StudentStatus;
import io.github.arivanamin.cinemayan.core.domain.gender.Gender;
import lombok.Value;

import java.time.Instant;
import java.util.List;

@Value
public class MovieSearchCriteria {

    String searchQuery;
    Gender gender;
    List<StudentStatus> statuses;
    List<GradeLevel> gradeLevels;
    Instant startDate;
    Instant endDate;
}
