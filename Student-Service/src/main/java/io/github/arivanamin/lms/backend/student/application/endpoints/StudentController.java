package io.github.arivanamin.lms.backend.student.application.endpoints;

import io.github.arivanamin.lms.backend.base.core.pagination.PaginationCriteria;
import io.github.arivanamin.lms.backend.student.application.request.CreateStudentRequest;
import io.github.arivanamin.lms.backend.student.application.request.UpdateStudentRequest;
import io.github.arivanamin.lms.backend.student.application.response.*;
import io.github.arivanamin.lms.backend.student.core.command.*;
import io.github.arivanamin.lms.backend.student.core.query.ReadStudentByIdQuery;
import io.github.arivanamin.lms.backend.student.core.query.ReadStudentsQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static io.github.arivanamin.lms.backend.student.application.config.StudentApiURLs.*;
import static io.github.arivanamin.lms.backend.student.application.config.StudentCaches.GET_ALL_STUDENTS_CACHE;
import static io.github.arivanamin.lms.backend.student.application.config.StudentCaches.GET_STUDENT_BY_ID_CACHE;

@Tag (name = "Student Controller")
@RestController
@RequiredArgsConstructor
@Slf4j
class StudentController {

    private final ReadStudentsQuery readQuery;
    private final ReadStudentByIdQuery readByIdQuery;
    private final CreateStudentCommand createCommand;
    private final UpdateStudentCommand updateCommand;
    private final DeleteStudentCommand deleteCommand;

    @GetMapping (GET_STUDENTS_URL)
    @Cacheable (GET_ALL_STUDENTS_CACHE)
    @Operation (summary = "Get a list of students")
    @ResponseStatus (HttpStatus.OK)
    public ReadStudentsResponse getAllStudents (@Valid PaginationCriteria criteria) {
        return ReadStudentsResponse.of(readQuery.execute(criteria));
    }

    @GetMapping (GET_STUDENT_BY_ID_URL)
    @Cacheable (GET_STUDENT_BY_ID_CACHE)
    @Operation (summary = "Get a single student by id")
    @ResponseStatus (HttpStatus.OK)
    public StudentResponse getStudentById (@PathVariable UUID id) {
        return StudentResponse.of(readByIdQuery.execute(id));
    }

    @PostMapping (CREATE_STUDENT_URL)
    @Operation (summary = "Creates a student")
    @CacheEvict (cacheNames = { GET_ALL_STUDENTS_CACHE, GET_STUDENT_BY_ID_CACHE },
        allEntries = true)
    @ResponseStatus (HttpStatus.CREATED)
    public CreateStudentResponse createStudent (@RequestBody @Valid CreateStudentRequest request) {
        UUID createdStudentId = createCommand.execute(request.toDomainEntity());
        return CreateStudentResponse.of(createdStudentId);
    }

    @PutMapping (UPDATE_STUDENT_URL)
    @Operation (summary = "Updates a student")
    @CacheEvict (cacheNames = { GET_ALL_STUDENTS_CACHE, GET_STUDENT_BY_ID_CACHE },
        allEntries = true)
    @ResponseStatus (HttpStatus.OK)
    public void updateStudent (@PathVariable UUID id,
                               @RequestBody @Valid UpdateStudentRequest request) {
        updateCommand.execute(request.toEntity(id));
    }

    @DeleteMapping (DELETE_STUDENT_URL)
    @Operation (summary = "Deletes a student")
    @CacheEvict (cacheNames = { GET_ALL_STUDENTS_CACHE, GET_STUDENT_BY_ID_CACHE },
        allEntries = true)
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteStudent (@PathVariable UUID id) {
        deleteCommand.execute(id);
    }
}
