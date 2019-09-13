package com.example.cafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ManagePackActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_manage_pack);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, String> userToPack = new HashMap<>();
                for (DataSnapshot UserSnapshot : dataSnapshot.getChildren()) {
                    String name = null;
                    String pack = null;
                    for (DataSnapshot attributeSnapshot : UserSnapshot.getChildren()) {
                        switch (attributeSnapshot.getKey()) {
                            case "name": {
                                name = String.valueOf(attributeSnapshot.getValue());
                                break;
                            }
                            case "pack": {
                                pack = String.valueOf(attributeSnapshot.getValue());
                                break;
                            }
                        }
                    }
                    if (pack != null) {
                        userToPack.put(name, pack);
                    }
                }
                onPacksChanged(userToPack);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void onPacksChanged(HashMap<String, String> userToPack) {
        ArrayList<String> UserStringArray = new ArrayList<>();
        for (String name : userToPack.keySet()) {
            UserStringArray.add(name + " assinou o pacote: " + userToPack.get(name));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, UserStringArray);
        ListView managePacksListView = (ListView) findViewById(R.id.manage_packs_list_view);
        managePacksListView.setAdapter(adapter);
    }
}
