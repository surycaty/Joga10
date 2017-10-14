package com.surycaty.tirartime;

import android.content.Intent;
import android.database.SQLException;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.surycaty.tirartime.db.Database;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            db = new Database(this);
            db.open();

            Toast.makeText(getApplicationContext(), "CONEXAO OK", Toast.LENGTH_LONG);
            Log.d("CONEXAO", "OK");
        } catch (SQLException ex) {
            Toast.makeText(getApplicationContext(), "ERRO NA CONEXAO: " + ex.getMessage(), Toast.LENGTH_LONG);
            Log.d("CONEXAO", "NOK");
        }

        Button btnTirarTime = (Button)findViewById(R.id.btnTirarTime);

        btnTirarTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, SelecionarJogadoresActivity.class);

                startActivity(it);
            }
        });

        Button btnAddJogador = (Button)findViewById(R.id.btnCadJogador);

        btnAddJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, CadastroJogadorActivity.class);

                startActivity(it);
            }
        });


    }

}
