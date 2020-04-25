package com.lpdouglas.alugalivro.service;

import com.lpdouglas.alugalivro.dto.LivroDto;
import com.lpdouglas.alugalivro.dto.LivroSimpleOutput;
import com.lpdouglas.alugalivro.repository.LivroRepository;
import com.lpdouglas.alugalivro.validation.LivroValidation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LivrosServices {

    final
    LivroRepository livroRepository;

    final ModelMapper modelMapper = new ModelMapper();

    public LivrosServices(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

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


        return livroRepository.save(livro);
    }

    public void deleteLivro(String id) {
        livroRepository.delete(id);
    }

    public void updateLivro(LivroDto livroDto) {
        livroRepository.update(livroDto);
    }

    public void alugarLivro(String id) {
        LivroDto livroDto = new LivroDto();
        livroDto.setId(id);
        livroDto.setAlugado(true);
        livroRepository.update(livroDto);
    }

//    public void devolverLivro(String id) {
//        LivroDto livroDto = new LivroDto();
//        livroDto.setId(id);
//        livroDto.setAlugado(false);
//        livroRepository.update(livroDto);
//    }
}
