package com.surycaty.joga10.activity

import android.content.Intent
import android.database.SQLException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

import com.surycaty.joga10.R
import com.surycaty.joga10.db.Database

class MainActivity : AppCompatActivity() {

    private var db: Database? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            db = Database(this)
            db!!.open()

        } catch (ex: SQLException) {
            Log.d("CONEXAO", "NOK")
        }

        val btnTirarTime = findViewById<View>(R.id.btnTirarTime) as Button

        btnTirarTime.setOnClickListener {
            val it = Intent(this@MainActivity, SelecionarJogadoresActivity::class.java)

            startActivity(it)
        }

        val btnGerenciarJogadores = findViewById<View>(R.id.btnJogador) as Button

        btnGerenciarJogadores.setOnClickListener{
            val it = Intent(this@MainActivity, GerenciarAtletaActivity::class.java)

            startActivity(it)
        }

    }

}
