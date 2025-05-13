package com.basic.UserCRUD.exceptions;

import com.basic.UserCRUD.dtos.ErrorResponseDTOs;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception, WebRequest webRequest) {
        ErrorResponseDTOs errorResponseDTOs = new ErrorResponseDTOs(exception.getMessage(), String.format("Path is %s", webRequest.getDescription(false)), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTOs, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnableToProcessException.class)
    public final ResponseEntity<Object> handleUnableToProcessException(UnableToProcessException exception, WebRequest webRequest) {
        ErrorResponseDTOs errorResponseDTOs = new ErrorResponseDTOs(exception.getMessage(), String.format("Path is %s", webRequest.getDescription(false)), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTOs, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest webRequest) {
       List<String> errorMessages = exception.getBindingResult().getFieldErrors().stream()
               .map(DefaultMessageSourceResolvable::getDefaultMessage)
               .collect(Collectors.toList());

       String errorMessage = String.join(" || ", errorMessages);
        ErrorResponseDTOs errorResponseDTOs = new ErrorResponseDTOs(errorMessage, String.format("Error(s) found %d", errorMessages.size()), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTOs, HttpStatus.BAD_REQUEST);
    }
}
