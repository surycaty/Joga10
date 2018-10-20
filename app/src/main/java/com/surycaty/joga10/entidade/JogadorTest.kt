package com.surycaty.joga10.entidade

import java.util.ArrayList

/**
 * Created by Surycaty on 27/09/17.
 */
object JogadorTest {

    fun listaJogadores(): ArrayList<Jogador> {

        var lista: ArrayList<Jogador> = ArrayList()

        var j1 = Jogador(1, "Da Silva", "GOL",5, false)
        lista.add(j1)
        var j2 = Jogador(2, "Ferreira", "LE",5, false)
        lista.add(j2)
        var j3 = Jogador(3, "Vincento", "ZAG",5, false)
        lista.add(j3)
        var j4: Jogador = Jogador(4, "Paco", "ZAG",5, false)
        lista.add(j4)
        var j5: Jogador = Jogador(5, "Cícero", "LD",5, false)
        lista.add(j5)
        var j6: Jogador = Jogador(6, "Roca", "VOL",5, false)
        lista.add(j6)
        var j7: Jogador = Jogador(7, "Santos", "VOL",5, false)
        lista.add(j7)
        var j8: Jogador = Jogador(8, "Pardilla", "ME",5, false)
        lista.add(j8)
        var j9: Jogador = Jogador(9, "Beranco", "MD",5, false)
        lista.add(j9)
        var j10: Jogador = Jogador(10, "Gomez", "ATA",5, false)
        lista.add(j10)
        var j11: Jogador = Jogador(11, "Allejo", "ATA",5, false)
        lista.add(j11)

        return lista
    }

    fun listaNomeTimes(): ArrayList<String> {

        val nomeTimes = ArrayList<String>()

        nomeTimes.add("Barcelona")
        nomeTimes.add("Real")
        nomeTimes.add("Juventus")
        //nomeTimes.add("PSG")
        nomeTimes.add("Bayern")
        nomeTimes.add("Borussia")
        nomeTimes.add("Chelsea")
        nomeTimes.add("Manchester United")
        nomeTimes.add("Roma")
        nomeTimes.add("Atlético Madrid")
        nomeTimes.add("Liverpool")
        nomeTimes.add("Sevilha")
        //nomeTimes.add("Mônaco")
        nomeTimes.add("City")
        nomeTimes.add("Milan")
        //nomeTimes.add("Lyon")

        return nomeTimes
    }
}
