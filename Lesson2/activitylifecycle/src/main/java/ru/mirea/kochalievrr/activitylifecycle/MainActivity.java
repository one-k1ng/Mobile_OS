package ru.mirea.kochalievrr.activitylifecycle;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    };

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        Log.i(TAG, "onRestoreInstanceState()");
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart()");
    };

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
    };

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
    };

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.i(TAG, "onSaveInstanceState()");
    };

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop()");
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    };
    /*
    Вопросы:
        1. Будет ли вызван метод «onCreate» после нажатия на кнопку «Home» и возврата в приложение?
        Ответ: Нет

        2. Изменится ли значение поля «EditText» после нажатия на кнопку «Home» и возврата в приложение?
        Ответ:Нет

        3. Изменится ли значение поля «EditText» после нажатия на кнопку «Back» и возврата в приложение?
        Ответ:Нет
    */
}