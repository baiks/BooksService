package com.book.service.exception.advice;


import com.book.service.exception.CustomException;
import com.book.service.exception.ErrorResponse;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Log4j2
@ControllerAdvice
public class CustControllerAdvice extends MessageSourceAdviceCtrl {
    protected CustControllerAdvice(MessageSource messageSource) {
        super(messageSource);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleExceptionDataIntegrityViolationException(
            DataIntegrityViolationException e) {
        String message = e.getCause().getLocalizedMessage();
        log.error("Invalid input " + message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), message));
    }

    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<ErrorResponse> handleExceptionMismatchedInputException(MismatchedInputException e) {
        String message = "Invalid Data types";
        log.error("Mismatched Input Exception..." + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), message));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleExceptionCustomException(CustomException e) {
        log.error("Custom Exception... " + e.getMessage());
        e.printStackTrace();
        log.error(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), e.getMessage()));
    }

}
