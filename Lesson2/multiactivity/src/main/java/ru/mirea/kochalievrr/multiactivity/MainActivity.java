package ru.mirea.kochalievrr.multiactivity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "MainActivity onCreate()");
        setContentView(R.layout.activity_main);
        tvView = (TextView) findViewById(R.id.editTextText);
        tvView.setText("MIREA - КОЧАЛИЕВ РАСИМ РУСТЕМОВИЧ");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "MainActivity onStart()");
    };
    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        Log.i(TAG, "MainActivity onRestoreInstanceState()");
    };
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "MainActivity onRestart()");
    };
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "MainActivity onResume()");
    };
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "MainActivity onPause()");
    };
    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.i(TAG, "MainActivity onSaveInstanceState()");
    };
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "MainActivity onStop()");
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "MainActivity onDestroy()");
    };

    public void onClickNewActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("key", tvView.getText().toString());
        startActivity(intent);
    }

}