package com.lpdouglas.alugalivro.config.database;

import com.lpdouglas.alugalivro.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Configuration
public class SchemaSQLite implements ApplicationRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        //jdbcTemplate.execute("DROP TABLE IF EXISTS livro");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS livro(" +
                "id VARCHAR(36) NOT NULL," +
                "nome VARCHAR(50)," +
                "autor VARCHAR(50)," +
                "alugado INTEGER," +
                "CONSTRAINT livro_pk PRIMARY KEY (id)" +
                ")");
    }
}
