package io.github.arivanamin.cinemayan.core.domain.audit;

import java.lang.annotation.*;

@Target (ElementType.FIELD)
@Retention (RetentionPolicy.RUNTIME)
@Documented
public @interface SensitiveData {

}
