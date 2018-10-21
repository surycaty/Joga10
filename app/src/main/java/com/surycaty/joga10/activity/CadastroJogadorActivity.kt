package com.surycaty.joga10.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.surycaty.joga10.R
import com.surycaty.joga10.dao.JogadorDAO
import com.surycaty.joga10.entidade.Jogador
import com.surycaty.joga10.util.Utils
import kotlinx.android.synthetic.main.activity_cadastro_jogador.*
import android.content.Intent
import com.surycaty.joga10.util.Constantes


class CadastroJogadorActivity : AppCompatActivity() {

    internal var sp: Spinner? = null
    private var arrayAdapter: ArrayAdapter<String>? = null
    internal var posicoes = JogadorDAO.listaPosicoes
    internal var isEditar : Boolean = false
    internal var idJogador : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_jogador)

        arrayAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, posicoes)

        isEditar = intent.getBooleanExtra("EDITAR", false)

        if (isEditar) {
            idJogador = intent.getIntExtra("ID", 0)
            txtNome.setText(intent.getStringExtra("NOME"))

            spinner.adapter = arrayAdapter
            spinner.setSelection(arrayAdapter!!.getPosition(intent.getStringExtra("POSICAO")))

            ratingBar.rating = intent.getIntExtra("NIVEL", 3).toFloat()
        }

        sp = spinner

        sp!!.adapter = arrayAdapter

        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        btnSalvar.setOnClickListener(View.OnClickListener {

            val nomeJogador = findViewById<TextView>(R.id.txtNome)
            val posicao = findViewById<Spinner>(R.id.spinner)
            val estrelas = findViewById<RatingBar>(R.id.ratingBar)

            if(nomeJogador.text == null || "" == nomeJogador.text.toString().trim()) {
                Utils.mensagem(applicationContext, Constantes.MSG_ERRO_CAMPO_NOME_OBRIGATORIO).show()
                return@OnClickListener
            }

            if(posicao.selectedItem == null || "".equals(posicao.selectedItem)) {
                Utils.mensagem(applicationContext, Constantes.MSG_ERRO_CAMPO_POSICAO_OBRIGATORIO).show()
                return@OnClickListener
            }

            val dao = JogadorDAO(applicationContext)

            val jogador = Jogador(0, nomeJogador.text.toString(), posicao.selectedItem.toString(), estrelas.rating.toInt())

            if (isEditar) {
                jogador.id = idJogador
            }

            dao.salvar(jogador)

            Utils.mensagem(applicationContext, Constantes.MSG_JOGADOR_ATUALIZADO_SUCESSO).show()

            val returnIntent = Intent()
            returnIntent.putExtra("RESULTADO", "SUCESSO")
            setResult(RESULT_OK, returnIntent)
            finish()
        })
    }
}
