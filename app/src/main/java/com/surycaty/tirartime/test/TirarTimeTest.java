package com.surycaty.tirartime.test;

import com.surycaty.tirartime.entidade.Jogador;
import com.surycaty.tirartime.entidade.JogadorTest;
import com.surycaty.tirartime.entidade.Time;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by negus on 04/10/17.
 */

public class TirarTimeTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("INICIANDO PROGRAMA E POPULANDO JOGADORES...");

        List<Jogador> jogadores = JogadorTest.listaJogadores();
        List<Time> times = new ArrayList<Time>();
        List<String> nomeTimes = JogadorTest.listaNomeTimes();

        int jogadoresPorTime = 5;

        List<Integer> nivelOrdenado = new ArrayList<Integer>();

        System.out.println("INICIANDO LACO PARA ORDENAR NIVEL...");
        for(int i = 5; i > 0; i--) {
            for(Jogador j : jogadores) {
                if(j.getLevel() == i) {
                    nivelOrdenado.add(j.getLevel());
                }
            }
        }
        System.out.println("NIVEL ORDENADO: " + nivelOrdenado.toString());


        int nivelAtual = 0;
        List<Integer> nivelFiltrado = new ArrayList<Integer>();

        System.out.println("INICIANDO LACO PARA FILTRAR NIVEL...");
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
        System.out.println("NIVEL FILTRADO: " + nivelFiltrado.toString());


        System.out.println("INICIANDO DESORDENAR NIVEL FILTRADO...");
        Collections.shuffle(nivelFiltrado);
        System.out.println("NIVEL FILTRADO: " + nivelFiltrado.toString());

        System.out.println("INICIAR POPULAR TIMES...");


        int numTimes = jogadores.size() / jogadoresPorTime;
        System.out.println("N DE TIMES: " + numTimes);

//		if(jogadores.size() % jogadoresPorTime > 0)
//			numTimes++;

        System.out.println("N DE TIMES ATUALIZADOS: " + numTimes);

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
                System.out.println("TIMES INSTANCIADOS: " + times.toString());
            }

            for(Time time : times) {
                nivelAtual = nivelFiltrado.get(0);

                int tam = jogadores.size();
                boolean hasNivel = false;

                for(int l = 0; l < tam; l++){
                    if(jogadores.get(l).getLevel() == nivelAtual) {
                        hasNivel = true;
                        break;
                    }
                }

                if(!hasNivel) {
                    nivelFiltrado.remove(0);
                    if(nivelFiltrado.size() > 0) {
                        nivelAtual = nivelFiltrado.get(0);
                    }

                }

                System.out.println("NIVELAtual Selecionando: " + nivelAtual);
                Jogador jogadorSelecionado = selecionaJogador(jogadores, nivelAtual);

                if(jogadorSelecionado != null) {
                    System.out.println("Jogador Selecionado: " + jogadorSelecionado.toString());
                    time.getJogadores().add(jogadorSelecionado);

                    jogadores.remove(jogadorSelecionado);
                }
            }

        }

        System.out.println("Escalacao:");

        for(Time t : times) {
            System.out.println("Jogadores do Time: " + t.getNome());
            for(Jogador j : t.getJogadores()) {
                System.out.println("Nome: " + j.getNome() + " - Lvl: " + j.getLevel());
            }
            System.out.println("");
        }



//		if(jogadores.size() % jogadoresPorTime > 0) {
//			times.add(new Time(times.size(), "Time " + times.size(), new ArrayList<Jogador>()));
//			times.get(times.size() - 1).setJogadores(jogadores);
//			System.out.println("Time Reserva: " + times.get(times.size() - 1).getNome());
//			for(Jogador j : times.get(times.size() - 1).getJogadores()) {
//				System.out.println("Nome: " + j.getNome() + " - Lvl: " + j.getLevel());
//			}
//		}

        System.out.println("FIM PROGRAMA");
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