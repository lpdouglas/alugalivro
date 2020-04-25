package com.lpdouglas.alugalivro.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidUserException extends RuntimeException {
    private String message;
}
