package com.surycaty.joga10.activity

import android.content.Intent
import android.database.SQLException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        val btnTirarTime = findViewById<Button>(R.id.btnTirarTime)

        btnTirarTime.setOnClickListener {
            val intent = Intent(this@MainActivity, SelecionarJogadoresActivity::class.java)

            startActivity(intent)
        }

        val btnGerenciarJogadores = findViewById<Button>(R.id.btnJogador)

        btnGerenciarJogadores.setOnClickListener{
            val intent = Intent(this@MainActivity, GerenciarAtletaActivity::class.java)

            startActivity(intent)
        }

    }

}
