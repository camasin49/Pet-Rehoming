package com.example.petrehoming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    ImageView back;
    private FirebaseAuth auth;
    private  EditText editTextemail, editTextpassword;
    private AppCompatButton btn_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        back = findViewById(R.id.back);
        editTextemail = findViewById(R.id.reg_email);
        editTextpassword = findViewById(R.id.reg_password);
        btn_register = findViewById(R.id.btn_reg);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editTextemail.getText().toString().trim();
                String pass = editTextpassword.getText().toString().trim();

                if (user.isEmpty()) {
                    editTextemail.setError("Email cannot be empty!");
                }

                if (user.isEmpty()) {
                    editTextpassword.setError("Email cannot be empty!");
                }else{

                    auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Register.this, "Registered!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this, Login.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Register.this, "Registered!" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, HomeScreen.class);
                startActivity(intent);
            }
        });
    }
}