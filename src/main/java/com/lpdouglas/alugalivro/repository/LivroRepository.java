package com.lpdouglas.alugalivro.repository;

import com.lpdouglas.alugalivro.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class LivroRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Livro save(Livro livro){
        livro.setId(UUID.randomUUID().toString());

        jdbcTemplate.execute("INSERT INTO livro (id, nome, autor, alugado) " +
                "VALUES( '"+
                livro.getId() +"','" +
                livro.getNome() +"','" +
                livro.getAutor() +"','" +
                (livro.getAlugado() ? 1 : 0) +"'"+
                ")");

        return livro;
    }

    public List<Livro> find(String search){
        List<Livro> livros = new ArrayList<>();

        List<Map<String, Object>> mapLivros;

        if (search == null) {
            mapLivros = jdbcTemplate.queryForList("SELECT * FROM livro");
        } else {
            mapLivros = jdbcTemplate.queryForList("SELECT * FROM livro WHERE nome LIKE '%"+search+"%' OR autor LIKE '%"+search+"%'");
        }

        for (Map<String, Object> livro:
             mapLivros) {
            String id = (String) livro.get("id");
            String nome = (String) livro.get("nome");
            String autor = (String) livro.get("autor");
            Boolean alugado = ( ((Integer) livro.get("alugado")) == 1 );

            livros.add( new Livro(id, nome, autor, alugado) );
        }
        return livros;
    }

}
