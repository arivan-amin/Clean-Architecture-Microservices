package io.github.arivanamin.cinemayan.catalog.domain.persistence;

import io.github.arivanamin.cinemayan.catalog.domain.entity.GradeLevel;
import io.github.arivanamin.cinemayan.catalog.domain.entity.StudentStatus;
import io.github.arivanamin.cinemayan.core.domain.gender.Gender;
import lombok.Value;

import java.time.Instant;
import java.util.List;

@Value
public class ReadStudentsParams {

    String searchQuery;
    Gender gender;
    List<StudentStatus> statuses;
    List<GradeLevel> gradeLevels;
    Instant startDate;
    Instant endDate;
}
