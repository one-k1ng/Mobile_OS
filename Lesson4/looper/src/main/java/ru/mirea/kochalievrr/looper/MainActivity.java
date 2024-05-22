package ru.mirea.kochalievrr.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import ru.mirea.kochalievrr.looper.databinding.ActivityMainBinding;

/* build.gradle.kts
buildFeatures {
        viewBinding  = true
    }
 */

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.d(MainActivity.class.getSimpleName(), "Task execute. This is result: " + msg.getData().getString("result"));
            }
        };
        MyLooper myLooper = new MyLooper(mainThreadHandler);
        myLooper.start();
        //binding.editTextMirea.setText("Мой номер по списку №24");
        binding.buttonMirea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = Integer.parseInt(binding.editTextMirea.getText().toString());
                String rab = binding.editTextMirea2.getText().toString();
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putInt("age", age);
                bundle.putString("work", rab);
                msg.setData(bundle);
                int v2 = age * 1000;
                myLooper.mHandler.sendMessageDelayed(msg,v2);
            }
        });
    }
}