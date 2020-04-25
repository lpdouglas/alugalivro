package com.lpdouglas.alugalivro.service;

import com.lpdouglas.alugalivro.dto.LivroDto;
import com.lpdouglas.alugalivro.dto.LivroSimpleOutput;
import com.lpdouglas.alugalivro.repository.LivroRepository;
import com.lpdouglas.alugalivro.validation.LivroValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LivrosServices {

    @Autowired
    LivroRepository livroRepository;

    ModelMapper modelMapper = new ModelMapper();

    public List<LivroSimpleOutput> getLivros(String search){
        List<LivroDto> livros = livroRepository.find(search);
        List<LivroSimpleOutput> livrosSimple = new ArrayList<>();

        for (LivroDto livro:
             livros) {
            livrosSimple.add( modelMapper.map(livro, LivroSimpleOutput.class) );
        }

        return livrosSimple;
    }

    public LivroDto getLivro(String id) {
        LivroDto livro = livroRepository.findById(id);

        if (livro == null) {
            throw new Error("Não existe livro com o ID ".concat(id));
        }

        return livro;
    }

    public LivroDto insertLivro(LivroDto livro) {
        LivroValidation.schema(livro);


        LivroDto livroSaved = livroRepository.save(livro);

        return livroSaved;
    }

//    public boolean updateLivro(LivroDto livro) {
//        boolean isUpdated = false;
//
//        for (Livro livroItem : livros) {
//            if (livroItem.getId().equals(livro.getId())){
//                livroItem.setNome(livro.getNome());
//                livroItem.setAutor(livro.getAutor());
//                livroItem.setAlugado(livro.getAlugado());
//                isUpdated = true;
//            }
//        }
//        return isUpdated;
//    }
//
    public void deleteLivro(String id) {
        livroRepository.delete(id);
    }

    public void updateLivro(LivroDto livroDto) {
        livroRepository.update(livroDto);
    }

}
