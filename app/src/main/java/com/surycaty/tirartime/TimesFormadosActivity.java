package com.surycaty.tirartime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.surycaty.tirartime.entidade.Jogador;

import java.util.ArrayList;
import java.util.List;

public class TimesFormadosActivity extends AppCompatActivity {

    private List<Jogador> selecionados = new ArrayList<Jogador>();

    private  String[] times = {"Alfa","Bravo","Charle","Delta"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_formados);

        Intent i = getIntent();
        selecionados = (List<Jogador>) i.getSerializableExtra("listaSelecionados");

        int numTimes = i.getIntExtra("numTimes", 0);

        TextView tv = new TextView(getApplicationContext());
        tv.setTextSize(30f);
        tv.setText("Existem "+selecionados.size() + " jogadores selecionados. \nSerão formados " + numTimes + " times.");

        LinearLayout.LayoutParams checkParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        checkParams.setMargins(40, 150, 10, 10);
        checkParams.gravity = Gravity.CENTER;

        addContentView(tv, checkParams);

    }
}
