package com.example.cafe.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Order {
    private String id;
    private String date;
    private String product;
    private String user;

    public Order() {

    }

    public void save(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference user = database.child("Orders").child(getId());
        user.child("date").setValue(getDate());
        user.child("product").setValue(getProduct());
        user.child("user").setValue(getUser());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
