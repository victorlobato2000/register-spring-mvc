package com.victorlobato.register.controllers;

import com.victorlobato.register.enums.StatusProfessor;
import com.victorlobato.register.models.Professor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProfessorController {

    @GetMapping("/professores")
    public ModelAndView index(){

        Professor p1 = new Professor(1L, "Vitu", new BigDecimal(4000.0), StatusProfessor.AFASTADO);
        Professor p2 = new Professor(2L, "Mariana", new BigDecimal(14000.0), StatusProfessor.INATIVO);
        Professor p3 = new Professor(3L, "Paulo", new BigDecimal(6000.0), StatusProfessor.APOSENTADO);

        List<Professor> professores = Arrays.asList(p1,p2,p3);

        ModelAndView mv = new ModelAndView("professores/index");

        mv.addObject("professores", professores);
        return mv;
    }
}
