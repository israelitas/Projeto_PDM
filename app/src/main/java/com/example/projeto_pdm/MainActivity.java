package com.example.projeto_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repository = new Repository(this);
    }

    public void onClickLogin(View view) {
        EditText rawCpf = findViewById(R.id.idLoginCpf);
        EditText rawPassword = findViewById(R.id.idLoginPassword);
        String cpf = rawCpf.getText().toString();
        String password = rawPassword.getText().toString();

        if(cpf.equals(repository.getCpf()) && password.equals(repository.getSenha())){
            Intent intent = new Intent(this, Dashboard.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Usuario ou senha incorretos", Toast.LENGTH_SHORT).show();
        }
    }
}