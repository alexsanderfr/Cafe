package com.example.cafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cafe.model.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageOrdersActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_orders);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Orders");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Order> orders = new ArrayList<>();
                for (DataSnapshot orderSnapshot : dataSnapshot.getChildren()) {
                    Order order = new Order();
                    for (DataSnapshot attributeSnapshot : orderSnapshot.getChildren()) {
                        switch (attributeSnapshot.getKey()) {
                            case "date": {
                                order.setDate(String.valueOf(attributeSnapshot.getValue()));
                                break;
                            }
                            case "product": {
                                order.setProduct(String.valueOf(attributeSnapshot.getValue()));
                                break;
                            }
                            case "user": {
                                order.setUser(String.valueOf(attributeSnapshot.getValue()));
                                break;
                            }
                            case "table": {
                                order.setTable(String.valueOf(attributeSnapshot.getValue()));
                            }
                        }
                    }

                    orders.add(order);
                }
                onOrdersChanged(orders);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void onOrdersChanged(ArrayList<Order> orders) {
        ArrayList<String> orderStringArray = new ArrayList<>();
        for (Order order : orders) {
            orderStringArray.add(order.getProduct() + " pedido por " + order.getUser()
                    + " na mesa " + order.getTable());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, orderStringArray);
        ListView manageOrdersListView = (ListView) findViewById(R.id.manage_orders_list_view);
        manageOrdersListView.setAdapter(adapter);
    }
}
