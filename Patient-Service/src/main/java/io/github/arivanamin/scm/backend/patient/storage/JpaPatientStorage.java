package io.github.arivanamin.scm.backend.patient.storage;

import io.github.arivanamin.scm.backend.base.domain.pagination.*;
import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.patient.core.persistence.PatientStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static io.github.arivanamin.scm.backend.base.application.config.ModelMapperConditions.getConditionToSkipAuditDataFields;
import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Slf4j
public class JpaPatientStorage implements PatientStorage {

    private final PatientRepository repository;

    @Override
    public PaginatedResponse<Patient> findAll (PaginationCriteria criteria) {
        Page<JpaPatient> page = repository.findAll(of(criteria.getPage(), criteria.getSize()));

        List<Patient> elements = fetchAllPatientsAndMapToEntity(page.getContent());
        return PaginatedResponse.of(extractPageData(page), elements);
    }

    private static List<Patient> fetchAllPatientsAndMapToEntity (List<JpaPatient> page) {
        return page.stream()
            .map(JpaPatient::toDomain)
            .toList();
    }

    public PageData extractPageData (Page<JpaPatient> page) {
        return PageData.of(page.getNumber(), page.getTotalPages(), page.getSize(),
            page.getTotalElements());
    }

    @Override
    public Optional<Patient> findById (UUID id) {
        return repository.findById(id)
            .map(JpaPatient::toDomain);
    }

    @Override
    public Optional<Patient> findByEmail (String email) {
        return repository.findByEmail(email)
            .map(JpaPatient::toDomain);
    }

    @Transactional
    @Override
    public UUID create (Patient patient) {
        return repository.save(JpaPatient.fromDomain(patient))
            .getId();
    }

    @Transactional
    @Override
    public void update (Patient updatedPatient) {
        JpaPatient storedPatient =
            JpaPatient.fromDomain(findById(updatedPatient.getId()).orElseThrow());
        updateChangedFields(updatedPatient, storedPatient);
    }

    private static void updateChangedFields (Patient patient, JpaPatient patientFromStorage) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
            .setPropertyCondition(getConditionToSkipAuditDataFields());
        modelMapper.map(patient, patientFromStorage);
    }

    @Transactional
    @Override
    public void delete (UUID id) {
        repository.deleteById(id);
    }
}
