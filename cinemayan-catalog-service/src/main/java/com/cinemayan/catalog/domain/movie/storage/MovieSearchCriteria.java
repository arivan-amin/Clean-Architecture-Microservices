package com.cinemayan.catalog.domain.movie.storage;

import com.cinemayan.core.domain.gender.Gender;
import lombok.Value;

import java.time.Instant;

@Value
public class MovieSearchCriteria {

    String searchQuery;
    Gender gender;
    // List<StudentStatus> statuses;
    // List<GradeLevel> gradeLevels;
    Instant startDate;
    Instant endDate;
}
