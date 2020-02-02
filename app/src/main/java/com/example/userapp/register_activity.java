package com.example.userapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.userapp.DAL.DB;
import com.example.userapp.Entities.Member;
import com.example.userapp.Entities.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Interfaces.NotifyDataChange;

public class register_activity extends AppCompatActivity {

    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);
      /*  Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        btn = findViewById(R.id.sendbtn);

        btn.setOnClickListener(v -> DB.getInstance().addLogin(getName(), getEmail(), getPassword(), new NotifyDataChange<User>() {
            @Override
            public void OnDataChanged(User user) {
                DB.getInstance().addMembers(new Member(getFirstName(), getLastName(), getPhone(), getAddress()), new NotifyDataChange<Member>() {
                    @Override
                    public void OnDataChanged(Member obj) {
                        Toast.makeText(register_activity.this, "the form has been submitted!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(register_activity.this, "Error on submitting: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                Toast.makeText(register_activity.this, "Login created for "+user+"!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(register_activity.this, "Error on submitting: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }));

    }

    public String getEmail() {
        return String.valueOf(((EditText) (findViewById(R.id.emailtext))).getText());
    }

    public String getPassword() {
        return String.valueOf(((EditText) (findViewById(R.id.passtext))).getText());
    }

    public String getAddress() {
        return String.valueOf(((EditText) (findViewById(R.id.address_text))).getText());
    }

    public String getPhone() {
        return String.valueOf(((EditText) (findViewById(R.id.phone_text))).getText());

    }

    public String getId() {
        return String.valueOf(((EditText) (findViewById(R.id.idtext))).getText());
    }

    public String getName() {
        return String.valueOf(((EditText) (findViewById(R.id.name_text))).getText());
    }

    public String getFirstName() {
        String name = getName();
        if (name == null || name.isEmpty())
            return "";

        String[] names = name.split(" ");
        if (names.length <= 1)
            return name;

        StringBuilder firstname = new StringBuilder();
        for (int i = 0; i < names.length - 1; i++)
            firstname.append(names[i]).append(" ");

        return firstname.toString();
    }

    public String getLastName() {
        String name = getName();
        if (name == null || name.isEmpty())
            return "";

        String[] names = name.split(" ");
        if (names.length <= 1)
            return "";

        return names[names.length - 1];
    }
}
