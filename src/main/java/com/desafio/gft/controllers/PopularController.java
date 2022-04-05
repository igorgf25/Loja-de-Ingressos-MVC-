package com.desafio.gft.controllers;

import com.desafio.gft.entities.*;
import com.desafio.gft.enums.GenerosMusicais;
import com.desafio.gft.services.CasaDeShowService;
import com.desafio.gft.services.EventoService;
import com.desafio.gft.services.RoleService;
import com.desafio.gft.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    public ModelAndView popularBanco(RedirectAttributes redirectAttributes) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/login");

        Long quantidadeRoles = roleService.contaRole();
        Long comparador = Long.valueOf(0l);

        System.out.println(quantidadeRoles);
        if (quantidadeRoles.equals(comparador)) {

            //Adicionando os role's
            Role roleUser = new Role("ROLE_USER");
            Role roleAdmin = new Role("ROLE_ADMIN");

            roleService.salvarRole(roleUser);
            roleService.salvarRole(roleAdmin);

            //Adicionando admin
            List<Role> adminRole = new ArrayList<Role>();
            adminRole.add(roleService.buscaRole("ROLE_ADMIN"));
            Usuario admin = new Usuario("admin", "admin", encoder.encode("admin"), new ArrayList<Ingresso>(), adminRole);
            usuarioService.salvarUsuario(admin);
            //Adicionando usuario
            List<Role> userRole = new ArrayList<Role>();
            userRole.add(roleService.buscaRole("ROLE_USER"));
            Usuario usuario = new Usuario("user", "user", encoder.encode("user"), new ArrayList<Ingresso>(), userRole);
            usuarioService.salvarUsuario(usuario);

            //Adicionando casas de show
            CasaDeShow casa1 = new CasaDeShow("Infinity Hall", "R. Luiz Fernando Betti, 73", new ArrayList<Evento>());
            CasaDeShow casa2 = new CasaDeShow("Arena Ayres", "R. Vinte e Oito de Outubro", new ArrayList<Evento>());
            CasaDeShow casa3 = new CasaDeShow("Villa Plaza Club", "Av. São Bernardo do Campo", new ArrayList<Evento>());

            casaDeShowService.salvarCasaDeShow(casa1);
            casaDeShowService.salvarCasaDeShow(casa2);
            casaDeShowService.salvarCasaDeShow(casa3);

            //Adicionando evento Lollapaluza
            MultipartFile lollaFoto = new MockMultipartFile("test.xlsx", new FileInputStream(new File("src/main/resources/static/lolla.png")));
            LocalTime horas1 = LocalTime.parse("12:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
            Date data1 = new GregorianCalendar(2022, Calendar.DECEMBER, 10).getTime();
            Long idCasa1 = Long.valueOf(1l);
            Long capacidade1 = Long.valueOf(10000l);
            BigDecimal valorIngresso1 = BigDecimal.valueOf(1500.00d);
            Evento lolla = new Evento("Lollapaluza", "Lollapalooza é um festival de música alternativa que acontece anualmente, é composto por gêneros como rock alternativo, heavy metal, punk rock, grunge e performances de comédia e danças, além de estandes de artesanato.",
                    capacidade1, data1, horas1, valorIngresso1, GenerosMusicais.ELETRONICA, casaDeShowService.buscarCasaDeShow(idCasa1), lollaFoto.getBytes());
            eventoService.salvarEvento(lolla);

            //Adicionando evento Rock In Rio
            MultipartFile rockFoto = new MockMultipartFile("test.xlsx", new FileInputStream(new File("src/main/resources/static/rock.png")));
            LocalTime horas2 = LocalTime.parse("14:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
            Date data2 = new GregorianCalendar(2022, Calendar.JUNE, 13).getTime();
            Long idCasa2 = Long.valueOf(2l);
            Long capacidade2 = Long.valueOf(20000l);
            BigDecimal valorIngresso2 = BigDecimal.valueOf(1750.00d);
            Evento rock = new Evento("Rock in Rio", "O Rock in Rio é um festival de música idealizado pelo empresário brasileiro Roberto Medina pela primeira vez em 1985.",
                    capacidade2, data2, horas2, valorIngresso2, GenerosMusicais.ELETRONICA, casaDeShowService.buscarCasaDeShow(idCasa2), rockFoto.getBytes());
            eventoService.salvarEvento(rock);

            //Adicionando evento Tomorow land
            MultipartFile tomorrowFoto = new MockMultipartFile("test.xlsx", new FileInputStream(new File("src/main/resources/static/tommorow.png")));
            LocalTime horas3 = LocalTime.parse("13:30:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
            Date data3 = new GregorianCalendar(2022, Calendar.MAY, 25).getTime();
            Long idCasa3 = Long.valueOf(3l);
            Long capacidade3 = Long.valueOf(50000l);
            BigDecimal valorIngresso3 = BigDecimal.valueOf(1000.00d);
            Evento tomorrow = new Evento("Tomorrowland", "Tomorrowland é um festival de música realizado anualmente. Sua edição original é realizada em Boom na Bélgica, cidade com menos de 20 mil habitantes, no distrito de Antuérpia.",
                    capacidade3, data3, horas3, valorIngresso3, GenerosMusicais.ELETRONICA, casaDeShowService.buscarCasaDeShow(idCasa3), tomorrowFoto.getBytes());
            eventoService.salvarEvento(tomorrow);
            redirectAttributes.addFlashAttribute("mensagem", "Banco de dados populado com sucesso");
            return mv;
        }

        redirectAttributes.addFlashAttribute("mensagem", "Banco de dados já populado");
        return mv;
    }
}
