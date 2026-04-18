package com.example.spring_boot.exceptions;

import com.example.spring_boot.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

//to centralize exception at one place
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);
    //EXCEPTION HANDLING METHOD
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> nullPointerException(
            Exception exception
    )
    {
        logger.error("Null Argument: ",exception);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad request")
                .errorMessage(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundException(
            Exception exception
    )
    {
        logger.error("Error when finding user: ",exception);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("User is not present")
                .errorMessage(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyPresent.class)
    public ResponseEntity<ErrorResponse> userAlreadyPresentException(
            Exception exception
    )
    {
        logger.error("Error when creating user: ",exception);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("User Already Present")
                .errorMessage(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
            Exception exception
    )
    {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .error("Method not allowed")
                .errorMessage(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse,HttpStatus.METHOD_NOT_ALLOWED);
    }

    /*
    {
    "timestamp": "2025-11-08T06:16:08.452+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/user"
     }
     */

    /*
    TRACE
    DEBUG
    INFO
    WARN
    ERROR
     */

    //SLF4J (Simple Logging Facade for Java)->LogBack or Log4J by default spring boot uses LogBack
}
