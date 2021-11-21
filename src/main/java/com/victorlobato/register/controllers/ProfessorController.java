package com.victorlobato.register.controllers;

import com.victorlobato.register.dto.ProfessorRequest;
import com.victorlobato.register.enums.StatusProfessor;
import com.victorlobato.register.models.Professor;
import com.victorlobato.register.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

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
    public ModelAndView nnew(@ModelAttribute ProfessorRequest professorRequest, Model model){
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("listaStatusProfessor", StatusProfessor.values());
        model.addAttribute("professorRequest", professorRequest);
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
            ModelAndView mv = new ModelAndView("redirect:/professores");
            mv.addObject(professorRequest);
            return mv ;
        }
    }
}
