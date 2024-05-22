package ru.mirea.kochalievrr.cryptoloader;
import android.content.Context;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyLoader extends AsyncTaskLoader<String> {

    String text;
    String key;

    public MyLoader(@NonNull Context context, String phrase, String key) {
        super(context);
        this.text = phrase;
        this.key = key;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Nullable
    @Override
    public String loadInBackground() {
        return decryptMsg(text, key);
    }

    private String decryptMsg(String encryptedPhrase, String base64Key) {
        try {
            byte[] decodedKey = Base64.decode(key, Base64.DEFAULT);
            Cipher cipher = Cipher.getInstance("AES");

            SecretKey secretKey = new SecretKeySpec(decodedKey, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            return new String(cipher.doFinal(Base64.decode(encryptedPhrase, Base64.DEFAULT)),StandardCharsets.UTF_8);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                 | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}