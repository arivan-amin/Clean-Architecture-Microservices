package io.github.arivanamin.lms.backend.student.application.endpoints;

import io.github.arivanamin.lms.backend.core.domain.gender.Gender;
import io.github.arivanamin.lms.backend.core.domain.pagination.PaginationCriteria;
import io.github.arivanamin.lms.backend.student.application.request.CreateStudentRequest;
import io.github.arivanamin.lms.backend.student.application.request.UpdateStudentRequest;
import io.github.arivanamin.lms.backend.student.application.response.*;
import io.github.arivanamin.lms.backend.student.domain.command.create.*;
import io.github.arivanamin.lms.backend.student.domain.command.delete.DeleteStudentCommand;
import io.github.arivanamin.lms.backend.student.domain.command.delete.DeleteStudentCommandInput;
import io.github.arivanamin.lms.backend.student.domain.command.update.UpdateStudentCommand;
import io.github.arivanamin.lms.backend.student.domain.command.update.UpdateStudentCommandInput;
import io.github.arivanamin.lms.backend.student.domain.entity.GradeLevel;
import io.github.arivanamin.lms.backend.student.domain.entity.StudentStatus;
import io.github.arivanamin.lms.backend.student.domain.persistence.ReadStudentsParams;
import io.github.arivanamin.lms.backend.student.domain.query.readbyid.*;
import io.github.arivanamin.lms.backend.student.domain.query.readbyspec.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static io.github.arivanamin.lms.backend.core.domain.dates.TimestampHelper.toLocalDate;
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
    public ReadStudentsResponse getAllStudents (
        @RequestParam (name = "search", required = false) String searchQuery,
        @RequestParam (name = "gender", required = false) Gender gender,
        @RequestParam (name = "status", required = false) List<StudentStatus> statuses,
        @RequestParam (name = "gradeLevel", required = false) List<GradeLevel> gradeLevels,
        @RequestParam (required = false) Long startDate,
        @RequestParam (required = false) Long endDate, @Valid PaginationCriteria criteria) {
        ReadStudentsParams params =
            new ReadStudentsParams(searchQuery, gender, statuses, gradeLevels,
                toLocalDate(startDate), toLocalDate(endDate));
        ReadStudentsQueryInput input = new ReadStudentsQueryInput(params, criteria);
        
        ReadStudentsQueryOutput output = readQuery.execute(input);
        return ReadStudentsResponse.of(output.getStudents());
    }

    @GetMapping (GET_STUDENT_BY_ID_URL)
    @Cacheable (GET_STUDENT_BY_ID_CACHE)
    @Operation (summary = "Get a single student by id")
    @ResponseStatus (HttpStatus.OK)
    public StudentResponse getStudentById (@PathVariable UUID id) {
        ReadStudentByIdQueryInput input = new ReadStudentByIdQueryInput(id);
        ReadStudentByIdQueryOutput output = readByIdQuery.execute(input);
        return StudentResponse.of(output.getStudent());
    }

    @PostMapping (CREATE_STUDENT_URL)
    @Operation (summary = "Creates a student")
    @CacheEvict (cacheNames = { GET_ALL_STUDENTS_CACHE, GET_STUDENT_BY_ID_CACHE },
        allEntries = true)
    @ResponseStatus (HttpStatus.CREATED)
    public CreateStudentResponse createStudent (@RequestBody @Valid CreateStudentRequest request) {
        CreateStudentCommandInput input = new CreateStudentCommandInput(request.toDomain());
        CreateStudentCommandOutput output = createCommand.execute(input);
        return CreateStudentResponse.of(output.getId());
    }

    @PutMapping (UPDATE_STUDENT_URL)
    @Operation (summary = "Updates a student")
    @CacheEvict (cacheNames = { GET_ALL_STUDENTS_CACHE, GET_STUDENT_BY_ID_CACHE },
        allEntries = true)
    @ResponseStatus (HttpStatus.OK)
    public void updateStudent (@PathVariable UUID id,
                               @RequestBody @Valid UpdateStudentRequest request) {
        UpdateStudentCommandInput input = new UpdateStudentCommandInput(request.toEntity(id));
        updateCommand.execute(input);
    }

    @DeleteMapping (DELETE_STUDENT_URL)
    @Operation (summary = "Deletes a student")
    @CacheEvict (cacheNames = { GET_ALL_STUDENTS_CACHE, GET_STUDENT_BY_ID_CACHE },
        allEntries = true)
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteStudent (@PathVariable UUID id) {
        DeleteStudentCommandInput input = new DeleteStudentCommandInput(id);
        deleteCommand.execute(input);
    }
}
