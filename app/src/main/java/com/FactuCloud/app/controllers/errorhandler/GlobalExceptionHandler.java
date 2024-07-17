package com.FactuCloud.app.controllers.errorhandler;

import com.FactuCloud.app.dto.response.error.ErrorResponse;
import com.FactuCloud.app.exceptions.BadRequestException;
import com.FactuCloud.app.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        // Obtiene el mensaje de error de la excepción
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();

        // Construye la respuesta de error personalizada
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);

        // Devuelve la respuesta de error personalizada
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(RuntimeException ex, WebRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("Error", HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex, WebRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("Error", HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // Loguea el error si es necesario
        ex.printStackTrace(); // Aquí puedes usar tu propio mecanismo de logging

        // Construye una respuesta de error con el código de estado 409 (Conflict)
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Error de integridad de datos: " + ex.getMessage());
    }

}
