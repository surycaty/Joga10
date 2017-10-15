package com.surycaty.tirartime;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
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

import com.surycaty.tirartime.dao.JogadorDAO;
import com.surycaty.tirartime.entidade.Jogador;
import com.surycaty.tirartime.entidade.JogadorTest;
import com.surycaty.tirartime.entidade.Time;
import com.surycaty.tirartime.util.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SelecionarJogadoresActivity extends AppCompatActivity {

    private List<Jogador> jogadores = new ArrayList<Jogador>();
    private List<CheckBox> selecionados = new ArrayList<CheckBox>();

    private int qtdJogadores = 5;
    private EditText etNumJogadores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_jogadores);

        etNumJogadores = (EditText) findViewById(R.id.numJogadores);

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


        //jogadores = JogadorTest.listaJogadores();
        JogadorDAO jogadorDao = new JogadorDAO(getApplicationContext());
        jogadores = jogadorDao.getListaJogadores();

        TableLayout table = (TableLayout) findViewById(R.id.idTableLayout);

        //Cabecalho da Tabela
        TableRow rowHeader = new TableRow(getApplicationContext());

        TextView tvNomeJogadorHeader = new TextView(this);
        tvNomeJogadorHeader.setTextColor(Color.BLACK);
        tvNomeJogadorHeader.setText("Atleta");
        rowHeader.addView(tvNomeJogadorHeader);

        TextView tvPosicao = new TextView(this);
        tvPosicao.setTextColor(Color.BLACK);
        tvPosicao.setText("Posição");
        rowHeader.addView(tvPosicao);

        TextView tvNivelHeader = new TextView(this);
        tvNivelHeader.setTextColor(Color.BLACK);
        tvNivelHeader.setText("Estatísticas");
        tvNivelHeader.setTextSize(18f);
        tvNivelHeader.setPadding(30, 30, 30, 30);

        rowHeader.addView(tvNivelHeader);
        rowHeader.setBackgroundColor(Color.rgb(200,200,200));

        table.addView(rowHeader);

        int top = 150;

        boolean isPar = false;
        boolean isPrimeira = true;

        for(Jogador j : jogadores) {

            CheckBox ck = new CheckBox(getApplicationContext());
            ck.setId(j.getId());
            ck.setText(j.getNome());
            ck.setTextSize(18f);
            ck.setTextColor(Color.BLACK);
            //ck.setBackgroundColor(Color.BLUE);
            ck.setPadding(30, 30, 30, 30);

            selecionados.add(ck);

            TableRow row = new TableRow(getApplicationContext());
            row.addView(ck);

            TextView textView = new TextView(this);
            textView.setTextColor(Color.BLACK);
            textView.setText("MC");
            textView.setPadding(20, 32, 20, 32);
            row.addView(textView);

            RatingBar ratingBar = new RatingBar(getApplicationContext());
            ratingBar.setNumStars(5);
            ratingBar.setRating((float)j.getLevel());
            ratingBar.setEnabled(false);
            ratingBar.setScaleY(0.5f);
            ratingBar.setScaleX(0.5f);;
            //ratingBar.setBackgroundColor(Color.RED);
            //ratingBar.setSecondaryProgress(R.drawable.ic_star_press);
            //ratingBar.setProgress(R.drawable.ic_star_press);
            //ratingBar.setBackgroundResource(R.drawable.ic_star);
            //ratingBar.setProgressDrawable(R.drawable.custom_ratingbar);


            row.addView(ratingBar);

            if(isPrimeira){
                isPar = true;
                isPrimeira = false;
            } else {
                if(isPar){
                    row.setBackgroundColor(Color.rgb(200,200,200));
                    isPar = false;
                } else {
                    row.setBackgroundColor(Color.WHITE);
                    isPar = true;
                }
            }

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

                qtdJogadores = Integer.valueOf(etNumJogadores.getText().toString());

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

                if(lista.isEmpty() || (lista.size() < qtdJogadores * 2)) {
                    mensagem = Utils.mensagem(getApplicationContext(), "Número de jogadores insulficiente para formar times.\nFavor Verificar o Nº de jogadores por times");
                    mensagem.show();
                    return;
                }

                List<Time> times = sortearTimes(lista);
                Intent it = new Intent(SelecionarJogadoresActivity.this, TimesFormadosActivity.class);

                it.putExtra("timesFormados", (Serializable) times);

                startActivity(it);
            }
        });

    }

    private List<Time> sortearTimes(List<Jogador> lista) {

        List<Jogador> jogadores = lista;
        List<Time> times = new ArrayList<Time>();
        List<String> nomeTimes = JogadorTest.listaNomeTimes();

        int jogadoresPorTime = qtdJogadores;

        List<Integer> nivelOrdenado = new ArrayList<Integer>();

        for(int i = 5; i > 0; i--) {
            for(Jogador j : jogadores) {
                if(j.getLevel() == i) {
                    nivelOrdenado.add(j.getLevel());
                }
            }
        }

        int nivelAtual = 0;
        List<Integer> nivelFiltrado = new ArrayList<Integer>();

        for(Integer i : nivelOrdenado) {
            if(nivelAtual == 0) {
                nivelFiltrado.add(i);
            } else {
                if(i != nivelAtual) {
                    nivelFiltrado.add(i);
                }
            }
            nivelAtual = i;
        }

        Collections.shuffle(nivelFiltrado);

        int numTimes = jogadores.size() / jogadoresPorTime;

        for(int i = 0; i < jogadoresPorTime; i++){
            if(i == 0) {
                for (int t = 0; t < numTimes; ++t) {
                    Collections.shuffle(nomeTimes);
                    String nome = nomeTimes.get(0);
                    times.add(new Time(t, nome, new ArrayList<Jogador>()));
                    nomeTimes.remove(0);
                }

                if(jogadores.size() % jogadoresPorTime > 0) {
                    Collections.shuffle(nomeTimes);
                    String nome = nomeTimes.get(0);
                    times.add(new Time(times.size(), nome, new ArrayList<Jogador>()));
                }
            }

            for(Time time : times) {
                if(!nivelFiltrado.isEmpty())
                    nivelAtual = nivelFiltrado.get(0);

                int tam = jogadores.size();
                boolean hasNivel = false;

                for(int l = 0; l < tam; l++){
                    if(jogadores.get(l).getLevel() == nivelAtual) {
                        hasNivel = true;
                        break;
                    }
                }

                if(!hasNivel && nivelFiltrado.size() > 0) {
                    nivelFiltrado.remove(0);
                    if(nivelFiltrado.size() > 0) {
                        nivelAtual = nivelFiltrado.get(0);
                    }
                }

                Jogador jogadorSelecionado = selecionaJogador(jogadores, nivelAtual);

                if(jogadorSelecionado != null) {
                    time.getJogadores().add(jogadorSelecionado);

                    jogadores.remove(jogadorSelecionado);
                }
            }

        }

        if(numTimes < times.size()) {
            for(Time t : times) {
                if(t.getJogadores().size() < jogadoresPorTime) {
                    Jogador remove = times.get(times.size() - 1).getJogadores().get(0);
                    t.getJogadores().add(remove);

                    if(times.get(times.size() - 1).getJogadores().size() > 0) {
                        times.get(times.size() - 1).getJogadores().remove(remove);
                    }
                }
            }
        }

        /*System.out.println("Escalacao:");
        for(Time t : times) {
            System.out.println("Jogadores do Time: " + t.getNome());
            for(Jogador j : t.getJogadores()) {
                System.out.println("Nome: " + j.getNome() + " - Lvl: " + j.getLevel());
            }
            System.out.println("");
        }*/

        return times;
    }

    private static Jogador selecionaJogador(List<Jogador> jogadores,
                                            int nivelAtual) {

        List<Jogador> aux = new ArrayList<Jogador>();

        for(Jogador j : jogadores) {
            if(j.getLevel() == nivelAtual)
                aux.add(j);
        }

        if(aux.size() > 1) {
            Collections.shuffle(aux);
        }

        return (aux.isEmpty())? null : aux.get(0);
    }

}
