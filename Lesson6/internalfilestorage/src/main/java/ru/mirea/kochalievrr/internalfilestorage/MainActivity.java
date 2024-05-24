package ru.mirea.kochalievrr.internalfilestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private String fileName = "mirea.txt";
    private EditText tv;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.editTextText);

        tv.setText("6 августа - В этот день в 1915 году защитники крепости Осовец, против которых немцы применили отравляющие газы, смогли подняться в контратаку. Враг был настолько изумлен стойкостью и мужеством русских солдат, что оставил поле боя. Этот героический эпизод останется в истории как «атака мертвецов».\n");


        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tv.post(new Runnable() {
                    public void run() {
                        tv.setText(getTextFromFile());
                    }
                });
            }
        }).start();
    }

    // открытие файла
    public String getTextFromFile() {
        FileInputStream fin = null;
        try {
            fin = openFileInput(fileName);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            Log.d(LOG_TAG, text);
            return text;
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }

    public void onbtnclick(View view) {
        FileOutputStream outputStream;
        String string = tv.getText().toString();
        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }}
}

//  /data/data/ru.mirea.kochalievrr.internalfilestorage/files/mirea.txt