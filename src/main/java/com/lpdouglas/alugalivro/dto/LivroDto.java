package com.lpdouglas.alugalivro.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroDto {

    private String id;

    @NotNull
    @Length(max = 50)
    private String nome;

    @Length(max = 50)
    private String autor;

    private Boolean alugado = false;

    @Length(max = 250)
    private String detalhes;
}
