package com.desafio.gft.entities;

import com.desafio.gft.enums.GenerosMusicais;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotEmpty(message = "Nome do evento não pode estar vazio")
    private String nome;

    @NotEmpty(message = "Descrição do evento não pode estar vazio")
    @Size(max = 255)
    private String descricao;

    @Min(value = 1, message = "A capacidade do evento deve no minimo 1")
    private Long capacidade;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Não é possivel marcar eventos em uma data anterior a hoje")
    private Date data;

    @DecimalMin(value = "0.00", message = "Valor minimo do ingresso: R$0,00")
    private BigDecimal valorIngresso;

    private GenerosMusicais estiloMusical;

    @ManyToOne
    private CasaDeShow casaDeShow;

    @Lob
    private byte[] foto;


}
