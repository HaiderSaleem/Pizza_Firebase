package com.example.mhaidersaleem.pizza_admin;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by M.HAiDER Saleem on 09/05/2018.
 */

public class items extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_items);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final EditText item_name= (EditText) findViewById(R.id.item_name);
        final EditText item_price= (EditText) findViewById(R.id.item_price);
        final EditText item_des= (EditText) findViewById(R.id.item_description);
        Button add= (Button) findViewById(R.id.btnadd_item);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int price= Integer.valueOf(item_price.getText().toString());

                Chef_attribute obj = new Chef_attribute();
                obj.set_item(item_name.getText().toString(),price,item_des.getText().toString());
              FirebaseHelper fr= new FirebaseHelper();
              try {
                  if (fr.add_items(obj)) {
                      Toast.makeText(getApplicationContext(), "Added SuccessFully ", Toast.LENGTH_SHORT).show();
                  }
              }
              catch (Exception e)
              {
                  Toast.makeText(getApplicationContext(), "Exception "+ e.getMessage(), Toast.LENGTH_SHORT).show();
              }
            }
        });

    }

    }
