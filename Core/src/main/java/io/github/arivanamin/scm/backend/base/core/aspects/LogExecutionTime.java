package io.github.arivanamin.scm.backend.base.core.aspects;

import java.lang.annotation.*;

@Target (ElementType.METHOD)
@Retention (RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {

}
