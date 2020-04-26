package com.lpdouglas.alugalivro.controller;

import com.lpdouglas.alugalivro.dto.LivroDto;
import com.lpdouglas.alugalivro.dto.LivroSimpleOutput;
import com.lpdouglas.alugalivro.service.LivrosServices;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LivrosController {

    final
    LivrosServices livrosServices;

    public LivrosController(LivrosServices livrosServices) {
        this.livrosServices = livrosServices;
    }

    @GetMapping("/livros")
    public List<LivroSimpleOutput> getLivros(@RequestParam(required = false) String search){
        return livrosServices.getLivros(search);
    }

    @GetMapping("/livros/{id}")
    public LivroDto getLivro(@PathVariable("id") String id){

        return livrosServices.getLivro(id);
    }

    @PostMapping("/livros")
    public LivroDto postLivro(@Valid  @RequestBody LivroDto livroInput){

        return livrosServices.insertLivro(livroInput);
    }

    @PutMapping("/livros/{id}")
    public String putLivro(@PathVariable String id, @RequestBody LivroDto livroInput){
        livroInput.setId(id);
        livrosServices.updateLivro(livroInput);
        return "Livro atualizado";
    }

    @DeleteMapping("/livros/{id}")
    public String deleteLivro(@PathVariable("id") String id){
        livrosServices.deleteLivro(id);
        return "Livro deletado";
    }

    @PostMapping("/livros/{id}/alugar")
    public String alugarLivro(@PathVariable("id") String id){
        livrosServices.alugarLivro(id);
        return "Livro alugado";
    }

//    @PostMapping("/livros/{id}/devolver")
//    public boolean devolverLivro(@PathVariable("id") String id){
//        livrosServices.devolverLivro(id);
//        return true;
//    }

}