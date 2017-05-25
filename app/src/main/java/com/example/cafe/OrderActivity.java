package com.example.cafe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("Cappuccino", R.drawable.cappuccino));
        products.add(new Product("Espresso", R.drawable.espresso));
        products.add(new Product("Naked", R.drawable.naked));
        products.add(new Product("Latte", R.drawable.latte));
        products.add(new Product("Chocolate", R.drawable.hot_chocolate));
        products.add(new Product("Mocha", R.drawable.mocha));
        products.add(new Product("Ristretto", R.drawable.ristretto));

        ProductAdapter productAdapter = new ProductAdapter(this, products);
        ListView orderList = (ListView) findViewById(R.id.order_list_view);
        orderList.setAdapter(productAdapter);
    }
}
