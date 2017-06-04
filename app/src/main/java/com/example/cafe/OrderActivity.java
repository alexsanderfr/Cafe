package com.example.cafe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafe.model.Order;
import com.example.cafe.model.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Calendar;

public class OrderActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_exit:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    Calendar calendar;

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

        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_table, null);
                builder.setView(dialogView);

                builder.setPositiveButton(R.string.order, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText tableEditText = (EditText) dialogView.findViewById(R.id.table_edit_text);
                        String table = tableEditText.getText().toString();
                        calendar = Calendar.getInstance();
                        String date = calendar.getTime().toString();
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        TextView productTextView = (TextView) view.findViewById(R.id.item_name);
                        String product = productTextView.getText().toString();
                        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                        String name = sharedPref.getString("name", null);
                        String orderId = null;
                        if (user != null) {
                            orderId = user.getUid() + position;
                        }
                        postToDatabase(name, date, orderId, product, table);
                    }
                });

                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }

    public void postToDatabase(String name, String date, String orderId, String product, String table) {
        Order order = new Order();
        order.setUser(name);
        order.setId(orderId);
        order.setDate(date);
        order.setProduct(product);
        order.setTable(table);
        order.save();
        Toast.makeText(getApplicationContext(), "Você pediu um " + product + " para a mesa " + table,
                Toast.LENGTH_SHORT).show();
    }
}
