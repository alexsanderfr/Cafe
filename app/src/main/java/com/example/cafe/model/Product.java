package com.example.cafe.model;

 //Created by henriquedealmeida on 11/05/17.

public class Product {

    //attributes
    private String mName;
    private int mImageResourceId;

    //constructor
    public Product(String name, int imageResourceId){
        mName = name;
        mImageResourceId = imageResourceId;
    }

    //methods
    public String getName(){
        return mName;
    }

    public int getImageResourceId(){
        return mImageResourceId;
    }
}
