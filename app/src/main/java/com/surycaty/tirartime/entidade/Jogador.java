package com.surycaty.tirartime.entidade;

import java.io.Serializable;

/**
 * Created by negus on 27/09/17.
 */

public class Jogador implements Serializable {

    private int id;
    private String nome;
    private int level;

    public Jogador(){}

    public Jogador(int id, String nome, int level) {
        this.id = id;
        this.nome = nome;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
