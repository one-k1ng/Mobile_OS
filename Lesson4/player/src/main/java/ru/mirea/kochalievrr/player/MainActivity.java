package ru.mirea.kochalievrr.player;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ru.mirea.kochalievrr.player.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MediaPlayer music;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        music = MediaPlayer.create(this, R.raw.song);}


    public void musicplay(View v)
    {
        binding.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.class.getSimpleName(),"musicplay");
                music.start();
            }
        });
        //music.start();
    }

    public void musicstop(View v)
    {
        binding.stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.class.getSimpleName(),"musicstop");
                //music.stop();
            }
        });
        music.stop();
        music = MediaPlayer.create(this, R.raw.song);
    }

    public void musicpause(View v)
    {
        binding.pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.class.getSimpleName(),"musicpause");
                music.pause();
            }
        });
        //music.pause();
    }
}