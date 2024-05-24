package ru.mirea.kochalievrr.mireaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import ru.mirea.kochalievrr.mireaproject.databinding.ActivityWorkingWithFilesBinding;

// Криптография
// Симметричное шифрование AES

public class WorkingWithFiles extends AppCompatActivity {

    private ActivityWorkingWithFilesBinding binding;
    private String testText;
    private TextView originalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityWorkingWithFilesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Original text
        testText = "Знаешь, что делает тебя слабым? \nУ тебя никогда не было нужды быть сильным. \nТебе никогда не приходилось драться за свою жизнь.";
        originalTextView = (TextView) findViewById(R.id.textViewOriginal);
        originalTextView.setText("[ORIGINAL]:\n" + testText + "\n");


        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkingWithFiles.this, MainActivity.class);
                startActivity(intent);
            }
        });

        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragment dialog = new MyDialogFragment();
                dialog.show(getSupportFragmentManager(), "custom");
                crypto();
            }
        });
    }


    protected void crypto() {

        // Set up secret key spec for 128-bit AES encryption and decryption
        SecretKeySpec sks = null;
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("any data used as random seed".getBytes());
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128, sr);
            sks = new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
        } catch (Exception e) {
            Log.e("Crypto", "AES secret key spec error");
        }

        // Encode the original data with AES
        byte[] encodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, sks);
            encodedBytes = c.doFinal(testText.getBytes());
        } catch (Exception e) {
            Log.e("Crypto", "AES encryption error");
        }

        TextView encodedTextView = (TextView)findViewById(R.id.textViewEncoded);
        encodedTextView.setText("[ENCODED]:\n" + Base64.encodeToString(encodedBytes, Base64.DEFAULT) + "\n");

        // Decode the encoded data with AES
        byte[] decodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, sks);
            decodedBytes = c.doFinal(encodedBytes);
        } catch (Exception e) {
            Log.e("Crypto", "AES decryption error");
        }

        TextView decodedTextView = (TextView)findViewById(R.id.textViewDecoded);
        decodedTextView.setText("[DECODED]:\n" + new String(decodedBytes) + "\n");
    }


}