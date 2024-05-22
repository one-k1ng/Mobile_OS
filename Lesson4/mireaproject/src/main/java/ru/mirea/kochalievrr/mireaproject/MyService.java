package ru.mirea.kochalievrr.mireaproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.MediaPlayer;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

//переопределение всех 4 методов жизненного цикла
public class MyService extends Service {
    private MediaPlayer mediaPlayer;
    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //начинается воспроизведение
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                stopForeground(true);
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    //производится инициализация медиапроигрывателя с помощью музыкального ресурса
    @Override
    public void onCreate() {
        super.onCreate();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentText("Playing....")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Пыяла\nАигел"))
                .setContentTitle("Русская музыка");
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Kochaliev R. R.", importance);
        channel.setDescription("MIREA Channel");
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.createNotificationChannel(channel);
        startForeground(1, builder.build());
        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        mediaPlayer.setLooping(false);
    }

    //метод «onDestroy» завершает воспроизведение
    @Override
    public void onDestroy() {
        stopForeground(true);
        mediaPlayer.stop();
    }
}