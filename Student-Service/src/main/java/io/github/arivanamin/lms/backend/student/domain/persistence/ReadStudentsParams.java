package io.github.arivanamin.lms.backend.student.domain.persistence;

import io.github.arivanamin.lms.backend.core.domain.gender.Gender;
import io.github.arivanamin.lms.backend.student.domain.entity.GradeLevel;
import io.github.arivanamin.lms.backend.student.domain.entity.StudentStatus;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class ReadStudentsParams {

    String searchQuery;
    Gender gender;
    List<StudentStatus> statuses;
    List<GradeLevel> gradeLevels;
    LocalDate startDate;
    LocalDate endDate;
}
