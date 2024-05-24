package ru.mirea.kochalievrr.mireaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;


import ru.mirea.kochalievrr.mireaproject.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.camera.setBackgroundColor(Color.YELLOW);
        binding.audiorecord.setBackgroundColor(Color.YELLOW);
        binding.accelerometer.setBackgroundColor(Color.YELLOW);

        binding.camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Camera.class);
                startActivity(intent);
                }
        });

        binding.audiorecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Audiorecord.class);
                startActivity(intent);
            }
        });

        binding.accelerometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Accelerometer.class);
                startActivity(intent);
            }
        });


    }
}