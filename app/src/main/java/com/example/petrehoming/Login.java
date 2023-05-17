package com.example.petrehoming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Login extends AppCompatActivity {
    ImageView back;

    private FirebaseAuth auth;
    private EditText editTextemail, editTextpassword;
    AppCompatButton btn_login;
    SharedPreferences sp;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        back = findViewById(R.id.back);
        editTextemail = findViewById(R.id.login_email);
        editTextpassword = findViewById(R.id.login_password);
        btn_login = findViewById(R.id.btn_login);

        sp = getSharedPreferences("userEmail", Context.MODE_PRIVATE);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, HomeScreen.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = editTextemail.getText().toString().trim();
                String pass = editTextpassword.getText().toString().trim();

                if (user.isEmpty()) {
                    editTextemail.setError("Email cannot be empty!");
                }

                if (pass.isEmpty()) {
                    editTextpassword.setError("Enter password");
                }

                auth.signInWithEmailAndPassword(user, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    SharedPreferences.Editor editor = sp.edit();

                                    editor.putString("email", user);
                                    editor.putString("pass",pass);
                                    editor.commit();
                                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}