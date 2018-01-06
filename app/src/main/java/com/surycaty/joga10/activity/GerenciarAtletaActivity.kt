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
import com.surycaty.joga10.util.Utils
import kotlinx.android.synthetic.main.activity_gerenciar_atleta.*
import java.util.*


class GerenciarAtletaActivity : AppCompatActivity() {

    private var jogadores: List<Jogador> = mutableListOf()
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
            var intent : Intent = Intent(this@GerenciarAtletaActivity, CadastroJogadorActivity::class.java)

            startActivityForResult(intent,CHAMADA)

        }

    }

    private fun atualizarAdapter() {
        var adapter : CadastroJogadorAdapter? = null

        adapter = CadastroJogadorAdapter(this@GerenciarAtletaActivity)

        lvJogadores.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        atualizarAdapter()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == CHAMADA) {
            if (resultCode == Activity.RESULT_OK) {

                var adapter : CadastroJogadorAdapter? = null

                adapter = CadastroJogadorAdapter(this@GerenciarAtletaActivity)

                lvJogadores.adapter = adapter
            }
        }
    }

    class CadastroJogadorAdapter() : BaseAdapter() {

        var list : ArrayList<Jogador>? = null
        lateinit var context : Context

        constructor(context: Context): this() {
            var jogadorDao : JogadorDAO = JogadorDAO(context)
            this.list = jogadorDao.listaJogadores

            this.context = context

        }

        override fun getView(posicao: Int, view: View?, p2: ViewGroup?): View {
            var convertView : View? = view

            if(convertView == null) {
                convertView = View.inflate(context, R.layout.itens_jogadores, null)
            }

            var txtNomeJogador : TextView = convertView?.findViewById(R.id.txtNome) as TextView
            var txtPosicaoJogador : TextView = convertView?.findViewById(R.id.txtPosicao) as TextView
            var imgEdit : ImageView = convertView?.findViewById(R.id.imgEdit)
            var imgDelete : ImageView = convertView?.findViewById(R.id.imgDelete)

            txtNomeJogador.text = list?.get(posicao)?.nome
            txtPosicaoJogador.text = list?.get(posicao)?.posicao


            imgEdit.setOnClickListener {

                var mensagem: Toast? = null
                var hasErro = false
                var textoErro = "ALTERAR"

                var intent : Intent = Intent(context, CadastroJogadorActivity::class.java)

                intent.putExtra("EDITAR", true)
                intent.putExtra("ID", list?.get(posicao)?.id)
                intent.putExtra("NOME", list?.get(posicao)?.nome)
                intent.putExtra("POSICAO", list?.get(posicao)?.posicao)
                intent.putExtra("NIVEL", list?.get(posicao)?.level)

                startActivity(context, intent, null)

                mensagem = Utils.mensagem(context, textoErro)
                mensagem!!.show()

            }

            imgDelete.setOnClickListener {

                val simpleAlert = AlertDialog.Builder(context).create()
                simpleAlert.setTitle("Atenção")
                simpleAlert.setMessage("Deseja Realmente excluir?")

                simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", {
                    dialogInterface, i ->

                    var jogador : Jogador
                    var jogadorDao : JogadorDAO = JogadorDAO(context)
                    jogadorDao.excluir(list?.get(posicao)!!)

                    var mensagem: Toast? = null
                    var hasErro = false
                    var textoErro = "Jogador Excluído com sucesso!"
                    mensagem = Utils.mensagem(context, textoErro)
                    mensagem!!.show()

                    //this.list = jogadorDao.listaJogadores

                    //this.arrayAdapter!!.remove(this.arrayAdapter!!.getItem(posicao))

                    mensagem = Utils.mensagem(context, textoErro)
                    mensagem!!.show()
                })

                simpleAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar", {
                    dialogInterface, i ->

                    var mensagem: Toast? = null
                    var hasErro = false
                    var textoErro = "Cancelou DELETAR"

                    mensagem = Utils.mensagem(context, textoErro)
                    mensagem!!.show()
                })

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