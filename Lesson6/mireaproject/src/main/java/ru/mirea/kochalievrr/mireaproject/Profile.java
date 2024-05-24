package ru.mirea.kochalievrr.mireaproject;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ru.mirea.kochalievrr.mireaproject.databinding.ActivityProfileBinding;

public class Profile extends AppCompatActivity {

    private static final String PREFS_FILE = "File1";
    private static final String PREF_NAME = "Name";
    private static final String PREF_LAST_NAME = "Last_Name";
    private static final String PREF_PHONE = "Phone";
    EditText nameBox1,nameBox2,nameBox3;
    SharedPreferences settings1,settings2,settings3;
    SharedPreferences.Editor prefEditor1,prefEditor2,prefEditor3;
    private ActivityProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        nameBox1 = findViewById(R.id.nameBox);
        settings1 = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        String name1 = settings1.getString(PREF_NAME,"");
        nameBox1.setText(name1);

        nameBox2 = findViewById(R.id.nameBox2);
        settings2 = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        String name2 = settings2.getString(PREF_LAST_NAME,"");
        nameBox2.setText(name2);

        nameBox3 = findViewById(R.id.nameBox3);
        settings3 = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        String name3 = settings3.getString(PREF_PHONE,"");
        nameBox3.setText(name3);

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();

        String name1 = nameBox1.getText().toString();
        String name2 = nameBox2.getText().toString();
        String name3 = nameBox3.getText().toString();

        // сохраняем
        prefEditor1 = settings1.edit();
        prefEditor1.putString(PREF_NAME, name1);
        prefEditor1.apply();

        prefEditor2 = settings2.edit();
        prefEditor2.putString(PREF_LAST_NAME, name2);
        prefEditor2.apply();

        prefEditor3 = settings3.edit();
        prefEditor3.putString(PREF_PHONE, name3);
        prefEditor3.apply();
    }

    public void saveName(View view) {
        // получаем
        EditText nameBox1 = findViewById(R.id.nameBox);
        String name1 = nameBox1.getText().toString();

        EditText nameBox2 = findViewById(R.id.nameBox2);
        String name2 = nameBox2.getText().toString();

        EditText nameBox3 = findViewById(R.id.nameBox3);
        String name3 = nameBox3.getText().toString();

        // сохраняем
        SharedPreferences.Editor prefEditor = settings1.edit();
        prefEditor.putString(PREF_NAME, name1);
        prefEditor.apply();

        SharedPreferences.Editor prefEditor2 = settings2.edit();
        prefEditor2.putString(PREF_LAST_NAME, name2);
        prefEditor2.apply();

        SharedPreferences.Editor prefEditor3 = settings3.edit();
        prefEditor3.putString(PREF_PHONE, name3);
        prefEditor3.apply();
    }

    public void getName(View view) {
        TextView nameView = findViewById(R.id.nameView);
        String name = settings1.getString(PREF_NAME,"не определено");
        String lastname = settings1.getString(PREF_LAST_NAME,"не определено");
        String phone = settings1.getString(PREF_PHONE,"не определено");
        nameView.setText(name+"\n"+lastname+"\n"+phone);
    }

}