package com.example.cafe.model;

/**
 * Created by henriquedealmeida on 20/06/17.
 */

public class Pack {

    //attributes
    private String mName;
    private int mPrice;

    //constructor
    public Pack(String name, int price){
        mName = name;
        mPrice = price;
    }



    //methods
    public String getName(){
        return mName;
    }

    public int getPrice(){
        return mPrice;
    }
}
