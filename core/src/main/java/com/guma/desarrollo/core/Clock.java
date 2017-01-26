package com.guma.desarrollo.core;

/**
 * Created by maryan.espinoza on 25/01/2017.
 */
import android.text.format.Time;

public class Clock {
    public static String getNow() {
        Time now = new Time();
        now.setToNow();
        String sTime = now.format("%Y-%m-%d-%T");
        return sTime;
    }
    public static String getTimeStamp() {
        Time now = new Time();
        now.setToNow();
        String sTime = now.format("%Y-%m-%d %H:%M:%S");
        return sTime;
    }

}
