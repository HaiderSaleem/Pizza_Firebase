package com.example.mhaidersaleem.pizza_admin;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by M.HAiDER Saleem on 09/05/2018.
 */

public class active_product extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("ActiveOrder");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String items;
                collect((Map<String,Object>) dataSnapshot.getValue());



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),("The read failed: " + databaseError.getCode()),Toast.LENGTH_SHORT).show();
            }
        });


}
public void collect(Map<String,Object> obj) {
    final TextView name = (TextView) findViewById(R.id.firstLine);
    final TextView aname = (TextView) findViewById(R.id.secondLine);
    final TextView bname = (TextView) findViewById(R.id.thirdLine);

    LinearLayout layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL);
    layout.setBackgroundResource(R.color.bg_register);


    ScrollView sc = new ScrollView(this);
    sc.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    sc.setFillViewport(true);

    LinearLayout layout1 = new LinearLayout(this);
    layout1.setOrientation(LinearLayout.VERTICAL);
    sc.addView(layout1);
    int i = 1;
    try{
    for (Map.Entry<String, Object> entry : obj.entrySet()) {

        LinearLayout row = new LinearLayout(this);
        row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        Map singleUser = (Map) entry.getValue();
        long check= (long) singleUser.get("isActive");
        if(check==1) {
            TextView txt = new TextView(this);
            txt.setLayoutParams(new LinearLayout.LayoutParams(750, 400));
            txt.setText((String) singleUser.get("orderDetail") + "\n");
            final String key = (String) singleUser.get("key");

            txt.setTextSize(32);


            txt.setId(i);
            i++;

            row.addView(txt);

            layout1.addView(row);
            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("ActiveOrder").child(key).child("isActive");
                    ref.setValue(0);



                }

            });
        }
    }
    layout.addView(sc);
    setContentView(layout);
}
catch(Exception e)
    {
        Toast.makeText(getApplicationContext(),"Active Product: "+e.getMessage(),Toast.LENGTH_LONG).show();
    }



}

}
