package com.webapp.usermanagementservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private UUID uuid;
    private LocalDateTime timestamp;
    private String message;
    private ErrorMessage error;
}
