package com.Expense_Management_System.Exception;

import com.Expense_Management_System.Payload.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class HandlingException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalException(ResourceNotFound e, WebRequest request){
        ExceptionDto exceptionDto = new ExceptionDto(
                new Date(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
