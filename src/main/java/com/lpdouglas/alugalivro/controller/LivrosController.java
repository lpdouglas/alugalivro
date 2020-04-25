package com.lpdouglas.alugalivro.controller;

import com.lpdouglas.alugalivro.dto.LivroDto;
import com.lpdouglas.alugalivro.model.Livro;
import com.lpdouglas.alugalivro.service.LivrosServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
public class LivrosController {

    @Autowired
    LivrosServices livrosServices;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/livros")
    public List<LivroDto> getLivros(){
        List<Livro> livros = livrosServices.getLivros();

        LivroDto[] livrosDto = modelMapper.map(livros, LivroDto[].class);

        return Arrays.asList(livrosDto);
    }

    @GetMapping("/livros/{id}")
    public LivroDto getLivro(@PathVariable("id") String id){

        Livro livro = livrosServices.getLivro(id);

        LivroDto livroDto = (livro != null) ? modelMapper.map(livro, LivroDto.class) : null;

        return livroDto;
    }

    @PostMapping("/livros")
    public LivroDto postLivro(@Valid  @RequestBody LivroDto livroInput){

        Livro livroEntity = modelMapper.map(livroInput, Livro.class);

        Livro livro = livrosServices.insertLivro(livroEntity);

        LivroDto livroOutput = (livro != null) ? modelMapper.map(livro, LivroDto.class) : null;

        return livroOutput;
    }

    @PutMapping("/livros/{id}")
    public Boolean putLivro(@PathVariable String id, @RequestBody LivroDto livroInput){

        livroInput.setId(id);
        Livro livroEntity = modelMapper.map(livroInput, Livro.class);

        boolean response = livrosServices.updateLivro(livroEntity);

        return response;
    }

    @DeleteMapping("/livros/{id}")
    public boolean DeleteLivro(@PathVariable("id") String id){

        boolean response = livrosServices.deleteLivro(id);

        return response;
    }

}