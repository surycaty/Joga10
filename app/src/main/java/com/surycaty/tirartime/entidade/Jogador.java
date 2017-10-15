package com.surycaty.tirartime.entidade;

import java.io.Serializable;

/**
 * Created by negus on 27/09/17.
 */

public class Jogador implements Serializable {

    private Integer id;
    private String nome;
    private String posicao;
    private int level;

    public Jogador(){}

    public Jogador(int id, String nome, String posicao, int level) {
        this.id = id;
        this.nome = nome;
        this.posicao = posicao;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
