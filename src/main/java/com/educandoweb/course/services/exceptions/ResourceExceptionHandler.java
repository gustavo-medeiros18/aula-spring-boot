package com.educandoweb.course.services.exceptions;

import com.educandoweb.course.resources.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ControllerAdvice annotation allows you to handle
 * exceptions across the whole application, not just
 * for a specific controller.
 * <p>
 * HttpServletRequest is an interface that defines methods
 * to provide client request information to a servlet.
 */
@ControllerAdvice
public class ResourceExceptionHandler {
  /**
   * ExceptionHandler annotation allows you to handle
   * specific exceptions in specific methods.
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StandardError> resourceNotFoundException(
      ResourceNotFoundException e,
      HttpServletRequest request
  ) {
    String error = "Resource not found";
    HttpStatus status = HttpStatus.NOT_FOUND;

    StandardError err = new StandardError(
        System.currentTimeMillis(),
        status.value(),
        error,
        e.getMessage(),
        request.getRequestURI()
    );

    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<StandardError> databaseException(
      DatabaseException e,
      HttpServletRequest request
  ) {
    String error = "Database error";
    HttpStatus status = HttpStatus.BAD_REQUEST;

    StandardError err = new StandardError(
        System.currentTimeMillis(),
        status.value(),
        error,
        e.getMessage(),
        request.getRequestURI()
    );

    return ResponseEntity.status(status).body(err);
  }
}
