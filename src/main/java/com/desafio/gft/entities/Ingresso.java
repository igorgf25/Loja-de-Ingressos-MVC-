package com.desafio.gft.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Ingresso {

    @Id
    private Long id;

    @NotEmpty(message = "Nome do ingresso é obrigatório")
    private String nome;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Não é possivel marcar eventos em uma data anterior a hoje")
    private Date data;

    private LocalTime horas;

    @DecimalMin(value = "0.00", message = "Valor minimo do ingresso: R$0,00")
    private BigDecimal valor;

    @Min(value = 1)
    private Integer quantidade;

    @ManyToOne
    private Usuario usuario;
}
