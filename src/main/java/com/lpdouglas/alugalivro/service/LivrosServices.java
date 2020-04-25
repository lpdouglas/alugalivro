package com.lpdouglas.alugalivro.service;

import com.lpdouglas.alugalivro.model.Livro;
import com.lpdouglas.alugalivro.repository.LivroRepository;
import com.lpdouglas.alugalivro.validation.LivroValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class LivrosServices {

    List<Livro> livros = new ArrayList<>(Arrays.asList(
            Livro.builder().id("id_8000").nome("A revolta de Atlas").alugado(false).build(),
            Livro.builder().id("sasfdfjsdn").nome("Não sei Lidar").autor("Fresno").alugado(false).build(),
            Livro.builder().id("posdiposdjf").nome("50 Tons de Cinza").alugado(false).build(),
            Livro.builder().id("soidfosidnfoi").nome("A anatomia do estado").autor("Rothbard").alugado(false).build()
    ));

    @Autowired
    LivroRepository livroRepository;

    public List<Livro> getLivros(String search){

        List<Livro> livros = livroRepository.find(search);

        return livros;
    }

    public Livro getLivro(String id) {
        Livro livro = null;

        for (Livro livroItem : livros) {
            if (livroItem.getId().equals(id)){
                livro = livroItem;
            }
        }

        if (livro==null) {
            throw new Error("Não existe livro com o ID ".concat(id));
        }

        return livro;
    }

    public Livro insertLivro(Livro livro) {
        LivroValidation.validate(livro);


        Livro livroSaved = livroRepository.save(livro);

        livros.add(livroSaved);

        return livroSaved;
    }

    public boolean updateLivro(Livro livro) {
        boolean isUpdated = false;

        for (Livro livroItem : livros) {
            if (livroItem.getId().equals(livro.getId())){
                livroItem.setNome(livro.getNome());
                livroItem.setAutor(livro.getAutor());
                livroItem.setAlugado(livro.getAlugado());
                isUpdated = true;
            }
        }
        return isUpdated;
    }

    public boolean deleteLivro(String id) {
        Livro livroToDelete = null;

        for (Livro livroItem : livros) {
            if (livroItem.getId().equals(id)){
                LivroValidation.delete(livroItem);
                livroToDelete = livroItem;
            }
        }
        livros.remove(livroToDelete);

        return livroToDelete!=null;
    }

}
