package com.ideas2it.ems.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 *     This class handles the exceptions like EmployeeException, NoSuchElementFound
 * </p>
 *
 * @author JeevithaKesavaraj
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * <p>
     *     Handles Employee exception while exception occurs
     * </p>
     * @param employeeException    {@link EmployeeException}
     * @return  ResponseEntity     String of message and status code
     */
    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<String> handleEmployeeException(EmployeeException employeeException) {
        return new ResponseEntity<>(employeeException.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * <p>
     *     Handles NoSuchElementFound Exception while exception occurs
     * </p>
     * @param noSuchElementException {@link }
     * @return
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
        return new ResponseEntity<>(noSuchElementException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
