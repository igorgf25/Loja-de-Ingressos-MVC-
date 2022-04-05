package com.desafio.gft.entities;

import com.desafio.gft.enums.GenerosMusicais;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    private LocalTime horas;

    @DecimalMin(value = "0.00", message = "Valor minimo do ingresso: R$0,00")
    private BigDecimal valorIngresso;

    private GenerosMusicais estiloMusical;

    @ManyToOne
    private CasaDeShow casaDeShow;

    @Lob
    private byte[] foto;

    public Evento(String nome, String descricao, Long capacidade, Date data, LocalTime horas, BigDecimal valorIngresso, GenerosMusicais estiloMusical, CasaDeShow casaDeShow, byte[] foto) {
        this.nome = nome;
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.data = data;
        this.horas = horas;
        this.valorIngresso = valorIngresso;
        this.estiloMusical = estiloMusical;
        this.casaDeShow = casaDeShow;
        this.foto = foto;
    }
}
