package com.desafio.gft.controllers;

import com.desafio.gft.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    EventoService eventoService;

    @RequestMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index.html");
        mv.addObject("listaEventos", eventoService.listaEventos());
        return mv;
    }
}
