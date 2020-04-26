package com.lpdouglas.alugalivro.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class LoginDto {

    @NotNull(message = "Email não pode ser nulo")
    @Email(message = "Email fora do formato correto")
    private String email;

    private String password;
}
