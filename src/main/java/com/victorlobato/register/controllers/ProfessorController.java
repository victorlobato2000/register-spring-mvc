package com.victorlobato.register.controllers;

import com.victorlobato.register.dto.ProfessorDto;
import com.victorlobato.register.enums.StatusProfessor;
import com.victorlobato.register.models.Professor;
import com.victorlobato.register.repositories.ProfessorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProfessorController {

    @Autowired
    ProfessorRepository professorRepository;

    @GetMapping("/professores")
    public ModelAndView index(){
        List<Professor> professores = professorRepository.findAll();
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);
        return mv;
    }

    @GetMapping("/professor/new")
    public ModelAndView nnew(){
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("statusProfessor", StatusProfessor.values());
        return mv;
    }

    @PostMapping("/professores")
    public String create(@Valid ProfessorDto professorDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/professor/new";
        }else {
            Professor professor = professorDto.toProfessor(); //transformando professorDTO em professorModel
            professorRepository.save(professor);
            return "redirect:/professores";
        }
    }
}
