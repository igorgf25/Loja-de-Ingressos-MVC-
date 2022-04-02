package com.desafio.gft.controllers;

import com.desafio.gft.entities.Evento;
import com.desafio.gft.entities.Ingresso;
import com.desafio.gft.entities.Usuario;
import com.desafio.gft.services.EventoService;
import com.desafio.gft.services.IngressoService;
import com.desafio.gft.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("ingresso")
public class IngressoController {

    @Autowired
    EventoService eventoService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    IngressoService ingressoService;

    @RequestMapping
    public ModelAndView comprarIngresso(@RequestParam Long id) throws Exception {
        ModelAndView mv = new ModelAndView("ingresso/form.html");
        ModelAndView mvError = new ModelAndView("index.html");

        Evento evento;

        try {
            evento = eventoService.buscaEvento(id);
            mv.addObject(evento);
        } catch (Exception e) {
            mvError.addObject("mensagem", "Erro ao buscar evento: " + e.getMessage());
            return mvError;
        }

        mv.addObject("ingresso", new Ingresso());

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView finalizarCompra(Evento evento, @RequestParam("quantidade") Long quantidade, RedirectAttributes redirectAttributes) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/");

        Evento eventoSalvar;

        try {
            eventoSalvar = eventoService.buscaEvento(evento.getId());
            ingressoService.salvarIngresso(eventoSalvar, quantidade);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro : " + e.getMessage());
            return mv;
        }

        return mv;
    }
}
