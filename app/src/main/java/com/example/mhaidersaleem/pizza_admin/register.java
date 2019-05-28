package com.example.mhaidersaleem.pizza_admin;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by M.HAiDER Saleem on 09/05/2018.
 */

public class register extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final EditText table = (EditText) findViewById(R.id.etnum);
        final EditText pass = (EditText) findViewById(R.id.etPass);
        Button add= (Button) findViewById(R.id.btnadd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseHelper fr= new FirebaseHelper();
                String number= table.getText().toString();
                String password = pass.getText().toString();
                Chef_attribute obj= new Chef_attribute();
                obj.set_user(number,password);

                if(fr.add_table(obj))
                {
                    Toast.makeText(getApplicationContext(),"Added SuccessFully ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
