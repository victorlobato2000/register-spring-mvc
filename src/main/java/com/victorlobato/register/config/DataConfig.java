package com.victorlobato.register.config;

import com.victorlobato.register.enums.StatusProfessor;
import com.victorlobato.register.models.Professor;
import com.victorlobato.register.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
public class DataConfig {

    @Autowired
    ProfessorRepository repository;

    @Bean
    public void criar(){
        Professor p1 = new Professor(null, "Paulo", new BigDecimal(4000.0), StatusProfessor.AFASTADO);
        Professor p2 = new Professor(null, "Victor", new BigDecimal(14000.0), StatusProfessor.INATIVO);
        Professor p3 = new Professor(null, "Lobato", new BigDecimal(6000.0), StatusProfessor.APOSENTADO);
        repository.saveAll(Arrays.asList(p1,p2,p3));
    }
}
