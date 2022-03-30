package com.desafio.gft.controllers;

import com.desafio.gft.entities.Evento;
import com.desafio.gft.enums.GenerosMusicais;
import com.desafio.gft.services.CasaDeShowService;
import com.desafio.gft.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("evento")
public class EventoController {

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

        mv.addObject("evento", evento);
        adicionarListasNoMV(mv);

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, path = "form")
    public ModelAndView salvarEvento(@Valid Evento evento, BindingResult bindingResult, @RequestParam("file")MultipartFile file) {
        ModelAndView mv = new ModelAndView("evento/form.html");

        boolean novo = true;

        if (evento.getId() != null) {
            novo = false;
        }

        if (bindingResult.hasErrors()) {
            mv.addObject("evento", evento);
            adicionarListasNoMV(mv);
            return mv;
        }

        try {
            evento.setFoto(file.getBytes());
        } catch (Exception e) {
            mv.addObject("message", "Erro ao salvar imagem");
            adicionarListasNoMV(mv);
            return mv;
        }

        eventoService.salvarEvento(evento);

        if (novo) {
            mv.addObject("evento", new Evento());
        } else {
            mv.addObject("evento", evento);
        }

        mv.addObject("mensagem", "Evento salvo com sucesso");
        adicionarListasNoMV(mv);

        return mv;
    }

    @RequestMapping("imagem")
    @ResponseBody
    public byte[] exibirImagem(@RequestParam Long id) {

        Evento evento;

        try {
            evento = eventoService.buscaEvento(id);
        } catch (Exception e) {
            evento = new Evento();
        }

        return evento.getFoto();
    }

    @RequestMapping(path = "excluir")
    public ModelAndView excluirEvento(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("redirect:/evento");

        try {
            eventoService.excluirEvento(id);
            mv.addObject("mensagem", "Evento excluido com sucesso");
        } catch (Exception e) {
            mv.addObject("mensagem", "Erro ao excluir evento: " + e.getMessage());
        }

        return mv;
    }

    private void adicionarListasNoMV(ModelAndView mv) {
        mv.addObject("listaCasa", casaDeShowService.listaCasaDeShow());
        mv.addObject("listaGeneros", GenerosMusicais.values());
    }
}
