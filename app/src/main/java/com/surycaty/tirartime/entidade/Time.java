package com.surycaty.tirartime.entidade;

import java.io.Serializable;
import java.util.List;

/**
 * Created by negus on 03/10/17.
 */

public class Time implements Serializable {

    public Time(int id, String nome, List<Jogador> jogadores) {
        this.id = id;
        this.nome = nome;
        this.jogadores = jogadores;
    }

    private int id;

    private String nome;

    private List<Jogador> jogadores;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome(){ return this.nome; }

    public void setNome(String nome) { this.nome = nome; }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
}
