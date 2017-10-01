package com.surycaty.tirartime;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.surycaty.tirartime.entidade.Jogador;
import com.surycaty.tirartime.entidade.JogadorTest;
import com.surycaty.tirartime.util.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelecionarJogadoresActivity extends AppCompatActivity {

    private List<Jogador> jogadores = new ArrayList<Jogador>();
    private List<CheckBox> selecionados = new ArrayList<CheckBox>();
    private int qtdTimes = 5;
    private int qtdJogadores = 5;
    private EditText etNumTimes;
    private EditText etNumJogadores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_jogadores);

        etNumTimes = (EditText) findViewById(R.id.numTimes);
        etNumJogadores = (EditText) findViewById(R.id.numJogadores);

        Button btnUpTimes = (Button) findViewById(R.id.btnUpTimes);
        btnUpTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qtdTimes < 5)
                    ++qtdTimes;

                etNumTimes.setText(String.valueOf(qtdTimes));
            }
        });

        Button btnDownTimes = (Button) findViewById(R.id.btnDownTimes);
        btnDownTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qtdTimes > 2)
                    --qtdTimes;

                etNumTimes.setText(String.valueOf(qtdTimes));
            }
        });

        Button btnUpJogadores = (Button) findViewById(R.id.btnUpJogadores);
        btnUpJogadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qtdJogadores < 11)
                    ++qtdJogadores;

                etNumJogadores.setText(String.valueOf(qtdJogadores));
            }
        });

        Button btnDownJogadores = (Button) findViewById(R.id.btnDownJogadores);
        btnDownJogadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qtdJogadores > 2)
                    --qtdJogadores;

                etNumJogadores.setText(String.valueOf(qtdJogadores));
            }
        });


        jogadores = JogadorTest.listaJogadores();
        TableLayout table = (TableLayout) findViewById(R.id.idTableLayout);

        int top = 150;

        boolean isPar = false;
        boolean isPrimeira = true;

        for(Jogador j : jogadores) {

            CheckBox ck = new CheckBox(getApplicationContext());
            ck.setId(j.getId());
            ck.setText(j.getNome());
            ck.setTextSize(18f);
            ck.setTextColor(Color.BLACK);
            ck.setBackgroundColor(Color.BLUE);
            ck.setPadding(10, 10, 10, 10);

            selecionados.add(ck);

            TableRow row = new TableRow(getApplicationContext());
            row.addView(ck);

            RatingBar ratingBar = new RatingBar(getApplicationContext());
            ratingBar.setNumStars(5);
            ratingBar.setRating((float)j.getLevel());
            ratingBar.setEnabled(false);
            ratingBar.setScaleY(0.5f);
            ratingBar.setScaleX(0.5f);
            ratingBar.setPaddingRelative(0,0,0,0);
            ratingBar.setBackgroundColor(Color.RED);

            row.addView(ratingBar);

            if(isPrimeira){
                isPar = true;
                isPrimeira = false;
            } else {
                if(isPar){
                    row.setBackgroundColor(Color.GRAY);
                    isPar = false;
                } else {
                    row.setBackgroundColor(Color.WHITE);
                    isPar = true;
                }
            }

            //row.setPaddingRelative(10,10,10,10);
            row.setDividerPadding(10);

            table.addView(row);
        }

        Button btnTirarTime = (Button) findViewById(R.id.btnTirarTime);

        btnTirarTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast mensagem = null;
                boolean hasErro = false;
                String textoErro = "";

                qtdTimes     = Integer.valueOf(etNumTimes.getText().toString());
                qtdJogadores = Integer.valueOf(etNumJogadores.getText().toString());

                if(qtdTimes < 2 || qtdTimes > 5) {
                    textoErro += "O Nº de times tem que estar entre 2 e 5, favor corrigir!";
                    hasErro = true;
                }

                if(qtdJogadores < 2 || qtdJogadores > 11) {

                    if(hasErro)
                        textoErro += "\n";

                    textoErro += "O Nº de jogadores tem que estar entre 2 e 11, favor corrigir!";

                    hasErro = true;
                }

                if(hasErro) {
                    mensagem = Utils.mensagem(getApplicationContext(), textoErro);
                    mensagem.show();;

                    return;
                }

                List<Jogador> lista = new ArrayList<Jogador>();

                for(CheckBox ck : selecionados) {
                    if(ck.isChecked()) {
                        for(Jogador j : jogadores) {
                            if(j.getId() == ck.getId())
                                lista.add(j);
                        }
                    }
                }


                boolean validacao = Utils.validarNumeroJogadores(lista.size(), qtdTimes);

                if(!validacao) {
                    mensagem = Utils.mensagem(getApplicationContext(), "Número de jogadores insulficiente para formar times.");
                    mensagem.show();
                    return;
                }

                Intent it = new Intent(SelecionarJogadoresActivity.this, TimesFormadosActivity.class);

                it.putExtra("listaSelecionados", (Serializable) lista);
                it.putExtra("numTimes", 4);

                startActivity(it);
            }
        });

    }
}
