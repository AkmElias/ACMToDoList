/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dateoperations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Kazi Nayeem
 */
public class MyDateClass {

    public static final String invalidDay = "-- ---,----";
    public static final String invalidTime = "--:--:-- --";
    public static final String invalidBoth = "--:--:-- -- -- ---,----";
    
    private static Date currentTime;
    private static final DateFormat dayFormatter = new SimpleDateFormat("dd MMM,yyyy");
    private static final DateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss a");
    private static final DateFormat timedayFormatter = new SimpleDateFormat("hh:mm:ss a dd MMM,yyyy");

    public static String getCurrentDate() {
        setCurrentDate();
        return dayFormatter.format(currentTime);
    }

    public static String getCurrentTime() {
        setCurrentDate();
        return timeFormatter.format(currentTime);
    }

    public static String getCurrentBoth() {
        setCurrentDate();
        return timedayFormatter.format(currentTime);
    }

    public static Date getDateNow() {
        setCurrentDate();
        return currentTime;
    }

    public static Date getDateNow(String date) {
        Date tem = null;
        try {
            tem = timedayFormatter.parse(date);
        } catch (ParseException ex) {
            System.err.println("Date format error");
        }
        return tem;
    }

    private static void setCurrentDate() {
        currentTime = new Date();
    }
}
