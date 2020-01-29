package com.example.userapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.userapp.R;

public class parcel_details extends AppCompatActivity {

    String phone;
    String address;
    String Name;
    TextView phone_text;
    TextView address_text;
    TextView name_text;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_details);
       /* Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        btn = (Button)findViewById(R.id.button);
        phone_text=(TextView)findViewById(R.id.phone_text);
        address_text=(TextView)findViewById(R.id.address_text);
        name_text=(TextView)findViewById(R.id.name_text);
        Intent intent= getIntent();
        Bundle extras = intent.getExtras();

        if(extras!=null) {
            phone = extras.getString("phone");
            address = extras.getString("address");
            Name = extras.getString("name");
        }
        name_text.setText(" "+Name);
        address_text.setText(" "+address);
        phone_text.setText(" "+phone);

      /*  btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, Add_Parcel.class));
            }
        });*/

    }


}
