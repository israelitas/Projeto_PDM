package com.example.projeto_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Retirar extends AppCompatActivity {
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retirar);
        repository = new Repository(this);
    }

    public void onClickRetirar(View view) {
        EditText editText = findViewById(R.id.idEditValorSacar);
        Double saque = Double.parseDouble(editText.getText().toString());
        if(repository.getSaldo() < saque){
            Toast.makeText(this, "Saldo insuficiente", Toast.LENGTH_SHORT).show();
        }else{
            Double valor = repository.getSaldo() - saque;
            repository.movimentacao(valor);
            repository.log("Saque de "+saque+" saldo atualizado "+valor);
            setResult(1);
            finish();
        }
    }
}