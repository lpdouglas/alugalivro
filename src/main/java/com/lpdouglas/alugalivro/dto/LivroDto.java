package com.lpdouglas.alugalivro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroDto {

    private String id;

    @NotNull
    private String nome;

    private String autor;

    private Boolean alugado = false;
}
