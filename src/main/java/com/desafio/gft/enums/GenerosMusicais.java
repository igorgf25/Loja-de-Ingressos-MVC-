package com.desafio.gft.enums;

import lombok.Getter;

@Getter
public enum GenerosMusicais {
    AXE ("Axé"),
    BLUES ("Blues"),
    COUNTRY ("Country"),
    ELETRONICA ("Eletrônica"),
    FORRO ("Forró"),
    FUNK ("Funk"),
    GOSPEL ("Gospel"),
    HIPHOP ("Hip Hop"),
    JAZZ ("Jazz"),
    MPB ("MPB"),
    MUSICA_CLASSICA ("Música Clásssica"),
    PAGODE ("Pagode"),
    POP ("Pop"),
    RAP ("Rap"),
    REGGAE ("Raggae"),
    ROCK ("Rock"),
    SAMBA ("Samba"),
    SERTANEJO ("Sertanejo");

    private final String nome;

    GenerosMusicais(String nome) {
        this.nome = nome;
    }
}
