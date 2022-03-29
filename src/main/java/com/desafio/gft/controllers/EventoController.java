package com.desafio.gft.controllers;

import com.desafio.gft.entities.Evento;
import com.desafio.gft.enums.GenerosMusicais;
import com.desafio.gft.services.CasaDeShowService;
import com.desafio.gft.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("evento")
public class EventoController {

    public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";

    @Autowired
    CasaDeShowService casaDeShowService;

    @Autowired
    EventoService eventoService;

    @RequestMapping
    public ModelAndView listaEventos() {
        ModelAndView mv = new ModelAndView("evento/lista.html");

        mv.addObject("lista", eventoService.listaEventos());

        return mv;
    }

    @RequestMapping("form")
    public ModelAndView formEvento(@RequestParam(required = false) Long id) {
        ModelAndView mv = new ModelAndView("evento/form.html");

        Evento evento;

        if (id == null) {
            evento = new Evento();
        } else {
            try {
                evento = eventoService.buscaEvento(id);
            } catch (Exception e) {
                evento = new Evento();
                mv.addObject("mensagem", "Erro ao procurar evento: " + e.getMessage());
            }
        }

        mv.addObject("listaCasa", casaDeShowService.listaCasaDeShow());
        mv.addObject("evento", evento);
        mv.addObject("listaGeneros", GenerosMusicais.values());

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, path = "form")
    public ModelAndView salvarEvento(@Valid Evento evento, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView("evento/form.html");

        boolean novo = true;

        if (evento.getId() != null) {
            novo = false;
        }

        if (bindingResult.hasErrors()) {
            mv.addObject("evento", evento);
            mv.addObject("listaCasa", casaDeShowService.listaCasaDeShow());
            mv.addObject("listaGeneros", GenerosMusicais.values());
            return mv;
        }

        eventoService.salvarEvento(evento);

        if (novo) {
            mv.addObject("evento", new Evento());
        } else {
            mv.addObject("evento", evento);
        }

        mv.addObject("mensagem", "Evento salvo com sucesso");
        mv.addObject("listaCasa", casaDeShowService.listaCasaDeShow());
        mv.addObject("listaGeneros", GenerosMusicais.values());

        return mv;
    }

}
