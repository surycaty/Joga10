package com.surycaty.tirartime.db;

/**
 * Created by negus on 14/10/17.
 */

public class SQL {

    public static String getCreateJogador() {

        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS JOGADOR ( ");
        sql.append(" _id INTEGER NOT NULL ")
                .append(" PRIMARY KEY AUTOINCREMENT, ")
                .append(" nome VARCHAR (120), ")
                .append(" posicao VARCHAR (4), ")
                .append(" level INTEGER )");

        return sql.toString();
    }
}
