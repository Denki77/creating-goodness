package ru.leadersofdigital.dobro.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        MarketError err = new MarketError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleInvalidDataException(InvalidDataException e) {
        MarketError err = new MarketError(HttpStatus.BAD_REQUEST.value(), e.getMessages());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        MarketError err = new MarketError(HttpStatus.BAD_REQUEST.value(), fieldErrors.stream().map(i -> i.getField()).collect(Collectors.toList()));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleValidateData(ValidateException e) {
        MarketError err = new MarketError(HttpStatus.BAD_REQUEST.value(), e.getMessages());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
