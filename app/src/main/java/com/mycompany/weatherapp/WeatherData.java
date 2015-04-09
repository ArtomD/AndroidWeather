package com.mycompany.weatherapp;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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
public class WeatherData {

    public Day[] HTTP_GETRequest(){

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
                    System.out.println(result);
                    Day[] days = JsonParser.parse(result);
        return days;
    }

    public Day[] makeServiceCall() {

        String url = "https://infinite-ravine-1759.herokuapp.com/weather";
        String response = null;
        try {
            // http client
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            // Checking http request method type


                HttpGet httpGet = new HttpGet(url);

            try {
                httpResponse = httpClient.execute(httpGet);
            } catch (IOException e1) {
                e1.printStackTrace();
            }


            httpEntity = httpResponse.getEntity();
           response = EntityUtils.toString(httpEntity);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response);
        Day[] days = JsonParser.parse(response);
        return days;

    }

}
