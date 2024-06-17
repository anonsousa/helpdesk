package br.com.it.helpdesk.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> manageInvalidArgs(MethodArgumentNotValidException e){
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        for (FieldError field : fieldErrors){
            errorMap.put(field.getField(), field.getDefaultMessage());
        }

        return errorMap;
    }
}
