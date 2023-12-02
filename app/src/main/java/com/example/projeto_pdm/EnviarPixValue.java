package com.example.projeto_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class EnviarPixValue extends AppCompatActivity {
    int chave;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_pix_value);
        chave = (int) getIntent().getSerializableExtra("chave");
        repository = new Repository(this);
    }

    public void onClickEnviar(View view) {
        EditText editText = findViewById(R.id.idEditEnvValor);
        Double valor = Double.parseDouble(editText.getText().toString());
        if(repository.getSaldo() < valor){
            Toast.makeText(this, "Saldo insuficiente", Toast.LENGTH_SHORT).show();
        }else{
            Double saldo = repository.getSaldo()-valor;
            repository.movimentacao(saldo);
            repository.upDateUsos(chave,repository.getUso(chave)+1);
            repository.log("Pix enviado de "+valor+" saldo atualizado de "+ saldo);
            setResult(1);
            finish();
        }
    }
}