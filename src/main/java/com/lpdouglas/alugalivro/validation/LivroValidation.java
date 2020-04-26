package com.lpdouglas.alugalivro.validation;

import com.lpdouglas.alugalivro.dto.LivroDto;
import com.lpdouglas.alugalivro.exception.LivroException;

public class LivroValidation {
    public static void schema(LivroDto livro){
        if (livro == null) {
            throw new LivroException("Livro não pode ser nulo");
        }

        if (livro.getNome() == null) {
            throw new LivroException("Livro deve ter nome");
        }

        if (livro.getAutor() == null) {
                throw new LivroException("Livro deve ter autor");
            }
    }

    public static void delete(LivroDto livro){
        if (livro.getAlugado()) {
            throw new LivroException("Um livro que está alugado não pode ser deletado");
        }
    }
    public static void update(LivroDto livro){
        if (livro.getAlugado()) {
            throw new LivroException("Um livro que está alugado não pode ser atualizado");
        }
    }
}
