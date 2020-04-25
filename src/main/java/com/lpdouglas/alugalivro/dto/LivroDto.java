package com.lpdouglas.alugalivro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
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

    @Length(max = 50
    )
    private String autor;

    private Boolean alugado = false;
}
