package com.mycompany.weatherapp;

/**
 * Created by Artom on 2015-04-04.
 */
public class Day {
    //variables to be used to store the data
    public String date,tempHigh,tempLow,precipEarly,precipLate,iconUrl;
    //public set methods
    public void setDate(String date){
        this.date = date;
    }
    public void setTempHigh(String tempHigh){
        this.tempHigh = tempHigh;
    }
    public void setTempLow(String tempLow){
        this.tempLow = tempLow;
    }
    public void setPrecipEarly(String precipEarly){
        this.precipEarly = precipEarly;
    }
    public void setPrecipLate(String precipLate){
        this.precipLate = precipLate;
    }
    public void setIconUrl(String iconUrl){
        this.iconUrl = iconUrl;
    }

    public String getDate(){
        return this.date;
    }
    public String getTempHigh(){
        return this.tempHigh;
    }
    public String getTempLow(){
        return this.tempLow;
    }
    public String getPrecipEarly(){
        return this.precipEarly;
    }
    public String getPrecipLate(){
        return this.precipLate;
    }
    public String getIconUrl(){
        return this.iconUrl;
    }

}
