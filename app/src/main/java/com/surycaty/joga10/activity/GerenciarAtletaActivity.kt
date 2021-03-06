package com.surycaty.joga10.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.surycaty.joga10.R
import com.surycaty.joga10.dao.JogadorDAO
import com.surycaty.joga10.entidade.Jogador
import com.surycaty.joga10.util.Constantes
import com.surycaty.joga10.util.Utils
import kotlinx.android.synthetic.main.activity_gerenciar_atleta.*
import kotlin.collections.ArrayList


class GerenciarAtletaActivity : AppCompatActivity() {

    private var jogadores = ArrayList<Jogador>()
    internal var sp: Spinner? = null
    private var arrayAdapter: ArrayAdapter<String>? = null
    internal var posicoes = JogadorDAO.listaPosicoes
    private var table: TableLayout? = null
    val CHAMADA = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gerenciar_atleta)

        atualizarAdapter()

        fab.setOnClickListener {
            val intent = Intent(this@GerenciarAtletaActivity, CadastroJogadorActivity::class.java)

            startActivityForResult(intent,CHAMADA)

        }

    }

    private fun atualizarAdapter() {
        lvJogadores.adapter = CadastroJogadorAdapter(this@GerenciarAtletaActivity)
    }

    override fun onResume() {
        super.onResume()

        atualizarAdapter()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CHAMADA) {
            if (resultCode == Activity.RESULT_OK) {
                lvJogadores.adapter = CadastroJogadorAdapter(this@GerenciarAtletaActivity)
            }
        }
    }

    class CadastroJogadorAdapter() : BaseAdapter() {

        var list : ArrayList<Jogador>? = null
        lateinit var context : Context

        constructor(context: Context): this() {

            val jogadorDao = JogadorDAO(context)
            this.list = jogadorDao.listaJogadores

            this.context = context

        }

        override fun getView(posicao: Int, view: View?, p2: ViewGroup?): View {
            var convertView : View? = view

            if(convertView == null) {
                convertView = View.inflate(context, R.layout.itens_jogadores, null)
            }

            val txtNomeJogador = convertView?.findViewById<TextView>(R.id.txtNome)
            val txtPosicaoJogador = convertView?.findViewById<TextView>(R.id.txtPosicao)
            val imgEdit = convertView?.findViewById<ImageView>(R.id.imgEdit)
            val imgDelete = convertView?.findViewById<ImageView>(R.id.imgDelete)
            val rtNivel = convertView!!.findViewById<RatingBar>(R.id.ratingBarGerenciamento)

            val jogador = list?.get(posicao)

            txtNomeJogador!!.text = jogador?.nome
            txtPosicaoJogador!!.text = jogador?.posicao
            rtNivel.rating = jogador?.level!!.toFloat()


            imgEdit!!.setOnClickListener {

                val intent = Intent(context, CadastroJogadorActivity::class.java)

                intent.putExtra("EDITAR", true)
                intent.putExtra("ID", jogador.id)
                intent.putExtra("NOME", jogador.nome)
                intent.putExtra("POSICAO", jogador.posicao)
                intent.putExtra("NIVEL", jogador.level)

                startActivity(context, intent, null)

                Utils.mensagem(context, "ALTERAR").show()

            }

            imgDelete!!.setOnClickListener {

                val simpleAlert = AlertDialog.Builder(context).create()
                simpleAlert.setTitle("Atenção")
                simpleAlert.setMessage("Deseja Realmente excluir?")

                simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, "OK") { dialogInterface, i ->

                    val jogadorDao = JogadorDAO(context)
                    jogadorDao.excluir(jogador)

                    //this.list = jogadorDao.listaJogadores
                    this.list!!.removeAt(posicao)

                    //this.arrayAdapter!!.remove(this.arrayAdapter!!.getItem(posicao))

                    Utils.mensagem(context, Constantes.MSG_JOGADOR_EXCLUIDO_SUCESSO).show()
                }

                simpleAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar") { dialogInterface, i ->
                    Utils.mensagem(context, Constantes.MSG_EXCLUIR_JOGADOR_CANCELADO).show()
                }

                simpleAlert.show()

            }

            return convertView
        }

        override fun getItem(p0: Int): Jogador? {
            return list?.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return list!!.size
        }

        internal var sp: Spinner? = null
        private var arrayAdapter: ArrayAdapter<String>? = null
        internal var posicoes = JogadorDAO.listaPosicoes

        constructor(parcel: Parcel) : this() {
            posicoes = parcel.createStringArray()
        }

    }

}