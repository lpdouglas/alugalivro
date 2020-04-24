package com.lpdouglas.alugalivro.validations;

import com.lpdouglas.alugalivro.models.Livro;

public class LivroValidation {
    public static void validate(Livro livro){
        if (livro == null) {
            throw new Error("Livro não pode ser nulo");
        }

        if (livro.getNome() == null) {
            throw new Error("Livro deve ter nome");
        }

        if (livro.getAutor() == null) {
                throw new Error("Livro deve ter autor");
            }
    }
    public static void delete(Livro livro){
        if (livro.getAlugado() == true) {
            throw new Error("Um livro que está alugado não pode ser deletado");
        }
    }
}
