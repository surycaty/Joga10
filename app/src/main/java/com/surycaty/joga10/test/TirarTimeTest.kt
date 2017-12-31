package com.surycaty.joga10.test

import com.surycaty.joga10.entidade.Jogador
import com.surycaty.joga10.entidade.JogadorTest
import com.surycaty.joga10.entidade.Time

import java.util.ArrayList
import java.util.Collections
import java.util.Comparator
import java.util.Random

/**
 * Created by negus on 04/10/17.
 */

object TirarTimeTest {

    /**
     * @param args
     */
    @JvmStatic
    fun main(args: Array<String>) {

        println("INICIANDO PROGRAMA E POPULANDO JOGADORES...")

        val jogadores = JogadorTest.listaJogadores()
        val times = ArrayList<Time>()
        val nomeTimes = JogadorTest.listaNomeTimes()

        val jogadoresPorTime = 5

        val nivelOrdenado = ArrayList<Int>()

        println("INICIANDO LACO PARA ORDENAR NIVEL...")
        for (i in 5 downTo 1) {
            for (j in jogadores) {
                if (j.level == i) {
                    nivelOrdenado.add(j.level)
                }
            }
        }
        println("NIVEL ORDENADO: " + nivelOrdenado.toString())


        var nivelAtual = 0
        val nivelFiltrado = ArrayList<Int>()

        println("INICIANDO LACO PARA FILTRAR NIVEL...")
        for (i in nivelOrdenado) {
            if (nivelAtual == 0) {
                nivelFiltrado.add(i)
            } else {
                if (i != nivelAtual) {
                    nivelFiltrado.add(i)
                }
            }
            nivelAtual = i
        }
        println("NIVEL FILTRADO: " + nivelFiltrado.toString())


        println("INICIANDO DESORDENAR NIVEL FILTRADO...")
        Collections.shuffle(nivelFiltrado)
        println("NIVEL FILTRADO: " + nivelFiltrado.toString())

        println("INICIAR POPULAR TIMES...")


        val numTimes = jogadores.size / jogadoresPorTime
        println("N DE TIMES: " + numTimes)

        //		if(jogadores.size() % jogadoresPorTime > 0)
        //			numTimes++;

        println("N DE TIMES ATUALIZADOS: " + numTimes)

        for (i in 0 until jogadoresPorTime) {
            if (i == 0) {
                for (t in 0 until numTimes) {
                    Collections.shuffle(nomeTimes)
                    val nome = nomeTimes[0]
                    times.add(Time(t, nome, ArrayList()))
                    //nomeTimes.removeAt(0)
                }

                if (jogadores.size % jogadoresPorTime > 0) {
                    Collections.shuffle(nomeTimes)
                    val nome = nomeTimes[0]
                    times.add(Time(times.size, nome, ArrayList()))
                }
                println("TIMES INSTANCIADOS: " + times.toString())
            }

            for (time in times) {
                nivelAtual = nivelFiltrado[0]

                val tam = jogadores.size
                var hasNivel = false

                for (l in 0 until tam) {
                    if (jogadores[l].level == nivelAtual) {
                        hasNivel = true
                        break
                    }
                }

                if (!hasNivel) {
                    nivelFiltrado.removeAt(0)
                    if (nivelFiltrado.size > 0) {
                        nivelAtual = nivelFiltrado[0]
                    }

                }

                println("NIVELAtual Selecionando: " + nivelAtual)
                val jogadorSelecionado = selecionaJogador(jogadores, nivelAtual)

                if (jogadorSelecionado != null) {
                    println("Jogador Selecionado: " + jogadorSelecionado.toString())
                    //time.jogadores!!.add(jogadorSelecionado)

                    //jogadores.remove(jogadorSelecionado)
                }
            }

        }

        println("Escalacao:")

        for (t in times) {
            println("Jogadores do Time: " + t.nome!!)
            for (j in t.jogadores!!) {
                println("Nome: " + j.nome + " - Lvl: " + j.level)
            }
            println("")
        }


        //		if(jogadores.size() % jogadoresPorTime > 0) {
        //			times.add(new Time(times.size(), "Time " + times.size(), new ArrayList<Jogador>()));
        //			times.get(times.size() - 1).setJogadores(jogadores);
        //			System.out.println("Time Reserva: " + times.get(times.size() - 1).getNome());
        //			for(Jogador j : times.get(times.size() - 1).getJogadores()) {
        //				System.out.println("Nome: " + j.getNome() + " - Lvl: " + j.getLevel());
        //			}
        //		}

        println("FIM PROGRAMA")
    }

    private fun selecionaJogador(jogadores: List<Jogador>,
                                 nivelAtual: Int): Jogador? {

        val aux = ArrayList<Jogador>()

        for (j in jogadores) {
            if (j.level == nivelAtual)
                aux.add(j)
        }

        if (aux.size > 1) {
            Collections.shuffle(aux)
        }

        return if (aux.isEmpty()) null else aux[0]
    }

}