package com.desafio.gft.controllers;

import com.desafio.gft.entities.*;
import com.desafio.gft.enums.GenerosMusicais;
import com.desafio.gft.services.CasaDeShowService;
import com.desafio.gft.services.EventoService;
import com.desafio.gft.services.RoleService;
import com.desafio.gft.services.UsuarioService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("popular")
public class PopularController {

    @Autowired
    RoleService roleService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CasaDeShowService casaDeShowService;

    @Autowired
    EventoService eventoService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @RequestMapping
    public ModelAndView popularBanco() throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/login");
        mv.addObject("mensagem", "Banco de dados populado");


        //Adicionando os role's
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");

        roleService.salvarRole(roleUser);
        roleService.salvarRole(roleAdmin);

        //Adicionando admin
        List<Role> adminRole = new ArrayList<Role>();
        adminRole.add(roleService.buscaRole("ROLE_ADMIN"));
        Usuario admin = new Usuario("admin","admin", encoder.encode("admin"), new ArrayList<Ingresso>(), adminRole);
        usuarioService.salvarUsuario(admin);
        //Adicionando usuario
        List<Role> userRole = new ArrayList<Role>();
        userRole.add(roleService.buscaRole("ROLE_USER"));
        Usuario usuario = new Usuario("user","user", encoder.encode("user"), new ArrayList<Ingresso>(), userRole);
        usuarioService.salvarUsuario(usuario);

        //Adicionando casas de show
        CasaDeShow casa1 = new CasaDeShow("Infinity Hall", "R. Luiz Fernando Betti, 73", new ArrayList<Evento>());
        CasaDeShow casa2 = new CasaDeShow("Arena Ayres", "R. Vinte e Oito de Outubro", new ArrayList<Evento>());
        CasaDeShow casa3 = new CasaDeShow("Villa Plaza Club", "Av. São Bernardo do Campo", new ArrayList<Evento>());

        casaDeShowService.salvarCasaDeShow(casa1);
        casaDeShowService.salvarCasaDeShow(casa2);
        casaDeShowService.salvarCasaDeShow(casa3);

        //Adicionando evento Lollapaluza
//        File file = new File("src/test/resources/input.txt");
        MultipartFile lollaFoto = new MockMultipartFile("test.xlsx", new FileInputStream(new File("src/main/resources/static/lolla.png")));
        LocalTime horas1 = LocalTime.now();
        Date data1 = new Date(2022,05,25);
        Long idCasa1 = Long.valueOf(1l);
        Long capacidade1 = Long.valueOf(10000l);
        BigDecimal valorIngresso1 = BigDecimal.valueOf(1500.00d);
        Evento lolla = new Evento("Lollapaluza", "Lollapalooza é um festival de música alternativa que acontece anualmente, é composto por gêneros como rock alternativo, heavy metal, punk rock, grunge e performances de comédia e danças, além de estandes de artesanato.",
                                    capacidade1, data1, horas1, valorIngresso1, GenerosMusicais.ELETRONICA, casaDeShowService.buscarCasaDeShow(idCasa1), lollaFoto.getBytes(), new ArrayList<Ingresso>());
        eventoService.salvarEvento(lolla);

        //Adicionando evento Rock In Rio
        MultipartFile rockFoto = new MockMultipartFile("test.xlsx", new FileInputStream(new File("src/main/resources/static/rock.png")));
        LocalTime horas2 = LocalTime.now();
        Date data2 = new Date(2022,07,03);
        Long idCasa2 = Long.valueOf(2l);
        Long capacidade2 = Long.valueOf(20000l);
        BigDecimal valorIngresso2 = BigDecimal.valueOf(1750.00d);
        Evento rock = new Evento("Rock in Rio", "O Rock in Rio é um festival de música idealizado pelo empresário brasileiro Roberto Medina pela primeira vez em 1985.",
                capacidade2, data2, horas2, valorIngresso2, GenerosMusicais.ELETRONICA, casaDeShowService.buscarCasaDeShow(idCasa2), rockFoto.getBytes(), new ArrayList<Ingresso>());
        eventoService.salvarEvento(rock);

        //Adicionando evento Tomorow land
        MultipartFile tomorrowFoto = new MockMultipartFile("test.xlsx", new FileInputStream(new File("src/main/resources/static/tommorow.png")));
        LocalTime horas3 = LocalTime.now();
        Date data3 = new Date(2022,06,13);
        Long idCasa3 = Long.valueOf(3l);
        Long capacidade3 = Long.valueOf(50000l);
        BigDecimal valorIngresso3 = BigDecimal.valueOf(1000.00d);
        Evento tomorrow = new Evento("Tomorrowland", "Tomorrowland é um festival de música realizado anualmente. Sua edição original é realizada em Boom na Bélgica, cidade com menos de 20 mil habitantes, no distrito de Antuérpia.",
                capacidade3, data3, horas3, valorIngresso3, GenerosMusicais.ELETRONICA, casaDeShowService.buscarCasaDeShow(idCasa3), tomorrowFoto.getBytes(), new ArrayList<Ingresso>());
        eventoService.salvarEvento(tomorrow);

        return mv;


    }
}
