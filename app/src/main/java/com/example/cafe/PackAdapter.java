package com.example.cafe;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cafe.model.Pack;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by henriquedealmeida on 20/06/17.
 */

public class PackAdapter extends ArrayAdapter<Pack> {
    public PackAdapter(Activity context, ArrayList<Pack> packs){
        super(context, 0, packs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.pack_list_item, parent, false);
        }

        Pack currentPack = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.pack_name);

        nameTextView.setText(currentPack.getName());

        TextView priceTextView = (TextView) listItemView.findViewById(R.id.pack_price);

        priceTextView.setText(NumberFormat.getCurrencyInstance().format(currentPack.getPrice()));

        return listItemView;
    }
}
