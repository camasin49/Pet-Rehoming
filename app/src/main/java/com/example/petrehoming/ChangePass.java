package com.example.petrehoming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePass extends AppCompatActivity {

    String newPass, oldPass;
    String currentPassword;
    ImageView back;
    String email;
    TextView emailCurrent;
    String newEmail;
    Button change;
    EditText oldP, newP;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        back = findViewById(R.id.back);
        emailCurrent = findViewById(R.id.userEmail);

        //EDITTEXT
        oldP = findViewById(R.id.oldpass);
        newP = findViewById(R.id.newpassword);
        //BUTTON
        change = findViewById(R.id.btn_change);

        //FIREBASE
        user = FirebaseAuth.getInstance().getCurrentUser();



        //EMAIL TEXTVIEW
        SharedPreferences sp = getApplicationContext().getSharedPreferences("userEmail", Context.MODE_PRIVATE);
        email = sp.getString("email", "");
        currentPassword = sp.getString("pass","");

        //WORD REMOVER
        String wordToRemove = "@gmail.com";
        emailCurrent.setText("User, "+newEmail);
        newEmail = email.replace(wordToRemove, "");

        // Create a credential using the user's email address and the current password
        AuthCredential credential = EmailAuthProvider.getCredential(email, currentPassword);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPass = newP.getText().toString();
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    user.updatePassword(newPass)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(ChangePass.this, "Successfully reset", Toast.LENGTH_SHORT).show();
                                                    } else if (newPass.isEmpty()) {
                                                        Toast.makeText(ChangePass.this, "New Password Can't be Empty", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        Toast.makeText(ChangePass.this, "Failed to update password", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                } else {
                                    Toast.makeText(ChangePass.this, "Failed to reauthenticate user", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePass.this, MainMenu.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        emailCurrent.setText("User, "+newEmail);
    }
}