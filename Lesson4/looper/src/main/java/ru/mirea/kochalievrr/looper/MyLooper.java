package ru.mirea.kochalievrr.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MyLooper extends Thread{
    public Handler mHandler;
    private Handler mainHandler;
    public MyLooper(Handler mainThreadHandler) {
        mainHandler =mainThreadHandler;
    }
    public void run() {
        Log.d("MyLooper", "run");
        Looper.prepare();
        mHandler = new Handler(Looper.myLooper()) {
            public void handleMessage(Message msg) {
                //String data = msg.getData().getString("KEY");
                //Log.d("MyLooper get message: ", data);
                //int count = data.length();

                int v = msg.getData().getInt("v");
                String rab = msg.getData().getString("rab");
                Log.d("MyLooper get age: %d", String.valueOf(v));
                Log.d("MyLooper get work: %s", rab);

                Message message = new Message();
                Bundle bundle = new Bundle();
                //bundle.putString("result", String.format("The number of letters in the word %s is %d ",data,count));
                bundle.putString("result", String.format("Age = %d Work = %s", v, rab));
                message.setData(bundle);
                // Send the message back to main thread message queue use main thread message Handler.
                mainHandler.sendMessage(message);
            }
        };
        Looper.loop();
    }
}
