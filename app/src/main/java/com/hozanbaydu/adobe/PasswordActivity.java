package com.hozanbaydu.adobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import com.hozanbaydu.adobe.databinding.ActivityMainBinding;
import com.hozanbaydu.adobe.databinding.ActivityPasswordBinding;


public class PasswordActivity extends AppCompatActivity {

    private ActivityPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        getSupportActionBar().hide();
    }


    public void addButton (View view){

        Intent intent = new Intent(PasswordActivity.this,UploadActivity.class);
        startActivity(intent);
        finish();
    }


}