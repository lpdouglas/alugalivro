package com.lpdouglas.alugalivro.config.database;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class SchemaSQLite implements ApplicationRunner {
    private final JdbcTemplate jdbcTemplate;

    public SchemaSQLite(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void run(ApplicationArguments args) {
        //jdbcTemplate.execute("DROP TABLE IF EXISTS livro");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS livro(" +
                "id VARCHAR(36) NOT NULL," +
                "nome VARCHAR(50)," +
                "autor VARCHAR(50)," +
                "alugado INTEGER," +
                "detalhes VARCHAR(250)," +
                "CONSTRAINT livro_pk PRIMARY KEY (id)" +
                ")");
    }
}
