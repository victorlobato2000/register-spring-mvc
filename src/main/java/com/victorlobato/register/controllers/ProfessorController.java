package com.victorlobato.register.controllers;

import com.victorlobato.register.dto.ProfessorRequest;
import com.victorlobato.register.enums.StatusProfessor;
import com.victorlobato.register.models.Professor;
import com.victorlobato.register.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    ProfessorRepository professorRepository;

    @GetMapping("")
    public ModelAndView index(){
        List<Professor> professores = professorRepository.findAll();
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(ProfessorRequest professorRequest){
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("listaStatusProfessor", StatusProfessor.values());
        return mv;
    }

    @PostMapping("")
    public ModelAndView create(@Valid ProfessorRequest professorRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("professores/new");
            mv.addObject("listaStatusProfessor", StatusProfessor.values());
            return mv;
        }else {
            Professor professor = professorRequest.toProfessor(); //transformando professorDTO em professorModel
            professorRepository.save(professor);
            ModelAndView mv = new ModelAndView("redirect:/professores/"+ professor.getId());
            mv.addObject(professorRequest);
            return mv ;
        }
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id){
        Optional<Professor> optionalProfessor = professorRepository.findById(id);
        if(optionalProfessor.isPresent()){

            Professor professor = optionalProfessor.get();
            ModelAndView mv = new ModelAndView("professores/show");
            mv.addObject("professor", professor);

            return mv;
        }else {
            return new ModelAndView("redirect:/professores");
        }
    }
}
