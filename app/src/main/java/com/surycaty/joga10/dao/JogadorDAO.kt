package com.surycaty.joga10.dao

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.surycaty.joga10.db.Database
import com.surycaty.joga10.entidade.Jogador
import java.util.*

/**
 * Created by Adriano Surycaty on 14/10/17.
 */
class JogadorDAO(private val context: Context?) {

    private val tabela = "JOGADOR"
    private var db: Database? = null
    private var conn: SQLiteDatabase? = null

    val listaJogadores: ArrayList<Jogador>
        get() {

            val jogadores = ArrayList<Jogador>()

            try {
                db = Database(context)
                conn = db!!.readableDatabase

                val cursor = conn!!.query(tabela, null, null, null, null, null, null)

                while (cursor.moveToNext()) {
                    val j = Jogador(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3))
                    jogadores.add(j)
                }


            } catch (ex: SQLException) {

            } finally {
                conn!!.close()
            }

            return jogadores
        }

    fun salvar(jogador: Jogador): Jogador? {

        try {

            db = Database(context)
            conn = db!!.writableDatabase

            if (jogador.id > 0) {
                conn!!.execSQL(getUpdateJogador(jogador))

            } else {

                val values = ContentValues()
                values.put("nome", jogador.nome)
                values.put("posicao", jogador.posicao)
                values.put("level", jogador.level)

                val id = Integer.valueOf(conn!!.insertOrThrow(tabela, null, values).toString())!!
                jogador.id = id

            }

            return jogador

        } catch (ex: SQLException) {
            Log.d("ERRO:", ex.message)

        } finally {
            conn!!.close()
        }

        return null
    }

    fun excluir(jogador: Jogador) {

        val sql = StringBuilder("DELETE FROM JOGADOR WHERE _id = ").append(jogador.id)

        try {
            db = Database(context)
            conn = db!!.writableDatabase

            if (jogador.id > 0) {
                conn!!.execSQL(sql.toString())

            }
        } catch (ex: SQLException) {
            Log.d("ERRO:", ex.message)

        } finally {
            conn!!.close()
        }


    }

    private fun getInsertJogador(jogador: Jogador): String {

        val sql = StringBuilder("INSERT INTO JOGADOR ")
        sql.append(" ( nome, posicao, level ) VALUES ")
        sql.append(" ('").append(jogador.nome).append("', ")
        sql.append(" '").append(jogador.posicao).append("', ")
        sql.append(jogador.level).append(") ")

        return sql.toString()
    }

    private fun getUpdateJogador(jogador: Jogador): String {

        val sql = StringBuilder("UPDATE JOGADOR SET ")
        sql.append(" nome = '").append(jogador.nome).append("',")
        sql.append(" posicao = '").append(jogador.posicao).append("',")
        sql.append(" level = ").append(jogador.level)
        sql.append(" WHERE _id = ").append(jogador.id)

        return sql.toString()
    }

    companion object {

        val listaPosicoes = arrayOf("ATA", "GOL", "ZAG", "LD", "LE", "VOL", "MD", "ME", "SA", "PD", "PE")
        //val listaPosicoes = arrayOf("Atacante", "Goleiro", "Zagueiro", "Lateral Direito", "Lateral Esquerdo", "Volante", "Meia-Direita", "Meia-Esquerda", "Centro-Avante")

        val createJogador: String
            get() {

                val sql = StringBuilder("CREATE TABLE IF NOT EXISTS JOGADOR ( ")
                sql.append(" _id INTEGER NOT NULL ")
                        .append(" PRIMARY KEY AUTOINCREMENT, ")
                        .append(" nome VARCHAR (120), ")
                        .append(" posicao VARCHAR (4), ")
                        .append(" level INTEGER )")

                return sql.toString()
            }
    }

}
