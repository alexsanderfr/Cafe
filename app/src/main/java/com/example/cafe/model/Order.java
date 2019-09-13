package com.example.cafe.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Order {
    private String id;
    private String date;
    private String product;
    private String user;
    private String table;

    public Order() {

    }

    public void save(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference order = database.child("Orders").child(getId());
        order.child("date").setValue(getDate());
        order.child("product").setValue(getProduct());
        order.child("user").setValue(getUser());
        order.child("table").setValue(getTable());
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

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
