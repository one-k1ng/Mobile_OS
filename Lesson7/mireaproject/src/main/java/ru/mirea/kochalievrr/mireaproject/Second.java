package ru.mirea.kochalievrr.mireaproject;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.mirea.kochalievrr.mireaproject.databinding.ActivitySecondBinding;

public class Second extends AppCompatActivity {
    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkinfo = null;
                if (connectivityManager != null) {
                    networkinfo = connectivityManager.getActiveNetworkInfo();
                }

                if (networkinfo != null && networkinfo.isConnected()) {
                    new DownloadPageTask().execute("https://ipinfo.io/json");
                } else {
                    Toast.makeText(Second.this, "Нет интернета", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Second.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private class DownloadPageTask extends AsyncTask<String, Void, String> {
        private String ip;
        private String city;
        private String region;
        private String country;
        private String loc;
        private String org;
        private String postal;
        private String timezone;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.ip.setText("Загружаем...");
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            binding.ip.setText(result);
            Log.d(MainActivity.class.getSimpleName(), result);
            try {
                JSONObject responseJson = new JSONObject(result);
                Log.d(MainActivity.class.getSimpleName(), "Response: " + responseJson);

                ip = responseJson.getString("ip");
                city = responseJson.getString("city");

                region = responseJson.getString("region");
                country = responseJson.getString("country");

                loc = responseJson.getString("loc");
                org = responseJson.getString("org");

                postal = responseJson.getString("postal");
                timezone = responseJson.getString("timezone");

                binding.ip.setText("ip: " + ip);
                binding.city.setText("city: " + city);

                binding.region.setText("region: " + region);
                binding.country.setText("country: " + country);

                binding.loc.setText("loc: " + loc);
                binding.org.setText("org: " + org);

                binding.postal.setText("postal: " + postal);
                binding.timezone.setText("timezone: " + timezone);

                String latitude = responseJson.getString("loc").split(",")[0];
                String longitude = responseJson.getString("loc").split(",")[1];
                weather(latitude, longitude);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }

        private String downloadIpInfo(String address) throws IOException {
            InputStream inputStream = null;
            String data = "";
            try {
                URL url = new URL(address);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(100000);
                connection.setConnectTimeout(100000);
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setUseCaches(false);
                connection.setDoInput(true);
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                    inputStream = connection.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int read = 0;
                    while ((read = inputStream.read()) != -1) {
                        bos.write(read);
                    }
                    bos.close();
                    data = bos.toString();
                } else {
                    data = connection.getResponseMessage() + ". Error Code: " + responseCode;
                }
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            return data;
        }

        private void weather(String latitude, String longitude) {
            String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&current_weather=true";
            new Weather().execute(weatherUrl);
        }
    }

    public class Weather extends AsyncTask<String, Void, String> {
        private String time;
        private String interval;
        private String temperature;
        private String windspeed;
        private String winddirection;
        private String is_day;
        private String weathercode;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.time.setText("Загружаем...");
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadWeatherData(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            binding.time.setText(result);
            Log.d(MainActivity.class.getSimpleName(), result);
            try {
                JSONObject weatherJson = new JSONObject(result);
                Log.d(MainActivity.class.getSimpleName(), "weatherJson: " + weatherJson);

                time = weatherJson.getJSONObject("current_weather").getString("time");
                interval = weatherJson.getJSONObject("current_weather").getString("interval");
                temperature = weatherJson.getJSONObject("current_weather").getString("temperature");
                windspeed = weatherJson.getJSONObject("current_weather").getString("windspeed");

                winddirection = weatherJson.getJSONObject("current_weather").getString("winddirection");
                is_day = weatherJson.getJSONObject("current_weather").getString("is_day");
                weathercode = weatherJson.getJSONObject("current_weather").getString("weathercode");


                binding.time.setText("time: " + time);
                binding.interval.setText("interval: " + interval);
                binding.temperature.setText("temperature: " + temperature);
                binding.windspeed.setText("windspeed: " + windspeed);
                binding.winddirection.setText("winddirection: " + winddirection);
                binding.day.setText("is_day: " + is_day);
                binding.weathercode.setText("weathercode: " + weathercode);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }

        private String downloadWeatherData(String address) throws IOException {
            InputStream inputStream = null;
            String data = "";
            try {
                URL url = new URL(address);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(100000);
                connection.setConnectTimeout(100000);
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setUseCaches(false);
                connection.setDoInput(true);
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    inputStream = connection.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int read = 0;
                    while ((read = inputStream.read()) != -1) {
                        bos.write(read);
                    }
                    bos.close();
                    data = bos.toString();
                } else {
                    data = connection.getResponseMessage() + ". Error Code: " + responseCode;
                }
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            return data;
        }
    }
}
