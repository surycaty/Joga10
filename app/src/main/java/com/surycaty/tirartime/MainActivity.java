package com.surycaty.tirartime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Intent it = new Intent(MainActivity.this, SelecionarJogadoresActivity.class);

                startActivity(it);
            }
        });


    }

}
