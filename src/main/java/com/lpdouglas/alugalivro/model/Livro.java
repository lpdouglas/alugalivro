package com.lpdouglas.alugalivro.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Livro {
    private String id;
    private String nome;
    private String autor;
    private Boolean alugado = false;
}
