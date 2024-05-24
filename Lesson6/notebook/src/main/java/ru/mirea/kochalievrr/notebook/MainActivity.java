package ru.mirea.kochalievrr.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static String FILE_NAME = "new_file.txt"; // имя файла по умолчанию
    private EditText mEditText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }


    // сохранение файла
    public void saveFile (View view){
        EditText mEditText = findViewById(R.id.editTextText2);
        FILE_NAME = mEditText.getText().toString();

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, FILE_NAME);


        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file.getAbsoluteFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            EditText textBox = findViewById(R.id.editText);
            String text = textBox.getText().toString();

            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    // открытие файла
    public void openFile(View view){
        EditText mEditText = findViewById(R.id.editTextText2);
        FILE_NAME = mEditText.getText().toString();


        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, FILE_NAME);

        FileInputStream fin = null;
        try {
            fin = new FileInputStream(file.getAbsoluteFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        TextView textView = findViewById(R.id.editText);
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            textView.setText(text);
            Toast.makeText(this, "Файл открыт", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}