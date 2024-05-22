package ru.mirea.kochalievrr.cryptoloader;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import ru.mirea.kochalievrr.cryptoloader.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String> {
    public final String TAG = this.getClass().getSimpleName();
    ActivityMainBinding binding;
    EditText edittext;
    Button button;
    private static final int LoaderID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext = findViewById(R.id.editTextText);
                String text = edittext.getText().toString();

                byte[] key = generateKey();
                String base64Key = Base64.encodeToString(key, Base64.DEFAULT);

                String encryptMsg = encryptMsg(text, key);

                Bundle bundle = new Bundle();
                bundle.putString("encryptMsg", encryptMsg);
                bundle.putString("key", base64Key);

                LoaderManager.getInstance(MainActivity.this).restartLoader(LoaderID, bundle, MainActivity.this);
            }
        });
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        String encryptMsg = args.getString("encryptMsg");
        String key = args.getString("key");
        return new MyLoader(this, encryptMsg, key);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        if (loader.getId() == LoaderID) {
            Log.d(TAG, "onLoadFinished: " + s);
            Toast.makeText(this, "onLoadFinished: " + s, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.d(TAG, "onLoaderReset");
    }
    
    private byte[] generateKey() {
        byte[] key = new byte[16];
        new SecureRandom().nextBytes(key);
        return key;
    }

    private String encryptMsg(String phrase, byte[] key) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");
            SecretKey secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedBytes = cipher.doFinal(phrase.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
    }
}