package com.ufrn.SIGZoo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.ufrn.SIGZoo.exception.RNException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "Recurso não encontrado",
            ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RNException.class)
    public ResponseEntity<ErrorResponse> handleRNException(RNException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST; 
        
        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            "Erro na Regra de Negócio",
            ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, status);
    }    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        System.err.println("Erro inesperado: " + ex.getMessage()); 
        ex.printStackTrace();

        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Erro Interno do Servidor",
            "Ocorreu um erro inesperado. Tente novamente mais tarde."
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }    

    public static class ErrorResponse {
        private int status;
        private String error;
        private String message;

        public ErrorResponse(int status, String error, String message) {
            this.status = status;
            this.error = error;
            this.message = message;
        }

        public int getStatus() { return status; }
        public String getError() { return error; }
        public String getMessage() { return message; }
    }    
}
