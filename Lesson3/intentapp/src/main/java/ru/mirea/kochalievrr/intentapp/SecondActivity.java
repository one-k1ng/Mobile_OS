package ru.mirea.kochalievrr.intentapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {
    TextView tvView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "SecondActivity onCreate()");

        setContentView(R.layout.activity_second);
        String text = (String) getIntent().getSerializableExtra("sysTime");
        tvView = (TextView) findViewById(R.id.textView);
        tvView.setText(text);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "SecondActivity onStart()");
    };
    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        Log.i(TAG, "SecondActivity onRestoreInstanceState()");
    };
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "SecondActivity onRestart()");
    };
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "SecondActivity onResume()");
    };
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "SecondActivity onPause()");
    };
    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.i(TAG, "SecondActivity onSaveInstanceState()");
    };
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "SecondActivity onStop()");
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "SecondActivity onDestroy()");
    };

}