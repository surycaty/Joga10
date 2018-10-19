package com.surycaty.joga10.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.*
import com.surycaty.joga10.R
import com.surycaty.joga10.dao.JogadorDAO
import com.surycaty.joga10.entidade.Jogador
import com.surycaty.joga10.entidade.JogadorTest
import com.surycaty.joga10.entidade.Time
import com.surycaty.joga10.util.Utils
import kotlinx.android.synthetic.main.activity_selecionar_jogadores.*
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class SelecionarJogadoresActivity : AppCompatActivity() {

    private var jogadores: List<Jogador> = ArrayList()
    private var adapter : SelecionarJogadoresActivity.CadastroJogadorAdapter? = null

    private var qtdJogadores = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selecionar_jogadores)

        var jogadorDao = JogadorDAO(this)
        jogadores = jogadorDao.listaJogadores

        this.adapter = SelecionarJogadoresActivity.CadastroJogadorAdapter(this@SelecionarJogadoresActivity, jogadores)

        lvSelecionarAtleta.adapter = this.adapter

        btnUpJogadores.setOnClickListener {
            if (qtdJogadores < 11)
                ++qtdJogadores

            numJogadores.setText(qtdJogadores.toString())

        }

        btnDownJogadores.setOnClickListener {
            if (qtdJogadores > 1)
                --qtdJogadores

            numJogadores.setText(qtdJogadores.toString())
        }

        btnTirarTime.setOnClickListener(View.OnClickListener {
            var mensagem: Toast? = null
            var hasErro = false
            var textoErro = ""

            qtdJogadores = Integer.valueOf(numJogadores.text.toString())!!

            if (qtdJogadores < 1 || qtdJogadores > 11) {

                if (hasErro)
                    textoErro += "\n"

                textoErro += "O Nº de jogadores tem que estar entre 1 e 11, favor corrigir!"

                hasErro = true
            }

            if (hasErro) {
                mensagem = Utils.mensagem(applicationContext, textoErro)
                mensagem.show()

                return@OnClickListener
            }

            val lista = ArrayList<Jogador>()

            //selecionados = adapter!!.list
            //val lista = buscarSelecionados()

            for (sel in this.adapter!!.list) {
                if (sel.isSelecionado) {
                    lista.add(sel)
                }
            }

            if (lista.isEmpty() || lista.size < qtdJogadores * 2) {
                mensagem = Utils.mensagem(applicationContext, "Número de jogadores insulficiente para formar times.\nFavor Verificar o Nº de jogadores por times")
                mensagem!!.show()
                return@OnClickListener
            }

            val times = sortearTimes(lista)
            val it = Intent(this@SelecionarJogadoresActivity, TimesFormadosActivity::class.java)

            it.putExtra("timesFormados", times as Serializable)

            startActivity(it)
        })

    }

    private fun sortearTimes(lista: MutableList<Jogador>): List<Time> {

        val times = mutableListOf<Time>()
        var nomeTimes = JogadorTest.listaNomeTimes()

        val jogadoresPorTime = qtdJogadores

        val nivelOrdenado = mutableListOf<Int>()

        for (i in 5 downTo 1) {
            for (j in lista) {
                if (j.level == i) {
                    nivelOrdenado.add(j.level)
                }
            }
        }

        var nivelAtual = 0
        val nivelFiltrado = ArrayList<Int>()

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

        nivelFiltrado.shuffle()

        val numTimes = lista.size / jogadoresPorTime

        for (i in 0 until jogadoresPorTime) {
            if (i == 0) {
                for (t in 0 until numTimes) {
                    nomeTimes.shuffle()
                    val nome = nomeTimes[0]
                    times.add(Time(t, nome, ArrayList()))
                    nomeTimes.removeAt(0)
                }

                if (lista.size % jogadoresPorTime > 0) {
                    Collections.shuffle(nomeTimes)
                    val nome = nomeTimes[0]
                    times.add(Time(times.size, nome, ArrayList()))
                }
            }

            for (time in times) {
                if (!nivelFiltrado.isEmpty())
                    nivelAtual = nivelFiltrado[0]

                val tam = lista.size
                var hasNivel = false

                for (l in 0 until tam) {
                    if (lista[l].level == nivelAtual) {
                        hasNivel = true
                        break
                    }
                }

                if (!hasNivel && nivelFiltrado.size > 0) {
                    nivelFiltrado.removeAt(0)
                    if (nivelFiltrado.size > 0) {
                        nivelAtual = nivelFiltrado[0]
                    }
                }

                val jogadorSelecionado = selecionaJogador(lista, nivelAtual)

                if (jogadorSelecionado != null) {
                    time.jogadores!!.add(jogadorSelecionado)

                    lista.remove(jogadorSelecionado)
                }
            }

        }

        if (numTimes < times.size) {
            for (t in times) {
                if (t.jogadores!!.size < jogadoresPorTime) {
                    val remove = times[times.size - 1].jogadores!!.get(0)
                    t.jogadores!!.add(remove)

                    if (times[times.size - 1].jogadores!!.size > 0) {
                        times[times.size - 1].jogadores!!.remove(remove)
                    }
                }
            }
        }

//        println("Escalacao:");
//        times.forEach { t ->
//            println("Jogadores do Time: "+ t.nome)
//            t.jogadores!!.forEach { j ->
//                System.out.println("Nome: " + j.nome + " - Lvl: " + j.level)
//            }
//            println("")
//        }

        return times
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

    class CadastroJogadorAdapter() : BaseAdapter() {

        var list : List<Jogador> = ArrayList()
        lateinit var context : Context

        constructor(context: Context, jogadores: List<Jogador>): this() {

            this.list = jogadores

            this.context = context

        }

        override fun getView(posicao: Int, view: View?, p2: ViewGroup?): View {
            var convertView : View? = view

            if(convertView == null) {
                convertView = View.inflate(context, R.layout.formar_times, null)
            }

            var jogador = this.getItem(posicao);

            var txtNomeJogador = convertView!!.findViewById(R.id.ckAtleta) as CheckBox
            var txtPosicaoJogador = convertView.findViewById(R.id.txtPosicaoSelecionar) as TextView
            var rtNivel = convertView.findViewById(R.id.rbNivelSelecionar) as RatingBar

            txtNomeJogador.text = list?.get(posicao)?.nome
            txtNomeJogador.isChecked = list?.get(posicao)?.isSelecionado
            txtPosicaoJogador.text = list?.get(posicao)?.posicao
            rtNivel.rating = list?.get(posicao)?.level.toFloat()

            txtNomeJogador.setOnClickListener {
                list?.get(posicao)!!.isSelecionado = txtNomeJogador.isChecked
            }

            return convertView
        }

        override fun getItem(p0: Int): Jogador {
            return list.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return list.size
        }

        internal var sp: Spinner? = null
        private var arrayAdapter: ArrayAdapter<String>? = null
        internal var posicoes = JogadorDAO.listaPosicoes

        constructor(parcel: Parcel) : this() {
            posicoes = parcel.createStringArray()
        }

    }
}
