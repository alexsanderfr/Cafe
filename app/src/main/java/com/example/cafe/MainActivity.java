package com.example.cafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] optionsStringArray = {"Cadastro", "Login", "Pedidos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mainMenu = (ListView) findViewById(R.id.main_menu);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, optionsStringArray);
        mainMenu.setAdapter(adapter);
        mainMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                        break;
                }
            }
        });
    }
}