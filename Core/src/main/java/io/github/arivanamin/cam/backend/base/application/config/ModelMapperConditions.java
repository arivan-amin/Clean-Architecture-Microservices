package io.github.arivanamin.cam.backend.base.application.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.Condition;

import java.util.Set;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class ModelMapperConditions {

    public static Condition<?, ?> getConditionToSkipAuditDataFields () {
        Set<String> fieldsToSkip = Set.of("createdAt", "updatedAt");
        return context -> {
            if (context.getMapping() == null || context.getMapping()
                .getLastDestinationProperty() == null) {
                return true;
            }
            String destinationField = context.getMapping()
                .getLastDestinationProperty()
                .getName();
            return !fieldsToSkip.contains(destinationField);
        };
    }
}
