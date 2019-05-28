package com.example.mhaidersaleem.pizza_admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

/**
 * Created by M.HAiDER Saleem on 10/05/2018.
 */

public class login extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username= (EditText) findViewById(R.id.email);
        final EditText password= (EditText) findViewById(R.id.password);
        Button btn = (Button) findViewById(R.id.btnLogin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String number= username.getText().toString();
                final String pass = password.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("Users");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String items;
                        collect((Map<String,Object>) dataSnapshot.getValue(),number,pass);



                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),("The read failed: " + databaseError.getCode()),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
    public void collect(Map<String,Object> obj,String username, String password) {
        try {
            String u=null;
            String p=null;
            int check=0;
            for (Map.Entry<String, Object> entry : obj.entrySet()) {

                Map singleUser = (Map) entry.getValue();
                u= (String) singleUser.get("user_name");
                p= (String) singleUser.get("user_password");
                if(username.equals(u) && password.equals(p))
                {
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    check=1;
                }


            }
            if(check!=1)
                Toast.makeText(getApplicationContext(),"User/Password Invalid "+ username + password+" "+u + p,Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"View Cv "+e.getMessage(),Toast.LENGTH_LONG).show();


        }
    }

}
