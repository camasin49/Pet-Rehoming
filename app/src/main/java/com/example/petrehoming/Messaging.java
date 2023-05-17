package com.example.petrehoming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Messaging extends AppCompatActivity {

    String email, contact;
    String newEmail;

    TextView emailTxt, contactTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        getSupportActionBar().hide();

        Intent intent = getIntent();
         email = intent.getStringExtra("email");
         contact = intent.getStringExtra("contactNumber");

        emailTxt = findViewById(R.id.email_message);
        contactTxt = findViewById(R.id.contact_message);

        emailTxt.setText(email);
        if (contact == null){
            contactTxt.setText("No Contact Number");
        } else {
            contactTxt.setText(contact);
        }


        emailTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.setData(Uri.parse("mailto:" + email));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
                startActivity(Intent.createChooser(intent, "Send email"));
            }
        });

        contactTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("No Contact Number".equals(contactTxt)) {
                    Toast.makeText(getApplicationContext(), "No Contact Found", Toast.LENGTH_SHORT).show();
                } else {
                    Uri uri = Uri.parse("smsto:" + contactTxt);
                    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                    startActivity(intent);
                }
            }
        });

    }
}