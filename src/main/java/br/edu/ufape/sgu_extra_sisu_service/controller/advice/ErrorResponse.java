package br.edu.ufape.sgu_extra_sisu_service.controller.advice;

import java.time.Instant;
import java.util.Map;

import lombok.Data;

@Data
public class ErrorResponse {
    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> detail;
 }