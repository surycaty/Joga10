package com.surycaty.joga10.entidade

import java.util.ArrayList

/**
 * Created by negus on 27/09/17.
 */

object JogadorTest {

    fun listaJogadores(): ArrayList<Jogador> {

        var lista: ArrayList<Jogador> = ArrayList();

        var j1: Jogador = Jogador(1, "Israel", "ATA",5, false);
        lista.add(j1);
        var j2 = Jogador(2, "Honorato", "MC",5, false);
        lista.add(j2);
        var j3: Jogador = Jogador(3, "Geleade", "MD",5, false);
        lista.add(j3);
        var j4: Jogador = Jogador(4, "Carolino", "MD",5, false);
        lista.add(j4);
        var j5: Jogador = Jogador(5, "Magão", "",5, false);
        lista.add(j5);
        var j6: Jogador = Jogador(6, "Valber", "ZAG",5, false);
        lista.add(j6);
        var j7: Jogador = Jogador(7, "Dionísio", "",5, false);
        lista.add(j7);
        var j8: Jogador = Jogador(8, "Carburador", "",5, false);
        lista.add(j8);
        var j9: Jogador = Jogador(9, "Fabrício", "",5, false);
        lista.add(j9);
        var j10: Jogador = Jogador(10, "Bomba", "",5, false);
        lista.add(j10);
        var j11: Jogador = Jogador(11, "Daniel Mucuim", "",5, false);
        lista.add(j11);
        var j12: Jogador = Jogador(12, "Diego Bessa", "",5, false);
        lista.add(j12);
        var j13: Jogador = Jogador(13, "Diego da Vila", "",5, false);
        lista.add(j13);
        var j14: Jogador = Jogador(14, "Thiago", "",5, false);
        lista.add(j14);
        var j15: Jogador = Jogador(15, "Naza", "",5, false);
        lista.add(j15);
        var j16: Jogador = Jogador(16, "Lucas", "",5, false);
        lista.add(j16);
        var j17: Jogador = Jogador(17, "Mardonio", "",5, false);
        lista.add(j17);
        var j18: Jogador = Jogador(18, "Marcelo", "",5, false);
        lista.add(j18);
        var j19: Jogador = Jogador(19, "Erivelton", "",5, false);
        lista.add(j19);
        var j20: Jogador = Jogador(20, "Sury SR4", "",5, false);
        lista.add(j20);
        var j21: Jogador = Jogador(21, "Borussia", "",5, false);
        lista.add(j21);
        var j22: Jogador = Jogador(22, "Guilherme", "",5, false);
        lista.add(j22);
        var j23: Jogador = Jogador(23, "Renato", "",5, false);
        lista.add(j23);
        var j24: Jogador = Jogador(24, "Naza", "",5, false);
        lista.add(j24);
        var j25: Jogador = Jogador(25, "Everton", "",5, false);
        lista.add(j25);
        var j26: Jogador = Jogador(26, "Jogador 01", "",5, false);
        lista.add(j26);
        var j27: Jogador = Jogador(27, "Jogador 02", "",5, false);
        lista.add(j27);
        var j28: Jogador = Jogador(28, "Jogador 03", "",5, false);
        lista.add(j28);
        var j29: Jogador = Jogador(29, "Jogador 04", "",5, false);
        lista.add(j29);
        var j30: Jogador = Jogador(30, "Jogador 05 Com O Nome Para Teste", "",5, false);
        lista.add(j30);

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
        //nomeTimes.add("Roma")
        nomeTimes.add("Atlético Madrid")
        nomeTimes.add("Liverpool")
        //nomeTimes.add("Sevilha")
        //nomeTimes.add("Mônaco")
        nomeTimes.add("City")
        //nomeTimes.add("Milan")
        //nomeTimes.add("Lyon")

        return nomeTimes
    }
}
