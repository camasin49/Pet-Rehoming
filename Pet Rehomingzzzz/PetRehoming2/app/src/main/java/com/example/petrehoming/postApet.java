package com.example.petrehoming;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class postApet extends AppCompatActivity {

    ImageView backtomenu;
    private ImageButton boy,girl;
    private AppCompatButton postPet;
    private AppCompatButton chooseImage;
    private TextView seeUploads;
    private EditText namePet;
    private ImageView picturePet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_apet);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        backtomenu = findViewById(R.id.backtomenu);

        boy = findViewById(R.id.bBoy);
        girl = findViewById(R.id.bGirl);
        postPet = findViewById(R.id.postApet);

        chooseImage = findViewById(R.id.imagefile);

        seeUploads = findViewById(R.id.uploads);
        namePet = findViewById(R.id.nameofPet);
        picturePet = findViewById(R.id.imagePet);


        backtomenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(postApet.this, MainMenu.class);
                        startActivity(intent);
                    }
                });

        chooseImage.setOnClickListener(view -> mGetContext.launch("image/*"));

        postPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        seeUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    ActivityResultLauncher<String> mGetContext = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null){
                        picturePet.setImageURI(result);
                    }
                }
            });
}