package com.surycaty.tirartime;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.surycaty.tirartime.entidade.Jogador;
import com.surycaty.tirartime.entidade.Time;

import java.util.ArrayList;
import java.util.List;

public class TimesFormadosActivity extends AppCompatActivity {

    private List<Time> times = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_formados);

        Intent i = getIntent();
        times = (List<Time>) i.getSerializableExtra("timesFormados");

        TableLayout table = (TableLayout) findViewById(R.id.idTableTimesFormados);

        for(Time time : times) {

            //Cabecalho da Tabela
            TableRow rowHeader = new TableRow(getApplicationContext());

            TextView tvNomeJogadorHeader = new TextView(this);
            tvNomeJogadorHeader.setTextColor(Color.BLACK);
            tvNomeJogadorHeader.setText(time.getNome());
            tvNomeJogadorHeader.setTextSize(18f);
            tvNomeJogadorHeader.setTextColor(Color.BLACK);
            tvNomeJogadorHeader.setPadding(30, 10, 30, 10);


            rowHeader.addView(tvNomeJogadorHeader);


            TextView tvLevelHeader = new TextView(this);
            tvLevelHeader.setTextColor(Color.BLACK);
            tvLevelHeader.setText("Level");
            tvLevelHeader.setTextSize(18f);
            tvLevelHeader.setTextColor(Color.BLACK);
            tvLevelHeader.setPadding(30, 10, 30, 10);


            rowHeader.addView(tvLevelHeader);
            rowHeader.setBackgroundColor(Color.GRAY);

            table.addView(rowHeader);

            for(Jogador j: time.getJogadores()) {
                TableRow row = new TableRow(getApplicationContext());

                TextView nomeJogador = new TextView(getApplicationContext());
                nomeJogador.setText(j.getNome());
                nomeJogador.setTextColor(Color.BLACK);
                nomeJogador.setTextSize(18f);
                nomeJogador.setTextColor(Color.BLACK);
                nomeJogador.setPadding(30, 10, 30, 10);

                row.addView(nomeJogador);

                RatingBar ratingBar = new RatingBar(getApplicationContext());
                ratingBar.setNumStars(5);
                ratingBar.setRating((float)j.getLevel());
                ratingBar.setEnabled(false);
                ratingBar.setScaleY(0.5f);
                ratingBar.setScaleX(0.5f);

                row.addView(ratingBar);

                table.addView(row);
            }

            TableRow rowEmpty = new TableRow(getApplicationContext());
            table.addView(rowEmpty);
        }

    }
}
