package com.lpdouglas.alugalivro.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class LivroException extends RuntimeException {
    private String message;
}
