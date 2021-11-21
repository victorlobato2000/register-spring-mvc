package com.victorlobato.register.dto;

import com.victorlobato.register.enums.StatusProfessor;
import com.victorlobato.register.models.Professor;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProfessorRequest {

    @NotBlank
    @NotNull
    private String nome; // NotBlank.professorRequest.nome
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal salario;
    private StatusProfessor statusProfessor;

    public Professor toProfessor() {
        Professor professor = new Professor();
        professor.setNome(this.nome);
        professor.setSalario(this.salario);
        professor.setStatusProfessor(this.statusProfessor);

        return professor;
    }
}
