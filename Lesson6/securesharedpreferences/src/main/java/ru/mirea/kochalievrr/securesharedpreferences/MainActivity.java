package ru.mirea.kochalievrr.securesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

    private static final String SECURE_PREFS_FILE = "Lesson_6";
    private static final String KEY_POET_NAME = "name";
    private static final String KEY_POET_PHOTO = "photo";
    private SharedPreferences secureSharedPreferences;
    private TextView TextView;
    private ImageView ImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView = findViewById(R.id.textView);
        ImageView = findViewById(R.id.imageView);

        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
        try {
            String mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
            secureSharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs",
                    mainKeyAlias,
                    getBaseContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            secureSharedPreferences.edit().putString("secure", "Николай Васильевич Гоголь").apply();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String result = secureSharedPreferences.getString("secure", "");

        TextView.setText(result);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gogol);
        ImageView.setImageBitmap(bitmap);
    }
}

//  /data/data/ru.mirea.kochalievrr.securesharedpreferences/shared_prefs/secret_shared_prefs.xml