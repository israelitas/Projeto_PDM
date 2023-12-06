package com.example.projeto_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Favoritos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        Repository repository = new Repository(this);

        ListView listView = findViewById(R.id.idListViewFav);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, repository.getChaves());
        listView.setAdapter(adapter);
    }
}