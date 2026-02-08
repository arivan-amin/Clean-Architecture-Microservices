package io.github.arivanamin.lms.backend.student.storage.specification;

import io.github.arivanamin.lms.backend.student.storage.entity.StudentEntity;
import jakarta.persistence.criteria.*;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification implements Specification<StudentEntity> {

    @Override
    public @Nullable Predicate toPredicate (Root<StudentEntity> root, CriteriaQuery<?> query,
                                            CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
