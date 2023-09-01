package com.example.gitrepoinspector.exceptionHandler;

import com.example.gitrepoinspector.model.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@Component
@ControllerAdvice
public class MyErrorHandlers {
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    protected ResponseEntity handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex, HttpServletRequest request) {
        String errorMessage = "accept: " + request.getHeader("accept") + " header is not supported";
        return new ResponseEntity<>(new ApiError(ex.getStatusCode().value(), errorMessage), ex.getStatusCode());
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException ex) {
        return new ResponseEntity<>(new ApiError(ex.getStatusCode().value(), ex.getReason()), ex.getStatusCode());
    }
}
