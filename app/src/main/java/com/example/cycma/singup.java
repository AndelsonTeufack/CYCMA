package com.example.cycma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class singup extends AppCompatActivity {

    TextInputLayout firstname_var, lastname_var, email_var, phonenumber_var, password_var;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.singup);

        firstname_var = findViewById(R.id.firstname_field);
        lastname_var = findViewById(R.id.lastname_field);
        email_var = findViewById(R.id.email_field);
        phonenumber_var = findViewById(R.id.email_field);
        password_var = findViewById(R.id.password_field);

    }

    public void loginbuttonclick(View view) {
        Intent intent = new Intent(getApplicationContext(),login.class);
        startActivity(intent);
        finish();
    }

    public void registerbuttonclick(View view) {
        String firstname_ = firstname_var.getEditText().getText().toString();
        String lastname_ = lastname_var.getEditText().getText().toString();
        String email_ = email_var.getEditText().getText().toString();
        String phonenumber_ = phonenumber_var.getEditText().getText().toString();
        String password_ = password_var.getEditText().getText().toString();

        if(!firstname_.isEmpty()){
            firstname_var.setError(null);
            firstname_var.setErrorEnabled(false);
            if(!lastname_.isEmpty()){
                lastname_var.setError(null);
                lastname_var.setErrorEnabled(false);
                if(!email_.isEmpty()){
                    email_var.setError(null);
                    email_var.setErrorEnabled(false);
                    if(!phonenumber_.isEmpty()){
                        phonenumber_var.setError(null);
                        phonenumber_var.setErrorEnabled(false);
                        if(!password_.isEmpty()){
                            password_var.setError(null);
                            password_var.setErrorEnabled(false);
                            if(email_.matches("^[A-Za-z0-9+_.-]+@(.+)$")){

                             firebaseDatabase = FirebaseDatabase.getInstance();
                             reference = firebaseDatabase.getReference( "datauser");

                                String firstname_s = firstname_var.getEditText().getText().toString();
                                String lastname_s = lastname_var.getEditText().getText().toString();
                                String email_s = email_var.getEditText().getText().toString();
                                String phonenumber_s = phonenumber_var.getEditText().getText().toString();
                                String password_s = password_var.getEditText().getText().toString();

                                storingdata stroringdatass = new storingdata(firstname_s, lastname_s, email_s, phonenumber_s, password_s);
                                reference.child(lastname_s).setValue(stroringdatass);
                                Toast.makeText(getApplicationContext(), "Enregistré", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), dashboard.class);
                                startActivity(intent);
                                finish();


                            }else{
                                email_var.setError("E-mail invalide");
                            }
                        }else{
                            password_var.setError("veuillez remplir ce champs");
                        }

                    }else{
                        phonenumber_var.setError("veuillez entrer un numero");
                    }
                }else{
                    email_var.setError("veuillez remplir ce champs");
                }
            }else{
                lastname_var.setError("veuillez remplir ce champs");
            }
        }else{
            firstname_var.setError("veuillez remplir ce champs");
        }
    }
}