package com.example.cafe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String id = getIntent().getStringExtra("User");


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(id);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot user) {
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("id", String.valueOf(user.getValue()));
                for (DataSnapshot attribute : user.getChildren()) {
                    switch (attribute.getKey()) {
                        case "email": {
                            editor.putString("email", String.valueOf(attribute.getValue()));
                            break;
                        }
                        case "name": {
                            editor.putString("name", String.valueOf(attribute.getValue()));
                            break;
                        }
                        case "type": {
                            type = (String) attribute.getValue();
                            editor.putString("type", String.valueOf(attribute.getValue()));
                            onTypeChange(type);
                            break;
                        }
                    }
                }
                editor.apply();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

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

    private void onTypeChange(String type) {
        Toast.makeText(getApplicationContext(), "Authenticated as " + type,
                Toast.LENGTH_SHORT).show();
        switch (type) {
            case "user": {
                startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                finish();
                break;
            }
            case "admin": {
                startActivity(new Intent(getApplicationContext(), ManageOrdersActivity.class));
                finish();
                break;
            }
        }
    }
}