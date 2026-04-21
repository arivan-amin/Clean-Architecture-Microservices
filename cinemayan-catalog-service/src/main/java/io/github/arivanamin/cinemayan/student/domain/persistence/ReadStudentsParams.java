package io.github.arivanamin.cinemayan.student.domain.persistence;

import io.github.arivanamin.cinemayan.core.domain.gender.Gender;
import io.github.arivanamin.cinemayan.student.domain.entity.GradeLevel;
import io.github.arivanamin.cinemayan.student.domain.entity.StudentStatus;
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
