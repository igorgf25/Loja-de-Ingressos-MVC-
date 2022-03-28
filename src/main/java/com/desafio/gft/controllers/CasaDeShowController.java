package com.desafio.gft.controllers;

import com.desafio.gft.entities.CasaDeShow;
import com.desafio.gft.services.CasaDeShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("casadeshow")
public class CasaDeShowController {

    @Autowired
    CasaDeShowService casaDeShowService;

    @RequestMapping
    public ModelAndView listaCasasDeShow() {
        ModelAndView mv = new ModelAndView("casaDeShow/lista.html");

        mv.addObject("lista", casaDeShowService.listaCasaDeShow());

        return mv;
    }

    @RequestMapping(path = "/form")
    public ModelAndView formCasa (@RequestParam(required = false) Long id) {
        ModelAndView mv = new ModelAndView("casaDeShow/form.html");

        CasaDeShow casaDeShow;

        if (id == null) {
            casaDeShow = new CasaDeShow();
        } else {
            try {
                casaDeShow = casaDeShowService.buscarCasaDeShow(id);
            } catch (Exception e) {
                casaDeShow = new CasaDeShow();
                mv.addObject("mensagem", "Erro ao buscar casa de show: " + e.getMessage());
            }
        }

        mv.addObject("casaDeShow", casaDeShow);

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/form")
    public ModelAndView salvarCasaDeShow(@Valid CasaDeShow casaDeShow, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView("casaDeShow/form.html");

        boolean novo = true;

        if (casaDeShow.getId() != null) {
            novo = false;
        }

        if (bindingResult.hasErrors()) {
            mv.addObject("casaDeShow", casaDeShow);
            return mv;
        }

        casaDeShowService.salvarCasaDeShow(casaDeShow);

        if (novo) {
            mv.addObject("casaDeShow", new CasaDeShow());
        } else {
            mv.addObject("casaDeShow", casaDeShow);
        }

        mv.addObject("mensagem", "Casa de show salva com sucesso");

        return mv;

    }

    @RequestMapping(path = "excluir")
    public ModelAndView excluirCasaDeShow(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("redirect:/casadeshow");

        try {
            casaDeShowService.excluirCasaDeShow(id);
            mv.addObject("mensagem", "Casa de show excluida com sucesso");
        } catch (Exception e) {
            mv.addObject("mensagem", "Erro ao excluir casa de show: " + e.getMessage());
        }

        return mv;
    }

}
