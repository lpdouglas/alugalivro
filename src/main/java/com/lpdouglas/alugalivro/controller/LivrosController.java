package com.lpdouglas.alugalivro.controller;

import com.lpdouglas.alugalivro.dto.LivroDto;
import com.lpdouglas.alugalivro.dto.LivroSimpleOutput;
import com.lpdouglas.alugalivro.service.LivrosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LivrosController {

    @Autowired
    LivrosServices livrosServices;

    @GetMapping("/livros")
    public List<LivroSimpleOutput> getLivros(@RequestParam(required = false) String search){
        List<LivroSimpleOutput> livros = livrosServices.getLivros(search);
        return livros;
    }

    @GetMapping("/livros/{id}")
    public LivroDto getLivro(@PathVariable("id") String id){

        LivroDto livro = livrosServices.getLivro(id);

        return livro;
    }

    @PostMapping("/livros")
    public LivroDto postLivro(@Valid  @RequestBody LivroDto livroInput){

        LivroDto livro = livrosServices.insertLivro(livroInput);

        return livro;
    }

    @PutMapping("/livros/{id}")
    public Boolean putLivro(@PathVariable String id, @RequestBody LivroDto livroInput){

        livroInput.setId(id);

        livrosServices.updateLivro(livroInput);

        return true;
    }

    @DeleteMapping("/livros/{id}")
    public boolean deleteLivro(@PathVariable("id") String id){
        livrosServices.deleteLivro(id);
        return true;
    }

    @PostMapping("/livros/{id}/alugar")
    public boolean alugarLivro(@PathVariable("id") String id){
        livrosServices.alugarLivro(id);
        return true;
    }

//    @PostMapping("/livros/{id}/devolver")
//    public boolean devolverLivro(@PathVariable("id") String id){
//        livrosServices.devolverLivro(id);
//        return true;
//    }

}