package com.lpdouglas.alugalivro.controller;

import com.lpdouglas.alugalivro.dto.LivroDto;
import com.lpdouglas.alugalivro.dto.LivroSimpleOutput;
import com.lpdouglas.alugalivro.service.LivrosService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LivrosController {

    final
    LivrosService livrosService;

    public LivrosController(LivrosService livrosService) {
        this.livrosService = livrosService;
    }

    @GetMapping("/livros")
    public List<LivroSimpleOutput> getLivros(@RequestParam(required = false) String search){
        return livrosService.getLivros(search);
    }

    @GetMapping("/livros/{id}")
    public LivroDto getLivro(@PathVariable("id") String id){

        return livrosService.getLivro(id);
    }

    @PostMapping("/livros")
    public LivroDto postLivro(@Valid  @RequestBody LivroDto livroInput){

        return livrosService.insertLivro(livroInput);
    }

    @PutMapping("/livros/{id}")
    public String putLivro(@PathVariable String id, @RequestBody LivroDto livroInput){
        livroInput.setId(id);
        livrosService.updateLivro(livroInput);
        return "Livro atualizado";
    }

    @DeleteMapping("/livros/{id}")
    public String deleteLivro(@PathVariable("id") String id){
        livrosService.deleteLivro(id);
        return "Livro deletado";
    }

    @PostMapping("/livros/{id}/alugar")
    public String alugarLivro(@PathVariable("id") String id){
        livrosService.alugarLivro(id);
        return "Livro alugado";
    }
//    Método não implementado pois não está na lista e não pode ser editado
//    um livro alugado (colocar como alugado=false seria uma edição)
//    @PostMapping("/livros/{id}/devolver")
//    public String devolverLivro(@PathVariable("id") String id){
//        livrosServices.devolverLivro(id);
//        return "Livro devolvido";
//    }

}