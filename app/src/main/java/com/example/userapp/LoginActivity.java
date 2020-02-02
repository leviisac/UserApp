package com.example.userapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.userapp.DAL.DB;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = (Button)findViewById(R.id.registerbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, register_activity.class));

            }
        });

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);




        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String username = usernameEditText.getText().toString();
                final String password=passwordEditText.getText().toString();
                checkDB(username, password);

            }
        });






    }

    private void checkDB(String username, String password) {
               DB.getInstance().login(username, password, (OnCompleteListener<AuthResult>) task -> {
            if (task.isSuccessful()) {

                Toast.makeText(LoginActivity.this, "Authentication succeed.",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("Name",username);
                startActivity(intent);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));

            } else {

                Toast.makeText(LoginActivity.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();

            }

            // ...
        });





    }
}
