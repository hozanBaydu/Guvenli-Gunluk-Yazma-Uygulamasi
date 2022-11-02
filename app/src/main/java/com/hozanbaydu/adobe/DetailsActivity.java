package com.hozanbaydu.adobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hozanbaydu.adobe.databinding.ActivityDetailsBinding;
import com.hozanbaydu.adobe.databinding.ActivityMainBinding;


public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        Model passwordModel = (Model) intent.getSerializableExtra("password");

        binding.nameDetailsText.setText(passwordModel.name);
        binding.passwordDetailsText.setText(passwordModel.password);


    }
}