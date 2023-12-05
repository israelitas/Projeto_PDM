package com.example.projeto_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class Dashboard extends AppCompatActivity {
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        repository = new Repository(this);

        TextView name = findViewById(R.id.idTextDash);
        name.setText("Seja bem vindo");
        TextView textValor = findViewById(R.id.idTextValor);
        textValor.setText(""+repository.getSaldo());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }

    public void onClickConta(View view) {
        Intent intent = new Intent(this, Conta.class);
        startActivity(intent);
    }

    public void onClickPix(View view) {
        Intent intent = new Intent(this, Pix.class);
        startActivity(intent);
    }
}