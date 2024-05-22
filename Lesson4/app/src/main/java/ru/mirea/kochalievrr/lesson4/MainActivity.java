package ru.mirea.kochalievrr.lesson4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ru.mirea.kochalievrr.lesson4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //Button btn;
    //TextView textView;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //btn = findViewById(R.id.button);
        //textView = findViewById(R.id.textView);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.editTextMirea.setText("Мой номер по списку №24");
        binding.buttonMirea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.class.getSimpleName(), "onClickListener");
            }
        });
    }

}