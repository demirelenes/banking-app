package com.demirelenes.bankingapp.exception;

import com.demirelenes.bankingapp.currency.model.CurrencyType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        var body = httpBodyCreator(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidCurrencyException.class, CurrencyMismatchException.class})
    public ResponseEntity<Object> handleCurrencyException(RuntimeException ex) {
        var body = httpBodyCreator(ex);
        body.put("validCurrencies", CurrencyType.values());

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler({IOException.class, SAXException.class, ParserConfigurationException.class, URISyntaxException.class})
    public ResponseEntity<Object> handleIOException(Exception ex) {
        var body = httpBodyCreator(ex);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        var body = httpBodyCreator(ex);

        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        body.put("errors", errors);

        return ResponseEntity.badRequest().body(body);
    }

    private Map<String, Object> httpBodyCreator(Exception ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("errors", errors);

        return body;
    }
}
