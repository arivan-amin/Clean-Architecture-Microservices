package io.github.arivanamin.lms.backend.student.storage;

import io.github.arivanamin.lms.backend.base.core.pagination.*;
import io.github.arivanamin.lms.backend.student.core.entity.Student;
import io.github.arivanamin.lms.backend.student.core.persistence.StudentStorage;
import io.github.arivanamin.lms.backend.student.storage.entity.StudentEntity;
import io.github.arivanamin.lms.backend.student.storage.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Slf4j
public class DatabaseStudentStorage implements StudentStorage {

    private final StudentRepository repository;

    @Override
    public PaginatedResponse<Student> findAll (PaginationCriteria criteria) {
        Page<StudentEntity> page = repository.findAll(of(criteria.getPage(), criteria.getSize()));

        List<Student> elements = fetchAllStudentsAndMapToEntity(page.getContent());
        return PaginatedResponse.of(extractPageData(page), elements);
    }

    private static List<Student> fetchAllStudentsAndMapToEntity (List<StudentEntity> page) {
        return page.stream()
            .map(StudentEntity::toDomain)
            .toList();
    }

    public PageData extractPageData (Page<StudentEntity> page) {
        return PageData.of(page.getNumber(), page.getTotalPages(), page.getSize(),
            page.getTotalElements());
    }

    @Override
    public Optional<Student> findById (UUID id) {
        return repository.findById(id)
            .map(StudentEntity::toDomain);
    }

    @Override
    public Optional<Student> findByEmail (String email) {
        return repository.findByEmail(email)
            .map(StudentEntity::toDomain);
    }

    @Transactional
    @Override
    public UUID create (Student student) {
        return repository.save(StudentEntity.fromDomain(student))
            .getId();
    }

    @Transactional
    @Override
    public void update (Student updatedStudent) {
        StudentEntity storedStudent =
            StudentEntity.fromDomain(findById(updatedStudent.getId()).orElseThrow());
        updateChangedFields(updatedStudent, storedStudent);
        repository.save(storedStudent);
    }

    private static void updateChangedFields (Student updatedStudent, StudentEntity storedStudent) {
        storedStudent.setFirstName(updatedStudent.getFirstName());
        storedStudent.setLastName(updatedStudent.getLastName());
        storedStudent.setEmail(updatedStudent.getEmail());
        storedStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
        storedStudent.setGender(updatedStudent.getGender());
        storedStudent.setAddress(updatedStudent.getAddress());
    }

    @Transactional
    @Override
    public void delete (UUID id) {
        repository.deleteById(id);
    }
}
