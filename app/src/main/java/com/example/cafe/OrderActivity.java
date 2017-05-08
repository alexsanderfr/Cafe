package com.example.cafe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OrderActivity extends AppCompatActivity {

    String[] ordersStringArray = {"Cappuccino", "Expresso", "Naked", "Latte",
            "Chocolate quente", "Mocca", "Ristretto"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ListView mainMenu = (ListView) findViewById(R.id.order_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, ordersStringArray);
        mainMenu.setAdapter(adapter);
    }
}
