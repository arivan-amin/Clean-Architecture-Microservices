package io.github.arivanamin.lms.backend.patient.storage;

import io.github.arivanamin.lms.backend.base.core.pagination.*;
import io.github.arivanamin.lms.backend.patient.core.entity.Patient;
import io.github.arivanamin.lms.backend.patient.core.persistence.PatientStorage;
import io.github.arivanamin.lms.backend.patient.storage.entity.PatientEntity;
import io.github.arivanamin.lms.backend.patient.storage.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Slf4j
public class DatabasePatientStorage implements PatientStorage {

    private final PatientRepository repository;

    @Override
    public PaginatedResponse<Patient> findAll (PaginationCriteria criteria) {
        Page<PatientEntity> page = repository.findAll(of(criteria.page(), criteria.size()));

        List<Patient> elements = fetchAllPatientsAndMapToEntity(page.getContent());
        return PaginatedResponse.of(extractPageData(page), elements);
    }

    private static List<Patient> fetchAllPatientsAndMapToEntity (List<PatientEntity> page) {
        return page.stream()
            .map(PatientEntity::toDomain)
            .toList();
    }

    public PageData extractPageData (Page<PatientEntity> page) {
        return PageData.of(page.getNumber(), page.getTotalPages(), page.getSize(),
            page.getTotalElements());
    }

    @Override
    public Optional<Patient> findById (UUID id) {
        return repository.findById(id)
            .map(PatientEntity::toDomain);
    }

    @Override
    public Optional<Patient> findByEmail (String email) {
        return repository.findByEmail(email)
            .map(PatientEntity::toDomain);
    }

    @Transactional
    @Override
    public UUID create (Patient patient) {
        return repository.save(PatientEntity.fromDomain(patient))
            .getId();
    }

    @Transactional
    @Override
    public void update (Patient updatedPatient) {
        PatientEntity storedPatient =
            PatientEntity.fromDomain(findById(updatedPatient.getId()).orElseThrow());
        updateChangedFields(updatedPatient, storedPatient);
        repository.save(storedPatient);
    }

    private static void updateChangedFields (Patient updatedPatient, PatientEntity storedPatient) {
        storedPatient.setFirstName(updatedPatient.getFirstName());
        storedPatient.setLastName(updatedPatient.getLastName());
        storedPatient.setEmail(updatedPatient.getEmail());
        storedPatient.setDateOfBirth(updatedPatient.getDateOfBirth());
        storedPatient.setGender(updatedPatient.getGender());
        storedPatient.setAddress(updatedPatient.getAddress());
    }

    @Transactional
    @Override
    public void delete (UUID id) {
        repository.deleteById(id);
    }
}
