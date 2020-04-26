package com.lpdouglas.alugalivro.repository;

import com.lpdouglas.alugalivro.dto.LivroDto;
import com.lpdouglas.alugalivro.exception.LivroException;
import com.lpdouglas.alugalivro.model.Livro;
import com.lpdouglas.alugalivro.validation.LivroValidation;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class LivroRepository {

    final
    JdbcTemplate jdbcTemplate;

    final ModelMapper modelMapper = new ModelMapper();

    public LivroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public LivroDto save(LivroDto livroDto){
        livroDto.setId(UUID.randomUUID().toString());

        //Criação da model para inserção no banco (que está sendo feita manualmente)
        Livro livro = modelMapper.map(livroDto, Livro.class);

        jdbcTemplate.execute("INSERT INTO livro (id, nome, autor, alugado, detalhes) " +
                "VALUES( '"+
                    livro.getId() +"','" +
                    livro.getNome() +"','" +
                    livro.getAutor() +"','" +
                    (livro.getAlugado() ? 1 : 0) +"', '"+
                    livro.getDetalhes() +"'" +
                    ")");

        return livroDto;
    }

    public List<LivroDto> find(String search){
        return find(search, List.of("id", "nome", "autor", "alugado"));
    }

    public List<LivroDto> find(String search, List<String> fields){
        return findLivros(fields, (search!=null && !search.isBlank()) ? "nome LIKE '%"+search+"%' OR autor LIKE '%"+search+"%'" : null);
    }

    public LivroDto findById(String id){
        List<LivroDto> livros = findLivros(null, "id = '".concat(id).concat("'"));
        if (livros.size() == 0) throw new LivroException(String.format("O livro %s não existe", id));
        return livros.get(0);
    }

    private List<LivroDto> findLivros(List<String> fields, String where){
        List<Map<String, Object>> mapLivros;
        String colums = fields!=null ? String.join(", ", fields) : "*";

        if (where == null) {
            mapLivros = jdbcTemplate.queryForList("SELECT "+colums+" FROM livro");
        } else {
            mapLivros = jdbcTemplate.queryForList("SELECT "+colums+" FROM livro WHERE "+where);
        }

        List<LivroDto> livros = new ArrayList<>();
        for (Map<String, Object> livro:
             mapLivros) {
            //Para simplificar a leitura desse mapeamento, não utilizei Reflections nem dao
            String id = livro.get("id") != null ? (String) livro.get("id") : "";
            String nome = livro.get("nome") != null ? (String) livro.get("nome") : "";
            String autor = livro.get("autor") != null ? (String) livro.get("autor") : "";
            Boolean alugado = livro.get("alugado") != null ? (Integer) livro.get("alugado") == 1 : null;
            String detalhes = livro.get("detalhes") != null ? (String) livro.get("detalhes") : "";

            livros.add( new LivroDto(id, nome, autor, alugado, detalhes) );
        }
        return livros;
    }

    public void delete(String id){
        List<LivroDto> livros = findLivros(null, "id = '".concat(id).concat("'"));

        if (livros.size() == 0) throw new LivroException(String.format("O livro %s não existe", id));

        LivroDto livro = livros.get(0);

        LivroValidation.delete(livro);

        jdbcTemplate.execute("DELETE FROM livro WHERE id = '"+id+"'");

    }

    public void update(LivroDto livroDto){
        List<LivroDto> livros = findLivros(null, "id = '".concat(livroDto.getId()).concat("'"));

        if (livros.size() == 0) throw new LivroException(String.format("O livro %s não existe", livroDto.getId()));

        LivroDto livroOld = livros.get(0);

        LivroValidation.update(livroOld);

        Livro newLivro = modelMapper.map(livroDto, Livro.class);
        newLivro.setId(livroDto.getId());
        if (newLivro.getNome() == null) newLivro.setNome(livroOld.getNome());
        if (newLivro.getAutor() == null) newLivro.setAutor(livroOld.getAutor());
        if (newLivro.getAlugado() == null) newLivro.setAlugado(livroOld.getAlugado());
        if (newLivro.getDetalhes() == null) newLivro.setDetalhes(livroOld.getDetalhes());

        jdbcTemplate.execute("UPDATE livro SET " +
                "nome = '" + newLivro.getNome() + "', " +
                "autor = '" + newLivro.getAutor() + "', " +
                "alugado = '" + (newLivro.getAlugado() ? "1" : "0") + "', " +
                "detalhes = '" + newLivro.getDetalhes() + "' " +
                "  WHERE id = '"+ newLivro.getId()+"'");

    }

}
