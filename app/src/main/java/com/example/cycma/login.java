package com.example.cycma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    Button singupbutton, loginbtn;

    TextInputLayout username_var, password_var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);

        singupbutton = findViewById(R.id.singup);
        loginbtn = findViewById(R.id.loginbutton);
        username_var = findViewById(R.id.user_text_field_design);
        password_var = findViewById(R.id.password_input_field);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_ = username_var.getEditText().getText().toString();
                String password_ = password_var.getEditText().getText().toString();

                if(!username_.isEmpty()){
                    username_var.setError(null);
                    username_var.setErrorEnabled(false);
                    if(!password_.isEmpty()){
                        password_var.setError(null);
                        password_var.setErrorEnabled(false);

                        final String username_data = username_var.getEditText().getText().toString();
                        final String password_data = password_var.getEditText().getText().toString();

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("datauser");

                        Query check_username = databaseReference.orderByChild("lastname").equalTo(username_data);
                        check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    username_var.setError(null);
                                    username_var.setErrorEnabled(false);
                                    String passwordcheck = snapshot.child(username_data).child("password").getValue(String.class);
                                    if(passwordcheck.equals(password_data)){
                                        password_var.setError(null);
                                        password_var.setErrorEnabled(false);
                                        Toast.makeText(getApplicationContext(), "Bienvenue", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), dashboard.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        password_var.setError("mot de passe incorrest");
                                    }
                                }else{
                                    username_var.setError("le prénom n'existe pas");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }else{
                        password_var.setError("veuillez entrer un mot de passe");
                    }
                }else{
                    username_var.setError("veuillez entrer un nom");
                }

            }
        });

        singupbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), singup.class);
                startActivity(intent);

            }
        });
    }
}