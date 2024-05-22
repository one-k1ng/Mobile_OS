package ru.mirea.kochalievrr.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;
import android.view.View;

import ru.mirea.kochalievrr.thread.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private int counter = 0;
    TextView Text1,Text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonMirea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        int numberThread = counter++;


                        TextView infoTextView = findViewById(R.id.textView);
                        Thread mainThread = Thread.currentThread();
                        infoTextView.setText("Имя текущего потока: " + mainThread.getName());
                        // Меняем имя и выводим в текстовом поле
                        mainThread.setName("МОЙ НОМЕР ГРУППЫ: БИСО-02-19, НОМЕР ПО СПИСКУ: 8, МОЙ ЛЮБИМЫЙ ФИЛЬМ: 'Драйв'");
                        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
                        Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread.getStackTrace()));
                        Log.d(MainActivity.class.getSimpleName(), "Group: " + mainThread.getThreadGroup());


                        Text1 = findViewById(R.id.editTextText);
                        Text2 = findViewById(R.id.editTextText2);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                double count = Integer.valueOf(Text1.getText().toString())/Integer.valueOf(Text2.getText().toString());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        infoTextView.append("\n\nСреднее количество пар в день за месяц: " + count);
                                    }
                                });
                            }
                        }).start();


                        Log.d("ThreadProject", String.format("Запущен поток № %d студентом группы № %s номер по списку № %d ", numberThread, "БИСО-02-19", 8));
                        long endTime = System.currentTimeMillis() + 20 * 1000;
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(), "Endtime: " + endTime);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject", "Выполнен поток № " + numberThread);
                        }
                    }
                }).start();
            }
        });
    }
}