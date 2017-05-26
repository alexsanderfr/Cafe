package com.example.cafe;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private String type;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public void save(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference user = database.child("Users").child(getId());
        user.child("name").setValue(getName());
        user.child("email").setValue(getEmail());
        user.child("password").setValue(Base64Custom.encodeBase64(getPassword()));
        user.child("type").setValue(getType());

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}