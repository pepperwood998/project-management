package com.tuan.exercise.projman.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tuan.exercise.projman.exception.DuplicateReleaseVersionNameException;
import com.tuan.exercise.projman.exception.ReleaseNotFoundException;
import com.tuan.exercise.projman.pojo.JsonApiError;

@ControllerAdvice
public class RestResponseStatusExceptionResolver extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getParameterName() + " parameter is missing";

        return getResponseEntity(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class, DuplicateReleaseVersionNameException.class,
            ReleaseNotFoundException.class })
    public ResponseEntity<Object> handleUnhandledException(Exception ex, WebRequest request) {
        String message = "";

        if (ex instanceof ReleaseNotFoundException) {
            message = ex.getMessage();
            return getResponseEntity(message, HttpStatus.NOT_FOUND);
        }

        if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException e = (MethodArgumentTypeMismatchException) ex;
            message = e.getName() + " should be of type " + e.getRequiredType().getName();
        } else {
            message = ex.getMessage();
        }

        return getResponseEntity(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        String message = ex.getMessage();

        return getResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> getResponseEntity(String message, HttpStatus status) {
        JsonApiError apiError = new JsonApiError(status, message);
        return new ResponseEntity<>(apiError, new HttpHeaders(), status);
    }
}
