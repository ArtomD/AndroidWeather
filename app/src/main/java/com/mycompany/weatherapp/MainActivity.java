package com.mycompany.weatherapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

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


public class MainActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void sendRequest1(View view){
        //final TextView scoreDisplay = (TextView)findViewById(R.id.contentText);
        //scoreDisplay.setText("Running");
        new HTTPRequestTask().execute("1");
    }
    public void sendRequest2(View view){
        //final TextView scoreDisplay = (TextView)findViewById(R.id.contentText);
        //scoreDisplay.setText("Running");
        new HTTPRequestTask().execute("2");
    }
    public void sendRequest3(View view){
        //final TextView scoreDisplay = (TextView)findViewById(R.id.contentText);
        //scoreDisplay.setText("Running");
        new HTTPRequestTask().execute("3");
    }
    public void sendRequest4(View view){
        //final TextView scoreDisplay = (TextView)findViewById(R.id.contentText);
        //scoreDisplay.setText("Running");
        new HTTPRequestTask().execute("4");
    }
    public void sendRequest5(View view){
        //final TextView scoreDisplay = (TextView)findViewById(R.id.contentText);
        //scoreDisplay.setText("Running");
        new HTTPRequestTask().execute("5");
    }

    //makes a new thread that will have access to the main view
    private class HTTPRequestTask extends AsyncTask<String, Void, Day[]>{



        //does on execute call
        //passes to onPostExecute when done
        @Override
        protected Day[] doInBackground(String... params) {
            WeatherData data = new WeatherData();
            try {
                Day[] days = data.makeServiceCall(params[0]);
                System.out.println(days.length);
                return days;
            }catch(Exception e){
                return new Day[0];
            }
        }

        //starts when doInBackground finishes
        //has access to the main views
        @Override
        protected void onPostExecute(Day[] days) {

            if(days.length==0){

            }
            String[] results = new String[days.length];
            String[] content = {"R.id.contentText", "contentText2", "contentText3", "contentText4", "contentText5"};
            for (int i = 0; i < days.length; i++) {

                results[i] = "Day " + (i + 1) + "\nHigh Temp: " + days[i].getTempHigh() + "\nLow Temp: " + days[i].getTempLow() + "\nMorning Precp %: " + days[i].getPrecipEarly() + "\nEvening Precp %: " + days[i].getPrecipLate();

            }

            ScrollView scroll = (ScrollView) findViewById(R.id.scrollView);
            scroll.removeAllViews();
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.weather_layout, null);
            scroll.addView(view);


            int[] textIds = {R.id.contentText, R.id.contentText2, R.id.contentText3, R.id.contentText4, R.id.contentText5};
            int[] imageIds = {R.id.tempImage, R.id.tempImage2, R.id.tempImage3, R.id.tempImage4, R.id.tempImage5};

            for (int i = 0; i < days.length; i++) {
                final TextView scoreDisplay = (TextView) findViewById(textIds[i]);
                scoreDisplay.setText(results[i]);
                final ImageView image1 = (ImageView) findViewById(imageIds[i]);
                if (days[i].getTempHigh().equals("")||days[i].getTempHigh().equals("")){
                    image1.setImageResource(R.drawable.snowflake1);
                }
                else if (((Integer.parseInt(days[i].getTempHigh()) + Integer.parseInt(days[i].getTempLow())) / 2) < -4)
                    image1.setImageResource(R.drawable.snowflake1);
                else if (((Integer.parseInt(days[i].getTempHigh()) + Integer.parseInt(days[i].getTempLow())) / 2) < 14)
                    image1.setImageResource(R.drawable.snowflake2);
                else if (((Integer.parseInt(days[i].getTempHigh()) + Integer.parseInt(days[i].getTempLow())) / 2) < 32)
                    image1.setImageResource(R.drawable.sunsnow1);
                else if (((Integer.parseInt(days[i].getTempHigh()) + Integer.parseInt(days[i].getTempLow())) / 2) < 50)
                    image1.setImageResource(R.drawable.sunsnow2);
                else if (((Integer.parseInt(days[i].getTempHigh()) + Integer.parseInt(days[i].getTempLow())) / 2) < 68)
                    image1.setImageResource(R.drawable.sunsnow3);
                else if (((Integer.parseInt(days[i].getTempHigh()) + Integer.parseInt(days[i].getTempLow())) / 2) < 86)
                    image1.setImageResource(R.drawable.sun1);
                else
                    image1.setImageResource(R.drawable.sun2);
            }

            for (int i = days.length; i < 5; i++) {
                final TextView scoreDisplay = (TextView) findViewById(textIds[i]);
                scoreDisplay.setVisibility(View.GONE);
                final ImageView image2 = (ImageView) findViewById(imageIds[i]);
                image2.setVisibility(View.GONE);
            }
            /*

            final TextView scoreDisplay2 = (TextView)findViewById(R.id.contentText2);
            scoreDisplay2.setText(results[1]);
            final ImageView image2 = (ImageView)findViewById(R.id.tempImage2);
            if(((Integer.parseInt(days[1].getTempHigh())+Integer.parseInt(days[1].getTempLow()))/2)<-4)
                image2.setImageResource(R.drawable.snowflake1);
            else if(((Integer.parseInt(days[1].getTempHigh())+Integer.parseInt(days[1].getTempLow()))/2)<14)
                image2.setImageResource(R.drawable.snowflake2);
            else if(((Integer.parseInt(days[1].getTempHigh())+Integer.parseInt(days[1].getTempLow()))/2)<32)
                image2.setImageResource(R.drawable.sunsnow1);
            else if(((Integer.parseInt(days[1].getTempHigh())+Integer.parseInt(days[1].getTempLow()))/2)<50)
                image2.setImageResource(R.drawable.sunsnow2);
            else if(((Integer.parseInt(days[1].getTempHigh())+Integer.parseInt(days[1].getTempLow()))/2)<68)
                image2.setImageResource(R.drawable.sunsnow3);
            else if(((Integer.parseInt(days[1].getTempHigh())+Integer.parseInt(days[1].getTempLow()))/2)<86)
                image2.setImageResource(R.drawable.sun1);
            else
                image2.setImageResource(R.drawable.sun2);


            final TextView scoreDisplay3 = (TextView)findViewById(R.id.contentText3);
            scoreDisplay3.setText(results[2]);
            final ImageView image3 = (ImageView)findViewById(R.id.tempImage3);
            if(((Integer.parseInt(days[2].getTempHigh())+Integer.parseInt(days[2].getTempLow()))/2)<-4)
                image3.setImageResource(R.drawable.snowflake1);
            else if(((Integer.parseInt(days[2].getTempHigh())+Integer.parseInt(days[2].getTempLow()))/2)<14)
                image3.setImageResource(R.drawable.snowflake2);
            else if(((Integer.parseInt(days[2].getTempHigh())+Integer.parseInt(days[2].getTempLow()))/2)<32)
                image3.setImageResource(R.drawable.sunsnow1);
            else if(((Integer.parseInt(days[2].getTempHigh())+Integer.parseInt(days[2].getTempLow()))/2)<50)
                image3.setImageResource(R.drawable.sunsnow2);
            else if(((Integer.parseInt(days[2].getTempHigh())+Integer.parseInt(days[2].getTempLow()))/2)<68)
                image3.setImageResource(R.drawable.sunsnow3);
            else if(((Integer.parseInt(days[2].getTempHigh())+Integer.parseInt(days[2].getTempLow()))/2)<86)
                image3.setImageResource(R.drawable.sun1);
            else
                image3.setImageResource(R.drawable.sun2);


            final TextView scoreDisplay4 = (TextView)findViewById(R.id.contentText4);
            scoreDisplay4.setText(results[3]);
            final ImageView image4 = (ImageView)findViewById(R.id.tempImage4);
            if(((Integer.parseInt(days[3].getTempHigh())+Integer.parseInt(days[3].getTempLow()))/2)<-4)
                image4.setImageResource(R.drawable.snowflake1);
            else if(((Integer.parseInt(days[3].getTempHigh())+Integer.parseInt(days[3].getTempLow()))/2)<14)
                image4.setImageResource(R.drawable.snowflake2);
            else if(((Integer.parseInt(days[3].getTempHigh())+Integer.parseInt(days[3].getTempLow()))/2)<32)
                image4.setImageResource(R.drawable.sunsnow1);
            else if(((Integer.parseInt(days[3].getTempHigh())+Integer.parseInt(days[3].getTempLow()))/2)<50)
                image4.setImageResource(R.drawable.sunsnow2);
            else if(((Integer.parseInt(days[3].getTempHigh())+Integer.parseInt(days[3].getTempLow()))/2)<68)
                image4.setImageResource(R.drawable.sunsnow3);
            else if(((Integer.parseInt(days[3].getTempHigh())+Integer.parseInt(days[3].getTempLow()))/2)<86)
                image4.setImageResource(R.drawable.sun1);
            else
                image4.setImageResource(R.drawable.sun2);


            final TextView scoreDisplay5 = (TextView)findViewById(R.id.contentText5);
            scoreDisplay5.setText(results[4]);
            final ImageView image5 = (ImageView)findViewById(R.id.tempImage5);
            if(((Integer.parseInt(days[4].getTempHigh())+Integer.parseInt(days[4].getTempLow()))/2)<-4)
                image5.setImageResource(R.drawable.snowflake1);
            else if(((Integer.parseInt(days[4].getTempHigh())+Integer.parseInt(days[4].getTempLow()))/2)<14)
                image5.setImageResource(R.drawable.snowflake2);
            else if(((Integer.parseInt(days[4].getTempHigh())+Integer.parseInt(days[4].getTempLow()))/2)<32)
                image5.setImageResource(R.drawable.sunsnow1);
            else if(((Integer.parseInt(days[4].getTempHigh())+Integer.parseInt(days[4].getTempLow()))/2)<50)
                image5.setImageResource(R.drawable.sunsnow2);
            else if(((Integer.parseInt(days[4].getTempHigh())+Integer.parseInt(days[4].getTempLow()))/2)<68)
                image5.setImageResource(R.drawable.sunsnow3);
            else if(((Integer.parseInt(days[4].getTempHigh())+Integer.parseInt(days[4].getTempLow()))/2)<86)
                image5.setImageResource(R.drawable.sun1);
            else
                image5.setImageResource(R.drawable.sun2);

        */
        }
    }



}
