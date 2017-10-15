package com.surycaty.tirartime.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.surycaty.tirartime.db.Database;
import com.surycaty.tirartime.entidade.Jogador;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by negus on 14/10/17.
 */

public class JogadorDAO {

    private final String tabela = "JOGADOR";
    private Database db;
    private SQLiteDatabase conn;
    private Context context;

    public JogadorDAO(Context context){
        this.context = context;
    }

    public Jogador salvar(Jogador jogador) {

        try {

            db = new Database(context);
            conn = db.getWritableDatabase();

            if (jogador.getId() > 0) {
                getUpdateJogador(jogador);
            }

            ContentValues values = new ContentValues();
            values.put("nome", jogador.getNome());
            values.put("posicao", jogador.getPosicao());
            values.put("level", jogador.getLevel());

            int id = Integer.valueOf(String.valueOf(conn.insertOrThrow(tabela, null, values)));
            jogador.setId(id);

            return jogador;

        } catch (SQLException ex) {

        } finally {
            conn.close();
        }

        return null;
    }

    public List<Jogador> getListaJogadores () {

        List<Jogador> jogadores = new ArrayList<Jogador>();

        try {
            db = new Database(context);
            conn = db.getReadableDatabase();

            Cursor cursor = conn.query(tabela, null, null, null, null, null, null);

            while(cursor.moveToNext()) {
                Jogador j = new Jogador(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
                jogadores.add(j);
            }


        } catch (SQLException ex) {

        } finally {
            conn.close();
        }

        return jogadores;
    }

    public static String getCreateJogador() {

        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS JOGADOR ( ");
        sql.append(" _id INTEGER NOT NULL ")
                .append(" PRIMARY KEY AUTOINCREMENT, ")
                .append(" nome VARCHAR (120), ")
                .append(" posicao VARCHAR (4), ")
                .append(" level INTEGER )");

        return sql.toString();
    }

    private String getInsertJogador(Jogador jogador) {

        StringBuilder sql = new StringBuilder("INSERT INTO JOGADOR ");
        sql.append(" ( nome, posicao, level ) VALUES ");
        sql.append(" ('").append(jogador.getNome()).append("', ");
        sql.append(" '").append(jogador.getPosicao()).append("', ");
        sql.append(jogador.getLevel()).append(") ");

        return sql.toString();
    }

    private String getUpdateJogador(Jogador jogador) {

        StringBuilder sql = new StringBuilder("UPDATE JOGADOR SET ");
        sql.append(" nome = '").append(jogador.getNome()).append("'");
        sql.append(" posicao = '").append(jogador.getPosicao()).append("'");
        sql.append(" level = ").append(jogador.getLevel());
        sql.append(" WHERE _id = ").append(jogador.getId());

        return sql.toString();
    }
}
