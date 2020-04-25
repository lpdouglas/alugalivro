package com.lpdouglas.alugalivro;

import com.lpdouglas.alugalivro.config.database.SchemaSQLite;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlugaLivroApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlugaLivroApplication.class, args);
    }

}
