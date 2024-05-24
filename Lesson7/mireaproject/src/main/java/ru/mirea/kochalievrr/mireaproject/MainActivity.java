package ru.mirea.kochalievrr.mireaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import ru.mirea.kochalievrr.mireaproject.databinding.ActivityMainBinding;


//После успешной авторизации происходит переход на главный экран.
//Фрагмент отображает информацию из сервиса погоды: https://api.openmeteo.com
//Сетевое взаимодействие: HttpURLConnection

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        binding.Email.setText("hello_world@yandex.ru");
        binding.Password.setText("123456789");
        binding.buttonSignOut.setVisibility(View.VISIBLE);

        binding.buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(binding.Email.getText().toString(), binding.Password.getText().toString());
                Intent intent = new Intent(MainActivity.this, Second.class);
                startActivity(intent);
            }
        });

        binding.buttonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(binding.Email.getText().toString(), binding.Password.getText().toString());
            }
        });

        binding.buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // Пользователь вошел в систему
            user = mAuth.getCurrentUser();
            if (user != null) {
                String userInfo = "Email User: " + user.getEmail() + "\n"
                        + "Email Verification Status: " + user.isEmailVerified() + "\n"
                        + "Firebase UID: " + user.getUid();
                binding.signOut.setText(userInfo);
            }
            binding.buttonSignIn.setVisibility(View.GONE);
            binding.buttonAccount.setVisibility(View.GONE);
            binding.Email.setVisibility(View.GONE);
            binding.Password.setVisibility(View.GONE);
        } else {
            // Пользователь вышел из системы
            binding.signOut.setText("Signed Out");
            binding.buttonSignIn.setVisibility(View.VISIBLE);
            binding.buttonAccount.setVisibility(View.VISIBLE);
            binding.Email.setVisibility(View.VISIBLE);
            binding.Password.setVisibility(View.VISIBLE);
        }
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure",
                                    task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }
    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }
}
