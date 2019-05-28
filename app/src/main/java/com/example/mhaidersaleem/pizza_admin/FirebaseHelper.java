package com.example.mhaidersaleem.pizza_admin;

import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by M.HAiDER Saleem on 09/05/2018.
 */

public class FirebaseHelper {
    public FirebaseHelper()
    {}
    public boolean add_table(Chef_attribute obj) {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Users");


            myRef.child(obj.user_name).setValue(obj);
            return true;
}
    public boolean add_items(Chef_attribute obj) {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Menu");


        myRef.child(obj.itemName).setValue(obj);
        return true;
    }

}
