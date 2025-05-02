package io.github.arivanamin.scm.backend.common.domain.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.function.Function;

import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class CriteriaHelper {

    public static <D, E> Example<E> createExampleFromEntity (D domainObject,
                                                             Function<D, E> mapper) {
        return Example.of(mapper.apply(domainObject), createExampleMatcher("id", "recordedAt"));
    }

    private static ExampleMatcher createExampleMatcher (String... ignoredFields) {
        return ExampleMatcher.matching()
            .withIgnorePaths(ignoredFields)
            .withIgnoreNullValues()
            .withIgnoreCase()
            .withStringMatcher(CONTAINING);
    }

    public static <D, E> Example<E> createExampleFromEntity (D domainObject, Function<D, E> mapper,
                                                             String... ignoredFields) {
        return Example.of(mapper.apply(domainObject), createExampleMatcher(ignoredFields));
    }
}
