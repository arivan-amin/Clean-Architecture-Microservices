package io.github.arivanamin.scm.backend.base.core.aspects;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
@Slf4j
// todo 1/26/25 - maybe this wrapper is unnecessary 
public final class ExecuteAndLogPerformance {

    public static <T> T executeThrowable (ThrowableExecutor<T> executor) throws Throwable {
        return executor.execute();
    }

    @FunctionalInterface
    public interface ThrowableExecutor<T> {

        T execute () throws Throwable;
    }
}
