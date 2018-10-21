package com.surycaty.joga10.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

import com.surycaty.joga10.R
import com.surycaty.joga10.entidade.Time
import com.surycaty.joga10.util.Constantes


class TimesFormadosActivity : AppCompatActivity() {

    private var times: List<Time>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_times_formados)

        val i = intent
        times = i.getSerializableExtra("timesFormados") as List<Time>

        val table = findViewById<TableLayout>(R.id.idTableTimesFormados)

        for (time in times!!) {

            //Cabecalho da Tabela
            val rowHeader = TableRow(applicationContext)

            val tvNomeJogadorHeader = TextView(this)
            tvNomeJogadorHeader.setTextColor(Color.BLACK)
            tvNomeJogadorHeader.text = time.nome
            tvNomeJogadorHeader.textSize = 18f
            tvNomeJogadorHeader.setTextColor(Color.BLACK)
            tvNomeJogadorHeader.setPadding(30, 10, 30, 10)

            rowHeader.addView(tvNomeJogadorHeader)


            val tvLevelHeader = TextView(this)
            tvLevelHeader.setTextColor(Color.BLACK)
            tvLevelHeader.text = Constantes.LEVEL
            tvLevelHeader.textSize = 18f
            tvLevelHeader.setTextColor(Color.BLACK)
            tvLevelHeader.setPadding(30, 10, 30, 10)


            rowHeader.addView(tvLevelHeader)
            rowHeader.setBackgroundColor(Color.GRAY)

            table.addView(rowHeader)

            for (j in time.jogadores!!) {
                val row = TableRow(applicationContext)

                val nomeJogador = TextView(applicationContext)
                nomeJogador.text = j.nome
                nomeJogador.setTextColor(Color.BLACK)
                nomeJogador.textSize = 18f
                nomeJogador.setTextColor(Color.BLACK)
                nomeJogador.setPadding(30, 10, 30, 10)

                row.addView(nomeJogador)

                val ratingBar = RatingBar(applicationContext)
                ratingBar.numStars = 5
                ratingBar.rating = j.level.toFloat()
                ratingBar.isEnabled = false
                ratingBar.scaleY = 0.5f
                ratingBar.scaleX = 0.5f

                row.addView(ratingBar)

                table.addView(row)
            }

            val rowEmpty = TableRow(applicationContext)
            table.addView(rowEmpty)
        }

    }
}
