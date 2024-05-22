package ru.mirea.kochalievrr.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import ru.mirea.kochalievrr.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        text = findViewById(R.id.textView);

        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.textView.setText("runn1");
            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.textView.setText("runn2");
            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.textView.setText("runn3");
            }
        };
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.textView.postDelayed(runn3, 2000);
                    binding.textView.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        text.setText("Последовательность запуска: runn1,runn2,runn3/runOnUiThread,post,postDelayed");
        text.append("runOnUiThread, post отправляют Runnable на немедленную обработку.");
        text.append("postDelayed позволяет указать задержку выполнения Runnable.");
        text.append("runOnUiThread - метод принимает объект Runnable в качестве параметра.");
        text.append("post - метод принимает объект Runnable и задержку в миллисекундах в качестве параметров.");
        text.append("postDelayed - метод принимает объект Runnable в качестве параметра.");
        text.append("android:maxLines='10' - лишние строки будут отрезаны, а android:lines=\"10\" - TextView будет расширяться, чтобы вместить все строки");
    }
}