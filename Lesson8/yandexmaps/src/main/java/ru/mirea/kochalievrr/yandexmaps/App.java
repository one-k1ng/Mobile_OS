package ru.mirea.kochalievrr.yandexmaps;

import com.yandex.mapkit.MapKitFactory;

import android.app.Application;

public class App extends Application {
    private final String MAPKIT_API_KEY = "42cfb8f7-408e-4c55-80f4-cdd9ea1151ec";
    @Override
    public void onCreate() {
        super.onCreate();
        // Set the api key before calling initialize on MapKitFactory.
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
    }
}
