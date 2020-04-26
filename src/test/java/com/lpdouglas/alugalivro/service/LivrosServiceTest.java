package com.lpdouglas.alugalivro.service;

import com.lpdouglas.alugalivro.dto.LivroDto;
import com.lpdouglas.alugalivro.dto.LivroSimpleOutput;
import com.lpdouglas.alugalivro.exception.LivroException;
import com.lpdouglas.alugalivro.repository.LivroRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class LivrosServiceTest {

    @InjectMocks
    LivrosService livrosService;

    @Mock
    LivroRepository livroRepository;

    @Before
    public void init() {
        Mockito.when(livroRepository.findById("idExistente"))
                .thenReturn(new LivroDto("idExistente", "3 porcos", "disney", false, ""));

        Mockito.when(livroRepository.save(Mockito.any(LivroDto.class))).thenAnswer(arguments -> arguments.getArgument(0));
        Mockito.doNothing().when(livroRepository).update(Mockito.any());
    }

    @Test
    public void getLivros(){
        Mockito.when(livroRepository.find(""))
                .thenReturn(List.of(new LivroDto("", "3 porcos", "disney", false, "")));

        //Action
        List<LivroSimpleOutput> livros = livrosService.getLivros("");

        //Verify
        Mockito.verify(livroRepository, Mockito.times(1)).find("");
        assertEquals(1, livros.size());
    }

    @Test
    public void getLivroByExistentId(){
        //Action
        LivroDto livro = livrosService.getLivro("idExistente");

        //Verify
        Mockito.verify(livroRepository, Mockito.times(1)).findById(Mockito.anyString());
        assertEquals("3 porcos", livro.getNome());
    }

    @Test(expected = LivroException.class)
    public void getLivroByInexistentId(){
        //Action
        livrosService.getLivro("OutroId");
    }

    @Test
    public void insertLivro(){
        //Action
        livrosService.insertLivro(LivroDto.builder().nome("Menino Maluco").autor("Ziraldo").build());

        //Verify
        Mockito.verify(livroRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test(expected = LivroException.class)
    public void insertLivroWithoutNome() {
        //Action
        livrosService.insertLivro(LivroDto.builder().build());

        //Verify
        Mockito.verify(livroRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test(expected = LivroException.class)
    public void insertLivroWithoutAutor() {
        //Action
        livrosService.insertLivro(LivroDto.builder().nome("Menino Maluco").build());

        //Verify
        Mockito.verify(livroRepository, Mockito.times(1)).save(Mockito.any());
    }

    public void deleteLivro() {
        livroRepository.delete("idExistente");

        //Verify
        Mockito.verify(livroRepository, Mockito.times(1)).delete(Mockito.any());
    }

    public void updateLivro() {
        livroRepository.update(LivroDto.builder().detalhes("Era uma vez uma donzela, irmãs, uma maldrasta, e um baile").build());

        //Verify
        Mockito.verify(livroRepository, Mockito.times(1)).update(Mockito.any());
    }

}