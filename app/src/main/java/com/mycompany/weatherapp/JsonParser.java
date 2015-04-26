package com.mycompany.weatherapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Artom on 2015-04-04.
 */
public class JsonParser {

    public static Day[] parse(String json){
        json = "{" + json +"}";

        try {
            JSONObject jObject = new JSONObject(json);
            JSONArray jArray = jObject.getJSONArray("data");

            Day[] days = new Day[jArray.length()];

            for(int i = 0; i < jArray.length();i++){
                days[i] = new Day();
                JSONObject c = jArray.getJSONObject(i);
                days[i].setDate(c.getString("date"));
                days[i].setTempHigh(c.getString("tempHigh"));

                days[i].setTempLow(c.getString("tempLow"));
                days[i].setPrecipEarly(c.getString("precipEarly"));
                days[i].setPrecipLate(c.getString("precipLate"));
                days[i].setIconUrl(c.getString("iconUrl"));
            }
            return days;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new Day[0];
    }

}
