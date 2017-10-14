package com.surycaty.tirartime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CadastroJogadorActivity extends AppCompatActivity {

    Spinner sp = null;
    ArrayAdapter<String> arrayAdapter;
    String[] posicoes = {"ATA", "GOL", "ZAG" , "LD", "LE", "VOL", "MD", "ME", "SA", "PD", "PE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_jogador);

        arrayAdapter = new ArrayAdapter<String> (this, R.layout.support_simple_spinner_dropdown_item, posicoes);

        sp = (Spinner) findViewById(R.id.spinner);

        sp.setAdapter(arrayAdapter);
    }
}
