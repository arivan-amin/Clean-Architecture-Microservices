package io.github.arivanamin.lms.backend.core.domain.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.function.Function;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class MappingUtility {

    public static <T, R> R mapIfNotNull (T value, Function<T, R> mapper) {
        return Optional.ofNullable(value)
            .map(mapper)
            .orElse(null);
    }

    public static <T, R> R mapOrDefault (T value, Function<T, R> mapper, R defaultValue) {
        return Optional.ofNullable(value)
            .map(mapper)
            .orElse(defaultValue);
    }
}
