package com.example.projeto_pdm;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class EnviarPix extends AppCompatActivity {
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_pix);
        repository = new Repository(this);
    }

    public void onClickEnviarPix(View view) {
        EditText rawChave = findViewById(R.id.idEditEnvChav);
        String chaveStr = rawChave.getText().toString();
        boolean existe = false;

        for (String chave : repository.getChaves()) {
            if(chave.equals(chaveStr)){
                existe = true;
                Intent intent = new Intent(this, EnviarPixValue.class);
                intent.putExtra("chave", chave);
                startActivityForResult(intent, 1);
                break; // Interrompe o loop, pois já encontrou a chave
            }
        }

        if(!existe){
            Toast.makeText(this, "A chave pix deve ser cadastrada primeiro", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){
            finish();
        }
    }
}