package br.edu.ufape.sgu_extra_sisu_service.controller.advice;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        
        ErrorResponse resposta = new ErrorResponse();
        resposta.setTimestamp(Instant.now());
        resposta.setStatus(HttpStatus.BAD_REQUEST.value()); 
        resposta.setError("Erro de Regra de Negócio");
        resposta.setMessage(ex.getMessage()); 
        resposta.setPath(request.getRequestURI());
        
        return new ResponseEntity<>(resposta, HttpStatus.BAD_REQUEST);
    }
}