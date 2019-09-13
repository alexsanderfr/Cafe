package com.example.cafe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafe.model.Pack;
import com.example.cafe.model.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * Created by henriquedealmeida on 20/06/17.
 */

public class PackOrderActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_order);

        ArrayList<Pack> packs = new ArrayList<Pack>();
        packs.add(new Pack("Promocional", 35));
        packs.add(new Pack("Padrão", 50));
        packs.add(new Pack("Executivo", 75));
        packs.add(new Pack("Premium", 90));

        PackAdapter packAdapter = new PackAdapter(this, packs);
        ListView orderList = (ListView) findViewById(R.id.order_list_view);
        orderList.setAdapter(packAdapter);

        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView nameTextView = (TextView) view.findViewById(R.id.pack_name);
                final String name = (String) nameTextView.getText();
                final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                AlertDialog.Builder builder = new AlertDialog.Builder(PackOrderActivity.this);

                builder.setPositiveButton(R.string.sign, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        postToDatabase(name, userId);
                    }
                });

                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.setTitle(R.string.question_pack);
                dialog.show();

            }
        });
    }

    public void postToDatabase(String name, String userId) {
        User user = new User();
        user.setId(userId);
        user.addPackToUser(name);
        Toast.makeText(getApplicationContext(), "Você assinou o pacote: " + name,
                Toast.LENGTH_SHORT).show();
    }
}
