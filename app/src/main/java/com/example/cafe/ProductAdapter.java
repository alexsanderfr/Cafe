package com.example.cafe;


//Created by henriquedealmeida on 11/05/17.

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Activity context, ArrayList<Product> products){
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Product currentProduct = getItem(position);

        ImageView itemImageView = (ImageView) listItemView.findViewById(R.id.item_image);

        itemImageView.setImageResource(currentProduct.getImageResourceId());

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.item_name);

        nameTextView.setText(currentProduct.getName());

        return listItemView;
    }
}
