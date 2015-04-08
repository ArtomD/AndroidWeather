package com.mycompany.weatherapp;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Artom on 2015-04-03.
 */
public class WeatherData extends Thread {

    public Handler mHandler;

    @Override
    public void run(){
        HttpResponse response = null;

        Looper.prepare();
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                // Act on the message
            }
        };
        Message msg = Message.obtain();
        msg.obj =  // Some Arbitrary object
                mHandler.sendMessage(msg);

        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI("https://infinite-ravine-1759.herokuapp.com/weather"));
            response = client.execute(request);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream inputStream = null;
        String result = null;
        try {
            inputStream = response.getEntity().getContent();



            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
            // json is UTF-8 by default
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            result = sb.toString();
        } catch (Exception e) {
            // Oops
        }
        finally {
            try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
        }

        Day[] days = JsonParser.parse(result);

        Looper.loop();
    }

    public void HTTP_GETRequest(){

        try {
            new Thread(new Runnable() {
                public void run() {
                    HttpResponse response = null;

                    try {
                        HttpClient client = new DefaultHttpClient();
                        HttpGet request = new HttpGet();
                        request.setURI(new URI("https://infinite-ravine-1759.herokuapp.com/weather"));
                        response = client.execute(request);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    InputStream inputStream = null;
                    String result = null;
                    try {
                        inputStream = response.getEntity().getContent();



                    HttpEntity entity = response.getEntity();
                    inputStream = entity.getContent();
                        // json is UTF-8 by default
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                        StringBuilder sb = new StringBuilder();

                        String line = null;
                        while ((line = reader.readLine()) != null)
                        {
                            sb.append(line + "\n");
                        }
                        result = sb.toString();
                    } catch (Exception e) {
                        // Oops
                    }
                    finally {
                        try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                    }

                    Day[] days = JsonParser.parse(result);

                }


            }).start();
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }


}
