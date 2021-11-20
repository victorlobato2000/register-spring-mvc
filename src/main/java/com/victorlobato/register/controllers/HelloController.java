package com.victorlobato.register.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @GetMapping("/hello-servlet")
    public String hello1(HttpServletRequest request){
        request.setAttribute("nome","Victor");
        return "hello";
    }

    @GetMapping("/hello/model")
    public String hello2(Model model){
        model.addAttribute("nome","Victor Silva");
        return "hello";
    }


    @GetMapping("/hello/model-view")
    public ModelAndView hello3(){
        ModelAndView mv = new ModelAndView("hello"); //nome do arquivo html a ser renderizado
        mv.addObject ("nome","Victor Lobato");
        return mv;
    }

}
