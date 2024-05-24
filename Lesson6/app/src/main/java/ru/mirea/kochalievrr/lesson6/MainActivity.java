package ru.mirea.kochalievrr.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "Account";
    private static final String PREF_NAME1 = "Name1";
    private static final String PREF_NAME2 = "Name2";
    private static final String PREF_NAME3 = "Name3";
    EditText nameBox1,nameBox2,nameBox3;
    SharedPreferences settings1,settings2,settings3;
    SharedPreferences.Editor prefEditor1,prefEditor2,prefEditor3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameBox1 = findViewById(R.id.editTextText);
//        nameBox1.setText("БИСО-02-19");
        settings1 = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        // получаем настройки
        String name1 = settings1.getString(PREF_NAME1,"");
        nameBox1.setText(name1);

        nameBox2 = findViewById(R.id.editTextText2);
//        nameBox2.setText("№ 8");
        settings2 = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        // получаем настройки
        String name2 = settings2.getString(PREF_NAME2,"");
        nameBox2.setText(name2);

        nameBox3 = findViewById(R.id.editTextText3);
//        nameBox3.setText("Бумажный Дом");
        settings3 = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        // получаем настройки
        String name3 = settings3.getString(PREF_NAME3,"");
        nameBox3.setText(name3);
    }

    @Override
    protected void onPause(){
        super.onPause();

        String name1 = nameBox1.getText().toString();
        String name2 = nameBox2.getText().toString();
        String name3 = nameBox3.getText().toString();

        // сохраняем в настройках
        prefEditor1 = settings1.edit();
        prefEditor1.putString(PREF_NAME1, name1);
        prefEditor1.apply();

        prefEditor2 = settings2.edit();
        prefEditor2.putString(PREF_NAME2, name2);
        prefEditor2.apply();

        prefEditor3 = settings3.edit();
        prefEditor3.putString(PREF_NAME3, name3);
        prefEditor3.apply();
    }

    public void saveName(View view) {
        // получаем введенное имя
        EditText nameBox1 = findViewById(R.id.editTextText);
        String name1 = nameBox1.getText().toString();

        EditText nameBox2 = findViewById(R.id.editTextText2);
        String name2 = nameBox2.getText().toString();

        EditText nameBox3 = findViewById(R.id.editTextText3);
        String name3 = nameBox3.getText().toString();

        // сохраняем его в настройках
        SharedPreferences.Editor prefEditor = settings1.edit();
        prefEditor.putString(PREF_NAME1, name1);
        prefEditor.apply();

        SharedPreferences.Editor prefEditor2 = settings2.edit();
        prefEditor2.putString(PREF_NAME2, name2);
        prefEditor2.apply();

        SharedPreferences.Editor prefEditor3 = settings3.edit();
        prefEditor3.putString(PREF_NAME3, name3);
        prefEditor3.apply();
    }

    public void getName(View view) {
        // получаем сохраненное имя
        TextView nameView = findViewById(R.id.editTextText);
        String name = settings1.getString(PREF_NAME1,"не определено");
        nameView.setText(name);
    }
}
// Device File Explorer: /data/data/ru.mirea.kochalievrr.lesson6/shared_prefs/Account.xml