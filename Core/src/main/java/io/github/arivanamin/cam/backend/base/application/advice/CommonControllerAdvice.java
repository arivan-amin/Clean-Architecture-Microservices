package io.github.arivanamin.cam.backend.base.application.advice;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
@NoArgsConstructor (access = AccessLevel.PRIVATE)
@Slf4j
public final class CommonControllerAdvice {

    @ExceptionHandler (MissingPathVariableException.class)
    ProblemDetail handleMissingPathVariable (MissingPathVariableException exception) {
        return CommonProblemDetails.getMissingPathVariableProblemDetail(exception);
    }

    @ExceptionHandler (HttpMessageNotReadableException.class)
    ProblemDetail handleMissingRequestBody (HttpMessageNotReadableException exception) {
        return CommonProblemDetails.getMissingRequestBodyProblemDetail(exception);
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    ProblemDetail handleMethodArgumentNotValid (MethodArgumentNotValidException exception) {
        return CommonProblemDetails.getMethodArgumentNotValidProblemDetail(exception);
    }

    @ExceptionHandler (MissingServletRequestParameterException.class)
    ProblemDetail handleMissingParameterException (
        MissingServletRequestParameterException exception) {
        return CommonProblemDetails.getMissingParameterProblemDetail(exception);
    }

    @ExceptionHandler (NoResourceFoundException.class)
    ProblemDetail handleResourceNotFound (NoResourceFoundException exception) {
        return CommonProblemDetails.getResourceNotFoundProblemDetail(exception);
    }

    @ExceptionHandler (Exception.class)
    ProblemDetail handleGeneralExceptions (Exception exception) {
        return CommonProblemDetails.getGeneralExceptionProblemDetail(exception);
    }
}
