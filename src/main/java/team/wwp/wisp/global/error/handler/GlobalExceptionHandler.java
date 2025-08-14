package team.wwp.wisp.global.error.handler;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.NonUniqueResultException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import team.wwp.wisp.global.error.exception.WispException;
import team.wwp.wisp.global.error.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(WispException.class)
    public ResponseEntity<ErrorResponse> handleGsmcException(WispException e) {
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(new ErrorResponse(e.getErrorCode().getMessage(), e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(NonUniqueResultException.class)
    public ResponseEntity<ErrorResponse> handleNonUniqueResultException(NonUniqueObjectException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse("Duplicate results detected, Please check your request", HttpStatus.CONFLICT.value()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Data integrity violation occurred. Please check your request.", HttpStatus.BAD_REQUEST.value()));
    }
}
