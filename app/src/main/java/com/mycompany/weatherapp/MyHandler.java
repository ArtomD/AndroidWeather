package com.mycompany.weatherapp;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by Artom on 2015-04-08.
 */
class MyHandler extends Handler {
    public MyHandler(Looper myLooper) {
        super(myLooper);
    }

    public void handleMessage(Message msg) {
    }
}