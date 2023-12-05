package com.example.projeto_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Depositar extends AppCompatActivity {
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depositar);
        repository = new Repository(this);
    }

    public void onClickDepositar(View view) {
        EditText editText = findViewById(R.id.idEditValorSacar);
        Double deposito = Double.parseDouble(editText.getText().toString());
        if (deposito <=0){
            Toast.makeText(this, "Por favor digite um valor válido", Toast.LENGTH_SHORT).show();
            return;
        }
        Double valor = repository.getSaldo() + deposito;
        repository.movimentacao(valor);
        repository.log("Deposito de "+deposito+" Saldo atualizado "+valor);
        setResult(1);
        finish();
    }
}