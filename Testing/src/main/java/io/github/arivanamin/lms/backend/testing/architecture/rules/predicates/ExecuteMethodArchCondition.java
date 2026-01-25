package io.github.arivanamin.lms.backend.testing.architecture.rules.predicates;

import com.tngtech.archunit.core.domain.*;
import com.tngtech.archunit.lang.*;

import java.util.List;

public class ExecuteMethodArchCondition extends ArchCondition<JavaClass> {

    private final String commandsAndQueriesMethodName;

    public ExecuteMethodArchCondition (String commandsAndQueriesMethodName) {
        super("have exactly one public method named 'execute'");
        this.commandsAndQueriesMethodName = commandsAndQueriesMethodName;
    }

    @Override
    public void check (JavaClass javaClass, ConditionEvents events) {
        List<JavaMethod> publicMethods = javaClass.getAllMethods()
            .stream()
            .filter(method -> !method.getOwner()
                .isEquivalentTo(Object.class))
            .filter(method -> method.getModifiers()
                .contains(JavaModifier.PUBLIC))
            .toList();

        long executeMethodCount = publicMethods.stream()
            .filter(method -> commandsAndQueriesMethodName.equals(method.getName()))
            .count();

        if (publicMethods.size() != 1 || executeMethodCount != 1) {
            events.add(new SimpleConditionEvent(javaClass, false,
                javaClass + " should have exactly one public method, and it must be named " +
                    "'execute'."));
        }
    }
}
