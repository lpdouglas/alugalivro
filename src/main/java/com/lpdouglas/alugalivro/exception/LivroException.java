package com.lpdouglas.alugalivro.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LivroException extends RuntimeException {
    private String message;
}
